package com.meike.abc.meike.Model.Util;

import android.location.Address;

import java.util.Calendar;

public class Translator {

    public static String priceToString(double price) {
        if (price % 1 == 0) {
            return String.format("$%.0f", price);
        }

        return String.format("$%.2f", price);
    }

    public static String doubleToString(double num) {
        if (num % 1 == 0) {
            return String.format("%.0f", num);
        }

        return String.format("%.1f", num);

    }

    public static String booleanToString(boolean bool) {
        if (bool) {
            return "是";
        }
        return "否";
    }

    public static String postTimeToString(Calendar postTime) {
        Calendar now = Calendar.getInstance();

        if (now.get(Calendar.YEAR) != postTime.get(Calendar.YEAR)) {
            return String.format("%d年%d月",
                    postTime.get(Calendar.YEAR),
                    postTime.get(Calendar.MONTH));
        }
        if (now.get(Calendar.MONTH) != postTime.get(Calendar.MONTH) ||
                now.get(Calendar.DATE) != postTime.get(Calendar.DATE)) {
            return String.format("%d月%d日",
                    postTime.get(Calendar.MONTH),
                    postTime.get(Calendar.DATE));

        }

        return String.format("%d:%2d", postTime.get(Calendar.HOUR),
                postTime.get(Calendar.MINUTE));

    }

//    public static String dateToString(Calendar date) { String str = "";
//
//        if (date.isSet(Calendar.YEAR))
//            str += date.get(Calendar.YEAR) + "年";
//        if (date.isSet(Calendar.MONTH))
//            str += date.get(Calendar.MONTH) + "月";
//        if (date.isSet(Calendar.DATE))
//            str += date.get(Calendar.DATE) + "日";
//
//        if (date.isSet(Calendar.AM_PM)) {
//            if (date.get(Calendar.AM_PM) == 1)
//                str += "  下午";
//            else
//                str += "  上午";
//        }
//        if (date.isSet(Calendar.HOUR))
//            str += " " + date.get(Calendar.HOUR) + "时";
//        if (date.isSet(Calendar.MINUTE))
//            str += String.format(":%02d分", date.get(Calendar.MINUTE));
//
//        return str;
//    }

    public static String addressToString(Address address) {
        if (address == null) {
            return "";
        }

        String str = String.format("%s, %s %s, %s %s",
                address.getAddressLine(0),
                address.getAddressLine(1),
                address.getLocality(),
                address.getAdminArea(),
                address.getPostalCode());

        str = str.replace("null", "");
        return str;
    }
}
