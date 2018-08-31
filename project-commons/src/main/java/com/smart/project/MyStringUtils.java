package com.smart.project;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyStringUtils {

    //yyyy-MM-dd HH:mm:ss 把日期转为字符串
    public static String formatDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        }
        if (str.trim().equals("")) {
            return true;
        }
        return false;
    }

}
