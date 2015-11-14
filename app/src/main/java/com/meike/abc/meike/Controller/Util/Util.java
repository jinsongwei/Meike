package com.meike.abc.meike.Controller.Util;

import com.meike.abc.meike.Model.Constants.TableType;
import com.meike.abc.meike.Model.Tables.AutoItem;
import com.meike.abc.meike.Model.Tables.HousingItem;
import com.meike.abc.meike.Model.Tables.JobsItem;
import com.meike.abc.meike.Model.Tables.OtherItem;
import com.meike.abc.meike.Model.Tables.PostItem;
import com.meike.abc.meike.Model.Tables.SaleItem;

public class Util {

    public static PostItem getItemObject(TableType type) {
        switch (type) {
            case Housing:
                return new HousingItem();
            case Auto:
                return new AutoItem();
            case Jobs:
                return new JobsItem();
            case Sale:
                return new SaleItem();
            case Other:
                return new OtherItem();
        }
        return null;
    }

    public static PostItem getItemObject(String type) {
        if (type.equals(TableType.Housing.getName())) {
            return new HousingItem();
        }
        if (type.equals(TableType.Auto.getName())) {
            return new AutoItem();
        }
        if (type.equals(TableType.Jobs.getName())) {
            return new JobsItem();
        }
        if (type.equals(TableType.Sale.getName())) {
            return new SaleItem();
        }
        if (type.equals(TableType.Other.getName())) {
            return new OtherItem();
        }
        return null;
    }
}
