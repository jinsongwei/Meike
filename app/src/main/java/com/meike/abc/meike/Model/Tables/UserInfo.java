package com.meike.abc.meike.Model.Tables;



import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMarshalling;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;
import com.meike.abc.meike.Model.Constants.TableName;
import com.meike.abc.meike.Model.Util.Converters;

import java.util.Calendar;
import java.util.List;
import java.util.Map;


@DynamoDBTable(tableName = TableName.User)
public class UserInfo {

    private static final String USER_NAME = "用户名";
    private static final String PASS_WORD = "密码";
    private static final String PHONE = "电话";
    private static final String EMAIL = "邮箱";
    private static final String JOIN_TIME = "加入";
    private static final String CREDIT = "积分";
    private static final String REGION = "地区";
    private static final String POST_LIST = "发布";
    private static final String FAVOURITE_LIST = "收藏";

    private String posterId;
    private String userName;
    private String password;
    private String phone;
    private String email;

    private Calendar joinTime;
    private int credit;
    private String region; // e.g. 洛杉矶 纽约 ...
    private Map<Integer, String[]> userPosts; // left store type of PostInfo,right stores postId
    private Map<Integer, String[]> userFavourites; // left store type of PostInfo,right stores postId

    @DynamoDBHashKey
    public String getPosterId() {
        return posterId;
    }
    public void setPosterId(String posterId) {
        this.posterId = posterId;
    }

    @DynamoDBAttribute(attributeName = USER_NAME)
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @DynamoDBAttribute(attributeName = PASS_WORD)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @DynamoDBAttribute(attributeName = PHONE)
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @DynamoDBAttribute(attributeName = EMAIL)
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @DynamoDBAttribute(attributeName = JOIN_TIME)
    @DynamoDBMarshalling(marshallerClass = Converters.CalendarConverter.class)
    public Calendar getJoinTime() {
        return joinTime;
    }
    public void setJoinTime(Calendar joinTime) {
        this.joinTime = joinTime;
    }

    @DynamoDBAttribute(attributeName = CREDIT)
    public int getCredit() {
        return credit;
    }
    public void setCredit(int credit) {
        this.credit = credit;
    }

    @DynamoDBAttribute(attributeName = REGION)
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }

    @DynamoDBAttribute(attributeName = POST_LIST)
    public Map<Integer, String[]> getUserPosts() {
        return userPosts;
    }
    public void setUserPosts(Map<Integer, String[]> userPosts) {
        this.userPosts = userPosts;
    }

    @DynamoDBAttribute(attributeName = FAVOURITE_LIST)
    public Map<Integer, String[]> getUserFavourites() {
        return userFavourites;
    }
    public void setUserFavourites(Map<Integer, String[]> userFavourites) {
        this.userFavourites = userFavourites;
    }


}
