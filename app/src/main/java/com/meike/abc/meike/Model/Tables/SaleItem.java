package com.meike.abc.meike.Model.Tables;

import android.support.v4.util.Pair;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;
import com.meike.abc.meike.Model.Constants.TableName;
import com.meike.abc.meike.Model.Constants.TableType;
import com.meike.abc.meike.Model.Support.PostAttribute;

import java.util.ArrayList;
import java.util.List;

@DynamoDBTable(tableName = TableName.Sale)
public class SaleItem extends PostItem {

    private static final String MAIN_CATEGORY = "主类别";
    private static final String SUB_CATEGORY = "次类别";

    private String mainCategory;
    private String subCategory;


    @DynamoDBAttribute(attributeName = MAIN_CATEGORY)
    public String getMainCategory() {
        return mainCategory;
    }
    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    @DynamoDBAttribute(attributeName = SUB_CATEGORY)
    public String getSubCategory() {
        return subCategory;
    }
    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }


    @Override
    public String subTitle1() {
        return mainCategory;
    }

    @Override
    public String subTitle2() {
        return subCategory;
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
        return TableType.Sale;
    }

    @Override
    public List<PostAttribute> attributeValueList() {
        List<PostAttribute> list = new ArrayList<>();
        list.add(new PostAttribute("主类别", mainCategory));
        list.add(new PostAttribute("次类别", subCategory));
        return list;
    }

}
