package com.kaoyaya.tongkai.utils;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {


    public static TimeUtils instance;

    private TimeUtils() {

    }

    public static TimeUtils getInstance() {
        if (instance == null) {
            instance = new TimeUtils();
        }
        return instance;
    }


    public int getTime(String startTime) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parse = sdf.parse(startTime);
            Date now = new Date();
            return (int) ((parse.getTime() - now.getTime()) / 1000);
        } catch (Exception e) {
            return -1;
        }
    }


    public String getFormatTimeStr(String startTime) {
        int time = getTime(startTime);
        if (time > 0) {
            int second = time % 60;
            int minute = time / 60 % 60;
            int hour = time / 60 / 60;
            return (hour >= 10 ? hour : "0" + hour) + "小时" + (minute >= 10 ? minute : "0" + minute) + "分" + (second >= 10 ? second : "0" + second) + "秒";
        }
        return "00小时00分00秒";
    }


    public String formatTime(String startTime) {
        return formatTime(startTime,"HH:mm");
    }

    public String formatTime(String startTime,String dateFormat) {
        if (TextUtils.isEmpty(startTime)) {
            return "";
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parse = sdf.parse(startTime);
            sdf = new SimpleDateFormat(dateFormat);
            return sdf.format(parse);
        } catch (Exception e) {
            return startTime;
        }
    }
}
