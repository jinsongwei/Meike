package com.meike.abc.meike.Model.Support;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBDocument;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMarshalling;
import com.meike.abc.meike.Model.Util.Converters;

import java.util.Calendar;

@DynamoDBDocument
public class ReviewMessage {
    private String posterName;
    private Calendar postTime;
    private String message;

    @DynamoDBAttribute(attributeName = "posterName")
    public String getPosterName() {
        return posterName;
    }
    public void setPosterName(String posterName){
        this.posterName = posterName;
    }

    @DynamoDBMarshalling(marshallerClass = Converters.CalendarConverter.class)
    public Calendar getPostTime() {
        return postTime;
    }
    public void setPostTime(Calendar postTime){
        this.postTime = postTime;
    }

    @DynamoDBAttribute(attributeName = "message")
    public String getMessage() {
        return message;
    }
    public void setMessage(String message){
        this.message = message;
    }
}
