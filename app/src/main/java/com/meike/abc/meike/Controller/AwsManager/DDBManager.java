package com.meike.abc.meike.Controller.AwsManager;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.meike.abc.meike.Controller.Util.Util;
import com.meike.abc.meike.Model.Constants.Region;
import com.meike.abc.meike.Model.Constants.TableType;
import com.meike.abc.meike.Model.Support.PostAttribute;
import com.meike.abc.meike.Model.Support.ReviewMessage;
import com.meike.abc.meike.Model.Tables.PostItem;
import com.meike.abc.meike.Model.Tables.UserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


final public class DDBManager {

    public enum LoadOperator {
        Load(null),
        LoadMore(ComparisonOperator.LT),
        Refresh(ComparisonOperator.GT);

        private final ComparisonOperator Op;

        LoadOperator(ComparisonOperator op) {
            this.Op = op;
        }
    }

    private static final String TAG = "AmazonExceptionService";
    private static final int LIMIT = 5;
    private static final String RANGE_KEY = "postId";

    private AWSClientManager acm;
    private AmazonDynamoDBClient ddb;
    private DynamoDBMapper mapper;

    public DDBManager(AWSClientManager acm, AmazonDynamoDBClient ddb, DynamoDBMapper mapper) {
        this.acm = acm;
        this.ddb = ddb;
        this.mapper = mapper;
    }

    /**
     * loadList Method combines the previous loadList, loadMore, refreshList altogether,
     * distinguished by LoadOperator.
     * @param id the id needed for the load.
     * @param loadOperator to distinguish the type of load:
     *                     load, loadMore, refresh.
     * @return the loaded list from amazon.
     */
    public List<PostItem> loadList(TableType type, Region region, int id, LoadOperator loadOperator,
                                    List<PostAttribute> filter){
        /** Regular routine shared by all kinds of query. */
        PostItem item = Util.getItemObject(type);
        assert item != null;
        item.setRegion(region.getChi());
        Class clazz = item.getClass();

        DynamoDBQueryExpression<PostItem> queryExp = new DynamoDBQueryExpression<PostItem>()
            .withScanIndexForward(false)
            .withHashKeyValues(item);

        /** If refresh, load all the post items. */
        if (loadOperator != LoadOperator.Refresh) {
            queryExp.withLimit(LIMIT);
        }

        /** If not load, set comparison conditions. */
        if (loadOperator != LoadOperator.Load) {
            Condition cond = new Condition().withComparisonOperator(loadOperator.Op)
                .withAttributeValueList(new AttributeValue().withN("" + id));
            queryExp.withRangeKeyCondition(RANGE_KEY, cond);
        }

        /** If not filtering, return results. */
        try {

            if (filter.isEmpty()) {
                return mapper.queryPage(clazz, queryExp).getResults();
            }

            return loadFilterList(type, filter, queryExp, clazz);

        } catch (AmazonServiceException a) {
            acm.wipeCredentialsOnAuthError(a);
            a.printStackTrace();
            throw new AmazonServiceException(TAG);
        }

    }

    public boolean uploadMessage(TableType type, String postId, ReviewMessage message) {

        PostItem item = Util.getItemObject(type);
        assert item != null;
        Class clazz = item.getClass();

        try {
            PostItem target = (PostItem) mapper.load(clazz, postId);
            target.getMessageList().add(0, message);
            mapper.save(target);
            return true;
        } catch (AmazonServiceException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public int uploadPost(PostItem item) {
        /**
         * Need to get the postId from server.
         */
        int postId= 11000;

        try {
            item.setPostId(postId);
            mapper.save(item);
        } catch (AmazonServiceException ex) {
            ex.printStackTrace();
            acm.wipeCredentialsOnAuthError(ex);
            return -1;
        }
        return postId;
    }

    public boolean addToUserList(PostItem post, UserInfo user, Map<Integer, String[]> map, boolean toAdd) {
        try {
            if (toAdd) {
                if (map == null) {
                    map = new HashMap<>();
                }
                map.put(post.getPostId(), new String[]{
                    post.tableType().getName(),
                    post.getRegion()
                });
            } else {
                if (map == null || map.isEmpty()) {
                    return false;
                }
                map.remove(post.getPostId());
            }

            mapper.save(user);
            return true;
        } catch (AmazonServiceException ex) {
            acm.wipeCredentialsOnAuthError(ex);
            ex.printStackTrace();
            return false;
        }
    }

    public List<PostItem> batchLoadList(Map<Integer, String[]> map) {
        ArrayList<Object> itemsToGet = new ArrayList<>();
        for (Map.Entry<Integer, String[]> entry: map.entrySet()) {
            String[] pair = entry.getValue();

            PostItem item = Util.getItemObject(pair[0]);
            assert item != null;
            item.setRegion(pair[1]);
            item.setPostId(entry.getKey());

            itemsToGet.add(item);
        }

        try {
            Map<String, List<Object>> resultMap = mapper.batchLoad(itemsToGet);

            List<PostItem> resultList = new ArrayList<>();
            for (Map.Entry<String, List<Object>> entry: resultMap.entrySet()) {
                for (Object item: entry.getValue()) {
                    resultList.add((PostItem) item);
                }
            }
            return resultList;
        } catch (AmazonServiceException ex) {
            acm.wipeCredentialsOnAuthError(ex);
            ex.printStackTrace();
            throw new AmazonServiceException("amazon service exception");
        }

    }

    private List<PostItem> loadFilterList(TableType type, List<PostAttribute> filter, DynamoDBQueryExpression<PostItem> queryExp, Class clazz) {

        Map<String, AttributeValue> expAttrValues = new HashMap<>();
        Map<String, String> expAttrNames = new HashMap<>();

        String filterExpression = getFilterExpression(filter, expAttrValues, expAttrNames);

        queryExp.withFilterExpression(filterExpression)
            .withExpressionAttributeValues(expAttrValues)
            .withExpressionAttributeNames(expAttrNames);

        try {
            int limit = LIMIT * filter.size();
            long count = ddb.describeTable(type.getName()).getTable().getItemCount();

            List<PostItem> resultList;
            do {
                queryExp.withLimit(limit);
                resultList = mapper.queryPage(clazz, queryExp).getResults();

                if (resultList.isEmpty()) {
                    limit *= 2;
                }

            } while (resultList.isEmpty()); //&& limit < (int) count * 2);

            return resultList;

        } catch (AmazonServiceException ex) {
            acm.wipeCredentialsOnAuthError(ex);
            ex.printStackTrace();
            throw new AmazonServiceException(TAG);
        }

    }

    private String getFilterExpression(List<PostAttribute> filter, Map<String, AttributeValue> mapValues, Map<String, String> mapNames){

        String filterExp = "";
        int count = 0;

        for(PostAttribute attr: filter) {
            String attributeName = "#type" + count;
            String attributeValue = ":value" + count;

            mapNames.put(attributeName, attr.getName());

            if (attr.getType() == PostAttribute.Type.Number) {
                mapValues.put(attributeValue, new AttributeValue().withN(attr.getValue()));
            } else {
                mapValues.put(attributeValue, new AttributeValue().withS(attr.getValue()));
            }

            if(count > 0)
                filterExp += " AND ";

            char symbol = attr.getCompare();
            if(symbol == '=')
                filterExp += attributeName + " = " + attributeValue;
            else {
                filterExp += attributeName + " " + symbol + "= " + attributeValue;
            }

            count++;
        }

        return filterExp;
    }
}

