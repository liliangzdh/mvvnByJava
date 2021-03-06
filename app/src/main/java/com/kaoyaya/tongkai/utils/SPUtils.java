package com.kaoyaya.tongkai.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.kaoyaya.tongkai.entity.ExamInfo;
import com.kaoyaya.tongkai.entity.StudyResourceItem;
import com.li.basemvvm.CommonApplication;

public class SPUtils {

    private static SPUtils spUtils;

    private static SharedPreferences sharedPreferences;


    private SPUtils() {
    }

    public static SPUtils getInstance() {
        if (spUtils == null) {
            spUtils = new SPUtils();
            Context context = CommonApplication.getAppContext();
            sharedPreferences = context.getSharedPreferences("kyySp", context.MODE_PRIVATE);
        }
        return spUtils;
    }

    private final String Token = "X-Token";

    public void saveToken(String token) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(Token, token);
        edit.apply();
    }

    public String getToken() {
        return sharedPreferences.getString(Token, "");
    }


    public void removeToken() {
        removeByKey(Token);
    }

    private void removeByKey(String name) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(name);
        edit.apply();
    }

    public void saveString(String key, String str) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key, str);
        edit.apply();
    }

    public String getByKey(String key) {
        return sharedPreferences.getString(key, "");
    }


    private final String ExamInfoKey = "ExamInfo";

    public void saveExamInfo(ExamInfo info) {
        saveString(ExamInfoKey, new Gson().toJson(info));
    }

    public ExamInfo getExamInfo() {
        return getObject(ExamInfoKey,ExamInfo.class);
    }


    private <T> T getObject(String key,Class<T> classOfT) {
        String byKey = getByKey(key);
        if (TextUtils.isEmpty(byKey)) {
            return null;
        }
        return new Gson().fromJson(byKey, classOfT);
    }

    public boolean isEmptyExamInfo() {
        ExamInfo examInfo = getExamInfo();
        if (examInfo == null || TextUtils.isEmpty(examInfo.getName())) {
            return true;
        }
        return false;
    }

    private final String StudyResourceItemKey = "StudyResourceItem";

    public void saveStudyItem(StudyResourceItem item) {
        saveString(StudyResourceItemKey, new Gson().toJson(item));
    }

    public StudyResourceItem getStudyResourceItem() {
        return getObject(StudyResourceItemKey,StudyResourceItem.class);
    }

    public void clearStudyItem() {
        removeByKey(StudyResourceItemKey);
    }
}
