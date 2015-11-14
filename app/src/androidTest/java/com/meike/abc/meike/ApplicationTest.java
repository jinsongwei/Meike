package com.meike.abc.meike;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.QueryResultPage;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.meike.abc.meike.Model.Constants.Region;
import com.meike.abc.meike.Model.Tables.HousingItem;
import com.meike.abc.meike.Model.Tables.PostItem;
import com.meike.abc.meike.Temp.AWSClientManager;
import com.meike.abc.meike.Temp.RandomInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    private AmazonDynamoDBClient ddb;
    private AWSClientManager acm;
    private DynamoDBMapper mapper;

    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        acm = new AWSClientManager(getSystemContext());
        ddb = acm.ddb();
        mapper = new DynamoDBMapper(ddb);
    }

    public void testName() throws Exception {
        createTable();
        upLoad();
        upLoad();
        upLoad();
        loadList();
    }

    private void createTable() {

        ProvisionedThroughput pt = new ProvisionedThroughput()
            .withReadCapacityUnits((long) 10).withWriteCapacityUnits((long) 2);

        CreateTableRequest request1 = mapper.generateCreateTableRequest(HousingItem.class)
            .withProvisionedThroughput(pt);

        try{
            ddb.createTable(request1);
            Log.d("DynamoDB", "create table request successfully relieved");

        }
        catch (ConditionalCheckFailedException e) {
            Log.e("", e.getMessage());
            e.printStackTrace();
        }
        catch(AmazonServiceException ex){
            Log.e("DynamoDB", "Error sending create table request", ex);
            acm.wipeCredentialsOnAuthError(ex);
        }
    }

    private void upLoad() {
        List<HousingItem> housingItemList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            housingItemList.add(RandomInfo.getRandomHousingItem(getContext()));
        }

        Log.e("", "trying...");
        mapper.batchSave(housingItemList);
        Log.e("", "done");
    }


    private void loadList() {

        PostItem item = new HousingItem();
        item.setRegion(Region.LA.getChi());
        Class clazz = HousingItem.class;

        try {
            DynamoDBQueryExpression<PostItem> queryExp = new DynamoDBQueryExpression<PostItem>()
                .withScanIndexForward(false)
                .withHashKeyValues(item);
            queryExp.setLimit(5);


            List<? extends PostItem> resultList;
            //noinspection unchecked
            mapper = new DynamoDBMapper(ddb, acm.getAWSCredential());
            QueryResultPage<? extends PostItem> resultQuery = mapper.queryPage(clazz, queryExp);
            resultList = resultQuery.getResults();

            for (PostItem i: resultList) {
                Log.e("", "" + i.getPostId());
            }

        } catch (AmazonServiceException a) {
            acm.wipeCredentialsOnAuthError(a);
            throw new AmazonServiceException("error");
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

    }
}