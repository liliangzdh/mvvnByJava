package com.kaoyaya.tongkai;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.hdl.elog.ELog;
import com.li.basemvvm.CommonApplication;

public class MainApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        CommonApplication.init(this);
        ELog.setIsDebug(BuildConfig.DEBUG);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static Context getAppContext() {
        return getAppContext();
    }
}
