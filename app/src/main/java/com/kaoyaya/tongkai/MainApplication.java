package com.kaoyaya.tongkai;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.multidex.MultiDex;

import com.hdl.elog.ELog;
import com.li.basemvvm.CommonApplication;

import java.util.List;

public class MainApplication extends Application {


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        CommonApplication.init(this);
        ELog.setIsDebug(BuildConfig.DEBUG);
        Log.e("test", "process name:" + getProName());
    }


    public static Context getAppContext() {
        return getAppContext();
    }


    //取得进程名
    public String getProName() {
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        if (am == null) {
            return "";
        }
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return "";
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == android.os.Process.myPid()) {
                return procInfo.processName;
            }
        }
        return "";
    }
}
