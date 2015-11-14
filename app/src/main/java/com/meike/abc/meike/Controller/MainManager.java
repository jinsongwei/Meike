package com.meike.abc.meike.Controller;

import android.content.Context;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.meike.abc.meike.Controller.AwsManager.AWSClientManager;
import com.meike.abc.meike.Controller.AwsManager.DDBManager;
import com.meike.abc.meike.Controller.AwsManager.S3Manager;
import com.meike.abc.meike.Model.Constants.Region;
import com.meike.abc.meike.Model.Constants.TableType;
import com.meike.abc.meike.Model.DataCenter;
import com.meike.abc.meike.Model.Support.PostAttribute;
import com.meike.abc.meike.Model.Tables.PostItem;
import com.meike.abc.meike.Model.Tables.UserInfo;
import com.meike.abc.meike.Model.UserCenter;

import java.util.List;

public final class MainManager {

    private static DDBManager ddbManager;
    private static S3Manager s3Manager;

    private static DataCenter dataCenter;
    private static UserCenter userCenter;

    private static void init(Context context) {
        AWSClientManager acm = new AWSClientManager(context);
        AmazonDynamoDBClient ddb = acm.ddb();
        DynamoDBMapper mapper = new DynamoDBMapper(ddb, acm.getAWSCredential());

        ddbManager = new DDBManager(acm, ddb, mapper);
        dataCenter = new DataCenter();
        userCenter = new UserCenter();
    }

    public void setCurrentRegion(Region region) {
        dataCenter.setCurrentRegion(region);
    }

    public void setCurrentType(TableType type) {
        dataCenter.setCurrentType(type);
    }

    public void setCurrentFilter(List<PostAttribute> filter) {
        dataCenter.setCurrentFilter(filter);
    }

    public boolean loadList() {
        List<PostItem> temp = ddbManager.loadList(
            dataCenter.getCurrentType(),
            dataCenter.getCurrentRegion(),
            0, DDBManager.LoadOperator.Load,
            dataCenter.getCurrentFilter());

        dataCenter.addLast(temp);
        return !temp.isEmpty();
    }

    public boolean loadMoreList() {
        List<PostItem> list = dataCenter.getCurrentList();
        List<PostItem> temp = ddbManager.loadList(
            dataCenter.getCurrentType(),
            dataCenter.getCurrentRegion(),
            list.get(list.size() - 1).getPostId(),
            DDBManager.LoadOperator.LoadMore,
            dataCenter.getCurrentFilter());
        dataCenter.addLast(temp);
        return !temp.isEmpty();
    }

    public boolean refreshList() {
        List<PostItem> temp = ddbManager.loadList(
            dataCenter.getCurrentType(),
            dataCenter.getCurrentRegion(),
            dataCenter.getCurrentList().get(0).getPostId(),
            DDBManager.LoadOperator.Refresh,
            dataCenter.getCurrentFilter());
        dataCenter.addLast(temp);
        return !temp.isEmpty();
    }

    public boolean loadUserFavourite() {
        List<PostItem> resultList = ddbManager.batchLoadList(userCenter.getUser().getUserFavourites());
        userCenter.loadFavourite(resultList);
        return !resultList.isEmpty();
    }

    public boolean loadUserPostList() {
        List<PostItem> resultList = ddbManager.batchLoadList(userCenter.getUser().getUserPosts());
        userCenter.loadFavourite(resultList);
        return !resultList.isEmpty();
    }

    public void addUserFavourite(PostItem post) {
        UserInfo user = userCenter.getUser();
        ddbManager.addToUserList(post, user, user.getUserFavourites(), true);
        userCenter.addFavourite(post);
    }

    public void removeUserFavourite(PostItem post) {
        UserInfo user = userCenter.getUser();
        ddbManager.addToUserList(post, user, user.getUserFavourites(), false);
        userCenter.removeFavourite(post);
    }

    public void addUserPostList(PostItem post) {
        UserInfo user = userCenter.getUser();
        ddbManager.addToUserList(post, user, user.getUserPosts(), true);
        userCenter.addUserPost(post);
    }

    public void removeUserPostList(PostItem post) {
        UserInfo user = userCenter.getUser();
        ddbManager.addToUserList(post, user, user.getUserPosts(), false);
        userCenter.removeUserPost(post);
    }

    public boolean uploadPost(PostItem post) {
        ddbManager.uploadPost(post);
        userCenter.addUserPost(post);
        /** need to upload imagelist as well. Come back later.*/
        return true;
    }

    private static MainManager instance;
    private MainManager() {}

    public static MainManager getInstance(Context context) {
        if (instance == null) {
            instance = new MainManager();
        }
        init(context);
        return instance;
    }
}
