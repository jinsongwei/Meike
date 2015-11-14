package com.meike.abc.meike.Model.Tables;


import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;
import com.meike.abc.meike.Model.Constants.TableName;
import com.meike.abc.meike.Model.Constants.TableType;
import com.meike.abc.meike.Model.Support.PostAttribute;

import java.util.List;


@DynamoDBTable(tableName = TableName.Other)
public class OtherItem extends PostItem {

    public static final String TABLE_NAME = "其他";

    @Override
    public String subTitle1() {
        return "";
    }

    @Override
    public String subTitle2() {
        return "";
    }

    @Override
    public String subTitle3() {
        return "";
    }

    @Override
    public String subTitle4() {
        return "";
    }

    @Override
    public TableType tableType() {
        return TableType.Other;
    }

    @Override
    public List<PostAttribute> attributeValueList() {
        return null;
    }

}
