package com.meike.abc.meike.Temp;

import android.content.Context;
import android.location.Address;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.S3Link;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.meike.abc.meike.Model.Constants.Region;
import com.meike.abc.meike.Model.Support.CompanyInfo;
import com.meike.abc.meike.Model.Support.ContactInfo;
import com.meike.abc.meike.Model.Support.ReviewMessage;
import com.meike.abc.meike.Model.Tables.HousingItem;
import com.meike.abc.meike.Model.Tables.PostItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;



public class RandomInfo {

    private static List<Character> chars = getChars();

    private static void generateRandomPostItem(Context context, PostItem item) {
        Random rd = new Random();
        item.setRegion(Region.LA.getChi());
        item.setPostId(10000 + rd.nextInt(200));
        item.setPosterId("1" + rd.nextInt(10));
        item.setTitle(getRandomString(5));
        item.setPrice(rd.nextDouble() * 1000);
        item.setPostTime(getRandomCalendar());
        item.setAddress(getRandomAddress());
        item.setMainImage(getRandomOneImages(context));
        item.setDetail(getRandomString(100));
        item.setContactInfo(getRandomContact("1" + rd.nextInt(10)));
        item.setMessageList(getRandomMessageList());
    }

    public static HousingItem getRandomHousingItem(Context context) {
        Random rd = new Random();

        HousingItem item = new HousingItem();
        generateRandomPostItem(context, item);

        item.setHousingType(HousingItem.HOUSING_VALUES[rd.nextInt(7)]);
        item.setLeasingType(HousingItem.LEASING_VALUES[rd.nextInt(7)]);
        item.setNumOfRooms(rd.nextInt(5));
        item.setNumOfBaths(rd.nextInt(2));
        item.setNumOfTenants(rd.nextInt(8));
        item.setSize(rd.nextInt(5000));
        item.setAvailableDate(getRandomCalendar());
        item.setDeposit(rd.nextDouble() * 2000);
        item.setPrivateRoom(rd.nextBoolean());
        item.setPrivateBath(rd.nextBoolean());
        item.setFurnished(rd.nextBoolean());
        item.setSmokingOK(rd.nextBoolean());
        item.setPetsOK(rd.nextBoolean());
        item.setLaundryType(HousingItem.LAUNDRY_VALUES[rd.nextInt(4)]);
        item.setParkingType(HousingItem.PARKING_VALUES[rd.nextInt(5)]);

        return item;
    }


    /**
     *
     *     ------    helping functions  -----
     */

    private static List<Character> getChars(){
        List<Character> chars = new ArrayList<>();
        for(char c = 'a'; c <= 'z'; c++){
            chars.add(c);
        }
        return chars;
    }
    private static Calendar getRandomCalendar(){
        Random rd = new Random();

        Calendar cal = Calendar.getInstance();
        cal.set(2015, rd.nextInt(11) + 1, rd.nextInt(30) + 1,
                rd.nextInt(22) + 1, rd.nextInt(58) + 1, rd.nextInt(58) + 1);

        return cal;
    }

    private static Address getRandomAddress(){
        Random rd = new Random();
        Address addr = new Address(Locale.ENGLISH);
        String[] address = new String[2];
        address[0] = Integer.toString(rd.nextInt(999));
        address[1] = getRandomString(5);

        String adminArea = getRandomString(2).toUpperCase();
        String postalCode = Integer.toString(92000 + rd.nextInt(999));
        String locality = getRandomString(1).toUpperCase() +
                getRandomString(5);
        addr.setAddressLine(0, address[0]);
        addr.setAddressLine(1, address[1]);
        addr.setAdminArea(adminArea);
        addr.setPostalCode(postalCode);
        addr.setLocality(locality);

        return addr;
    }

    private static String getRandomString(int max){
        Random rd = new Random();
        String text = "";

        for(int i = 0; i < max; i++){
            text += chars.get(rd.nextInt(25));
        }
        return text;
    }

    private static ContactInfo getRandomContact(String phoneNum){
        Random rd = new Random();

        String name = getRandomString(4);
        String phone = phoneNum;
        String email = getRandomString(7) + "@gmail.com";
        String weChat = getRandomString(3) + Integer.toString(rd.nextInt(1000));
        String qq = Integer.toString(10000) + Integer.toString(10000);
        Address addr = getRandomAddress();

        ContactInfo ci = new ContactInfo();
        ci.setName(name);
        ci.setPhoneNumber(phone);
        ci.setEmail(email);
        ci.setWeChat(weChat);
        ci.setQq(qq);
        ci.setAddress(addr);

        return ci;
    }

    private static List<ReviewMessage> getRandomMessageList() {
        List<ReviewMessage> list = new ArrayList<>();
        int num = new Random().nextInt(20);
        for (int i = 0; i < num; i++) {
            list.add(getRandomReviewMessage());
        }
        return list;
    }

    private static ReviewMessage getRandomReviewMessage(){
        ReviewMessage reviewMessage = new ReviewMessage();

        String posterName = getRandomString(7);
        String message = getRandomString(40);
        Calendar cal = getRandomCalendar();

        reviewMessage.setPosterName(posterName);
        reviewMessage.setMessage(message);
        reviewMessage.setPostTime(cal);

        return reviewMessage;
    }

    private static CompanyInfo getRandomCompany(){
        Random rd = new Random();
        CompanyInfo ci = new CompanyInfo();

        ci.setPhoto(rd.nextInt(5));
        ci.setName(getRandomString(5));
        ci.setIndustry(getRandomString(5));
        ci.setWebsite("http://" + getRandomString(7) + ".com");
        ci.setDescription(getRandomString(8));
        ci.setLocation(getRandomAddress());

        return ci;
    }
    private static S3Link getRandomOneImages(Context context){
        Random rd = new Random();
        AWSClientManager acm = new AWSClientManager(context);
        AmazonDynamoDBClient ddb = acm.ddb();
        DynamoDBMapper mapper = new DynamoDBMapper(ddb,acm.getAWSCredential());

        S3Link image = mapper.createS3Link("otta-0",getRandomString(rd.nextInt(5)) + ".jpg");
        return image;
    }
    private static List<S3Link> getRandomImages(Context context){

        Random rd = new Random();
        AWSClientManager acm = new AWSClientManager(context);
        AmazonDynamoDBClient ddb = acm.ddb();
        DynamoDBMapper mapper = new DynamoDBMapper(ddb,acm.getAWSCredential());

        List<S3Link> images = new ArrayList<>();
        for(int i = 0; i<rd.nextInt(3);i++) {
            S3Link image = mapper.createS3Link("otta-0",getRandomString(rd.nextInt(3))+".jpg");
            images.add(image);
        }
        return images;
    }

}
