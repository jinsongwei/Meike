package com.meike.abc.meike.Model.Tables;

import android.location.Address;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMarshalling;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.S3Link;
import com.meike.abc.meike.Model.Constants.Region;
import com.meike.abc.meike.Model.Constants.TableName;
import com.meike.abc.meike.Model.Constants.TableType;
import com.meike.abc.meike.Model.Util.Converters;
import com.meike.abc.meike.Model.Support.ContactInfo;
import com.meike.abc.meike.Model.Support.PostAttribute;
import com.meike.abc.meike.Model.Support.ReviewMessage;

import java.util.Calendar;
import java.util.List;

@DynamoDBTable(tableName = TableName.Post)
public abstract class PostItem {

    static final String STRING = "地区";
    static final String POST_ID = "ID";
    static final String POSTER_ID = "发布人ID";
    static final String TITLE = "标题";
    static final String PRICE = "价格";
    static final String POST_TIME = "发布时间";
    static final String ADDRESS = "地址";
    static final String MAIN_IMAGE = "主照片";
    static final String IMAGE_LIST = "照片列表";
    static final String DETAIL = "详细细节";
    static final String CONTACT_INFO = "联系人";
    static final String MESSAGE_LIST = "留言板";

    private String region;
    private int postId;
    private String posterId;
    private String title;
    private double price;
    private Calendar postTime;
    private Address address;
    private S3Link mainImage;
    private List<S3Link> imageList;
    private String detail;
    private ContactInfo contactInfo;
    private List<ReviewMessage> messageList;


    /**
     * Public Methods for DDBMapper.
     */
    @DynamoDBHashKey
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }

    @DynamoDBRangeKey
    public int getPostId() {
        return postId;
    }
    public void setPostId(int postId) {
        this.postId = postId;
    }

    @DynamoDBAttribute(attributeName = POSTER_ID)
    public String getPosterId() {
        return posterId;
    }
    public void setPosterId(String posterId) {
        this.posterId = posterId;
    }

    @DynamoDBAttribute(attributeName = TITLE)
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @DynamoDBAttribute(attributeName = PRICE)
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @DynamoDBAttribute(attributeName = POST_TIME)
    @DynamoDBMarshalling(marshallerClass = Converters.CalendarConverter.class)
    public Calendar getPostTime() {
        return postTime;
    }
    public void setPostTime(Calendar postTime) {
        this.postTime = postTime;
    }

    @DynamoDBAttribute(attributeName = ADDRESS)
    @DynamoDBMarshalling(marshallerClass = Converters.AddressConverter.class)
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    @DynamoDBAttribute(attributeName = DETAIL)
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }

    @DynamoDBAttribute(attributeName = MAIN_IMAGE)
    public S3Link getMainImage() {
        return mainImage;
    }
    public void setMainImage(S3Link mainImage) {
        this.mainImage = mainImage;
    }

    @DynamoDBAttribute(attributeName = IMAGE_LIST)
    public List<S3Link> getImageList() {
        return imageList;
    }
    public void setImageList(List<S3Link> imageList) {
        this.imageList = imageList;
    }

    @DynamoDBAttribute(attributeName = CONTACT_INFO)
    @DynamoDBMarshalling(marshallerClass = Converters.ContactInfoConverter.class)
    public ContactInfo getContactInfo() {
        return contactInfo;
    }
    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }


    @DynamoDBAttribute(attributeName = MESSAGE_LIST)
    public List<ReviewMessage> getMessageList() {
        return messageList;
    }
    public void setMessageList(List<ReviewMessage> messageList) {
        this.messageList = messageList;
    }



    /**
     * Abstract Methods.
     */

    /**
     * Subtitle 1.
     * @return Subtitle 1.
     */
    public abstract String subTitle1();

    /**
     * Subtitle 2.
     * @return Subtitle 2.
     */
    public abstract String subTitle2();

    /**
     * Subtitle 3.
     * @return Subtitle 3.
     */
    public abstract String subTitle3();

    /**
     * Subtitle 4.
     * @return Subtitle 4.
     */
    public abstract String subTitle4();


    public abstract TableType tableType();


    public abstract List<PostAttribute> attributeValueList();

}
