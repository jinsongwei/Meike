package com.meike.abc.meike.Model.Tables;



import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;
import com.meike.abc.meike.Model.Constants.TableName;
import com.meike.abc.meike.Model.Constants.TableType;
import com.meike.abc.meike.Model.Support.PostAttribute;

import java.util.ArrayList;
import java.util.List;

@DynamoDBTable(tableName = TableName.Auto)
public class AutoItem extends PostItem {

    public static final String TABLE_NAME = "汽车";

    private static final String MAKE = "品牌";
    private static final String MODEL = "型号";
    private static final String BODY_SHAPE = "车型";
    private static final String NEW_CAR = "新旧";
    private static final String YEAR = "年份";
    private static final String MILEAGE = "里程";
    private static final String TITLE_TYPE = "事故历史";
    private static final String COLOR = "颜色";
    private static final String NUM_OF_DOORS = "车门";
    private static final String NUM_OF_SEATS = "车座";
    private static final String NUM_OF_CYLINDERS = "发动机";
    private static final String HORSE_POWER = "马力";
    private static final String TRANSMISSION = "变速";
    private static final String WHEEL_DRIVE = "驱动";
    private static final String MPG = "耗油量";

    private String make;
    private String model;
    private String bodyShape;
    private String newCar; // new / used/ certified
    private int year;
    private int mileage;
    private String titleType;
    private String color;
    private int numOfDoors;
    private int numOfSeats;
    private int numOfCylinders;
    private int horsePower;
    private String transmission;
    private String wheelDrive; //front wheel drive?
    private int mpg;


    @Override
    public String subTitle1() {
        return make;
    }

    @Override
    public String subTitle2() {
        return model;
    }

    @Override
    public String subTitle3() {
        return year + "年";
    }

    @Override
    public String subTitle4() {
        return mileage / 10000 + "万麦";
    }

    @Override
    public TableType tableType() {
        return TableType.Auto;
    }

    @Override
    public List<PostAttribute> attributeValueList() {
        List<PostAttribute> list = new ArrayList<>();
        list.add(new PostAttribute("品牌", make));
        list.add(new PostAttribute("车型", model));
        list.add(new PostAttribute("车身", bodyShape));
        list.add(new PostAttribute("新车/二手车", newCar));
        list.add(new PostAttribute("年份", "" + year));
        list.add(new PostAttribute("里程", subTitle4()));
        list.add(new PostAttribute("历史", titleType));
        list.add(new PostAttribute("颜色", color));
        list.add(new PostAttribute("车门", "" + numOfDoors));
        list.add(new PostAttribute("车座", "" + numOfSeats));
        list.add(new PostAttribute("发动机", "" + numOfCylinders + "V"));
        list.add(new PostAttribute("马力", "" + horsePower));
        list.add(new PostAttribute("变速", transmission));
        list.add(new PostAttribute("驱动", wheelDrive));
        list.add(new PostAttribute("耗油量", "" + mpg));
        return list;
    }

    @DynamoDBAttribute(attributeName = MAKE)
    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }

    @DynamoDBAttribute(attributeName = MODEL)
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    @DynamoDBAttribute(attributeName = BODY_SHAPE)
    public String getBodyShape() {
        return bodyShape;
    }
    public void setBodyShape(String bodyShape) {
        this.bodyShape = bodyShape;
    }

    @DynamoDBAttribute(attributeName = NEW_CAR)
    public String getNewCar() {
        return newCar;
    }
    public void setNewCar(String newCar) {
        this.newCar = newCar;
    }

    @DynamoDBAttribute(attributeName = YEAR)
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    @DynamoDBAttribute(attributeName = MILEAGE)
    public int getMileage() {
        return mileage;
    }
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @DynamoDBAttribute(attributeName = TITLE_TYPE)
    public String getTitleType() {
        return titleType;
    }
    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }

    @DynamoDBAttribute(attributeName = COLOR)
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    @DynamoDBAttribute(attributeName = NUM_OF_DOORS)
    public int getNumOfDoors() {
        return numOfDoors;
    }
    public void setNumOfDoors(int numOfDoors) {
        this.numOfDoors = numOfDoors;
    }

    @DynamoDBAttribute(attributeName = NUM_OF_SEATS)
    public int getNumOfSeats() {
        return numOfSeats;
    }
    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    @DynamoDBAttribute(attributeName = NUM_OF_CYLINDERS)
    public int getNumOfCylinders() {
        return numOfCylinders;
    }
    public void setNumOfCylinders(int numOfCylinders) {
        this.numOfCylinders = numOfCylinders;
    }

    @DynamoDBAttribute(attributeName = HORSE_POWER)
    public int getHorsePower() {
        return horsePower;
    }
    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    @DynamoDBAttribute(attributeName = TRANSMISSION)
    public String getTransmission() {
        return transmission;
    }
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    @DynamoDBAttribute(attributeName = WHEEL_DRIVE)
    public String getWheelDrive() {
        return wheelDrive;
    }
    public void setWheelDrive(String wheelDrive) {
        this.wheelDrive = wheelDrive;
    }

    @DynamoDBAttribute(attributeName = MPG)
    public int getMpg() {
        return mpg;
    }
    public void setMpg(int mpg) {
        this.mpg = mpg;
    }
}
