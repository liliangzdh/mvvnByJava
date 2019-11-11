package com.kaoyaya.tongkai;

import android.app.Application;
import android.content.Context;

import com.hdl.elog.ELog;
import com.li.basemvvm.CommonApplication;

public class MainApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        CommonApplication.init(this);
        ELog.setIsDebug(BuildConfig.DEBUG);
    }


    public static Context getAppContext() {
        return getAppContext();
    }
}
