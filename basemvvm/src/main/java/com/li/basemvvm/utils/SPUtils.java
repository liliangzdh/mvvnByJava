package com.li.basemvvm.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.li.basemvvm.CommonApplication;


public class SPUtils {


    private static SPUtils spUtils;
    private static SharedPreferences preferences;

    private final static String TokenKey = "KaoYaYaToken";

    private SPUtils() {
    }

    public static SPUtils getInstance() {
        if (spUtils == null) {
            spUtils = new SPUtils();
            Context appContext = CommonApplication.getAppContext();
            preferences = appContext.getSharedPreferences("KaoYaYa", Context.MODE_PRIVATE);
        }
        return spUtils;
    }

    /**
     * 保存 token
     */
    public void saveToken(String token) {
        if (TextUtils.isEmpty(token)) {
            return;
        }
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(TokenKey, token);
        edit.apply();
    }

    /**
     * 获取登录token
     */
    public String getToken() {
        return preferences.getString(TokenKey, "");
    }

    /**
     * 清除token信息
     */
    public void clearToken(){
        SharedPreferences.Editor edit = preferences.edit();
        edit.remove(TokenKey);
        edit.apply();
    }
}
