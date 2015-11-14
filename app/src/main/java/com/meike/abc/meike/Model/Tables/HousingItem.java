package com.meike.abc.meike.Model.Tables;


import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMarshalling;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;
import com.meike.abc.meike.Model.Constants.TableName;
import com.meike.abc.meike.Model.Constants.TableType;
import com.meike.abc.meike.Model.Support.PostAttribute;
import com.meike.abc.meike.Model.Util.Converters;
import com.meike.abc.meike.Model.Util.Translator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.meike.abc.meike.Model.Support.PostAttribute.Type.Boolean;
import static com.meike.abc.meike.Model.Support.PostAttribute.Type.Number;


@DynamoDBTable(tableName = TableName.Housing)
public final class HousingItem extends PostItem {

    private static final String HOUSING_TYPE = "房屋";
    private static final String LEASING_TYPE = "交易";
    private static final String NUM_OF_ROOMS = "房间";
    private static final String NUM_OF_BATHS = "浴室";
    private static final String NUM_OF_TENANTS = "房客";
    private static final String SIZE = "规模";
    private static final String AVAILABLE_DATE = "入住日期";
    private static final String DEPOSIT = "押金";
    private static final String PRIVATE_ROOM = "私人房间";
    private static final String PRIVATE_BATH = "私人浴室";
    private static final String FURNISHED = "已装修";
    private static final String SMOKING_OK = "可以抽烟";
    private static final String PETS_OK = "欢迎宠物";
    private static final String LAUNDRY_TYPE = "洗衣机";
    private static final String PARKING_TYPE = "停车位";

    public static final String[] HOUSING_VALUES = new String[]{
        "全部",
        "独立房",
        "公寓",
        "别墅",
        "办公室",
        "商铺",
        "其他"
    };

    public static final String[] LEASING_VALUES = new String[]{
        "全部",
        "整租",
        "合租",
        "短期",
        "转让",
        "出售",
        "其他"
    };

    public static final String[] PARKING_VALUES = new String[] {
        "全部",
        "车库",
        "小区",
        "街道",
        "没有"
    };

    public static final String[] LAUNDRY_VALUES = new String[] {
        "全部",
        "室内",
        "小区内",
        "没有"
    };


    private String housingType;
    private String leasingType;
    private int numOfRooms;
    private double numOfBaths;
    private int numOfTenants;
    private int size;
    private Calendar availableDate;
    private double deposit;
    private boolean privateRoom;
    private boolean privateBath;
    private boolean furnished;
    private boolean smokingOK;
    private boolean petsOK;
    private String laundryType;
    private String parkingType;



    @Override
    public String subTitle1() {
        return housingType;
    }

    @Override
    public String subTitle2() {
        return leasingType;
    }

    @Override
    public String subTitle3() {
        return numOfRooms + "室" + numOfBaths + "浴";
    }

    @Override
    public String subTitle4() {
        return size + "平尺";
    }

    @Override
    public TableType tableType() {
        return TableType.Housing;
    }

    @Override
    public List<PostAttribute> attributeValueList() {
        List<PostAttribute> list = new ArrayList<>();
        list.add(new PostAttribute(HOUSING_TYPE, housingType));
        list.add(new PostAttribute(LEASING_TYPE, leasingType));
        list.add(new PostAttribute(NUM_OF_ROOMS, "" + numOfRooms));
        list.add(new PostAttribute(NUM_OF_BATHS, Translator.doubleToString(numOfBaths)));
        list.add(new PostAttribute(NUM_OF_TENANTS, "" + numOfTenants));
        list.add(new PostAttribute(SIZE, "" + size));
        list.add(new PostAttribute(DEPOSIT, Translator.priceToString(deposit)));
        list.add(new PostAttribute(PRIVATE_ROOM, Translator.booleanToString(privateRoom)));
        list.add(new PostAttribute(PRIVATE_BATH, Translator.booleanToString(privateBath)));
        list.add(new PostAttribute(FURNISHED, Translator.booleanToString(furnished)));
        list.add(new PostAttribute(PARKING_TYPE, parkingType));
        list.add(new PostAttribute(LAUNDRY_TYPE, laundryType));
        list.add(new PostAttribute(AVAILABLE_DATE, Translator.postTimeToString(availableDate)));
        list.add(new PostAttribute(SMOKING_OK, Translator.booleanToString(smokingOK)));
        list.add(new PostAttribute(PETS_OK, Translator.booleanToString(petsOK)));
        return list;
    }

    public static List<PostAttribute> attributeNameList() {
        List<PostAttribute> list = new ArrayList<>();
        list.add(new PostAttribute(HOUSING_TYPE, HOUSING_VALUES));
        list.add(new PostAttribute(LEASING_TYPE, LEASING_VALUES));
        list.add(new PostAttribute(NUM_OF_ROOMS, Number));
        list.add(new PostAttribute(NUM_OF_BATHS, Number));
        list.add(new PostAttribute(NUM_OF_TENANTS, Number));
        list.add(new PostAttribute(SIZE, Number));
        list.add(new PostAttribute(DEPOSIT, Number));
        list.add(new PostAttribute(PRIVATE_ROOM, Boolean));
        list.add(new PostAttribute(PRIVATE_BATH, Boolean));
        list.add(new PostAttribute(FURNISHED, Boolean));
        list.add(new PostAttribute(PARKING_TYPE, PARKING_VALUES));
        list.add(new PostAttribute(LAUNDRY_TYPE, LAUNDRY_VALUES));
        list.add(new PostAttribute(AVAILABLE_DATE, Number));
        list.add(new PostAttribute(SMOKING_OK, Boolean));
        list.add(new PostAttribute(PETS_OK, Boolean));
        return list;
    }

    @DynamoDBAttribute(attributeName = HOUSING_TYPE)
    public String getHousingType() {
        return housingType;
    }
    public void setHousingType(String housingType) {
        this.housingType = housingType;
    }

    @DynamoDBAttribute(attributeName = LEASING_TYPE)
    public String getLeasingType() {
        return leasingType;
    }
    public void setLeasingType(String leasingType) {
        this.leasingType = leasingType;
    }

    @DynamoDBAttribute(attributeName = NUM_OF_ROOMS)
    public int getNumOfRooms() {
        return numOfRooms;
    }
    public void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    @DynamoDBAttribute(attributeName = NUM_OF_BATHS)
    public double getNumOfBaths() {
        return numOfBaths;
    }
    public void setNumOfBaths(double numOfBaths) {
        this.numOfBaths = numOfBaths;
    }

    @DynamoDBAttribute(attributeName = NUM_OF_TENANTS)
    public int getNumOfTenants() {
        return numOfTenants;
    }
    public void setNumOfTenants(int numOfTenants) {
        this.numOfTenants = numOfTenants;
    }

    @DynamoDBAttribute(attributeName = SIZE)
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }

    @DynamoDBAttribute(attributeName = AVAILABLE_DATE)
    @DynamoDBMarshalling(marshallerClass = Converters.CalendarConverter.class)
    public Calendar getAvailableDate() {
        return availableDate;
    }
    public void setAvailableDate(Calendar availableDate) {
        this.availableDate = availableDate;
    }

    @DynamoDBAttribute(attributeName = DEPOSIT)
    public double getDeposit() {
        return deposit;
    }
    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    @DynamoDBAttribute(attributeName = PRIVATE_ROOM)
    public boolean isPrivateRoom() {
        return privateRoom;
    }
    public void setPrivateRoom(boolean privateRoom) {
        this.privateRoom = privateRoom;
    }

    @DynamoDBAttribute(attributeName = PRIVATE_BATH)
    public boolean isPrivateBath() {
        return privateBath;
    }
    public void setPrivateBath(boolean privateBath) {
        this.privateBath = privateBath;
    }

    @DynamoDBAttribute(attributeName = FURNISHED)
    public boolean isFurnished() {
        return furnished;
    }
    public void setFurnished(boolean furnished) {
        this.furnished = furnished;
    }

    @DynamoDBAttribute(attributeName = SMOKING_OK)
    public boolean isSmokingOK() {
        return smokingOK;
    }
    public void setSmokingOK(boolean smokingOK) {
        this.smokingOK = smokingOK;
    }

    @DynamoDBAttribute(attributeName = PETS_OK)
    public boolean isPetsOK() {
        return petsOK;
    }
    public void setPetsOK(boolean petsOK) {
        this.petsOK = petsOK;
    }

    @DynamoDBAttribute(attributeName = LAUNDRY_TYPE)
    public String getLaundryType() {
        return laundryType;
    }
    public void setLaundryType(String laundryType) {
        this.laundryType = laundryType;
    }

    @DynamoDBAttribute(attributeName = PARKING_TYPE)
    public String getParkingType() {
        return parkingType;
    }
    public void setParkingType(String parkingType) {
        this.parkingType = parkingType;
    }
}

