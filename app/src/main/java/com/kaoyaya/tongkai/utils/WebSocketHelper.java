package com.kaoyaya.tongkai.utils;

import android.util.Log;

import androidx.annotation.NonNull;

import com.dhh.websocket.Config;
import com.dhh.websocket.RxWebSocket;
import com.dhh.websocket.WebSocketSubscriber;
import com.google.gson.Gson;
import com.kaoyaya.tongkai.entity.LiveResponseInfo;
import com.kaoyaya.tongkai.http.RequestLoggerInterceptor;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.WebSocket;
import rx.Subscription;

public class WebSocketHelper {

    private static final String url = "ws://www.kuaixuezaixian.com/ws/v1";
//    private static final String url = "ws://192.168.118.131:8082/ws/v1";

    private WebSocketHelper() {
    }

    private static WebSocketHelper instance;


    public static WebSocketHelper getInstance() {
        if (instance == null) {
            instance = new WebSocketHelper();
        }
        return instance;
    }


    /**
     * 订阅 直播评分
     */

    public void subscribeLive() {
        subscribeLiveAction(true);
    }

    /**
     * 取消订阅 直播评分
     */
    public void unSubscribeLive() {
        subscribeLiveAction(false);
    }

    /***
     *  发送进入直播间事件
     */
    public void sendEnterLiveRoom(int liveId) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("op", "enter_live_room");
        data.put("args", new String[]{String.valueOf(liveId)});
        RxWebSocket.send(url, new Gson().toJson(data));
    }

    /**
     * 发送关闭评价弹窗事件
     */
    public void sendCloseLiveRatingPop() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("op", "close_live_rating_popup");
        data.put("args", new String[]{"live_rating_popup"});
        RxWebSocket.send(url, new Gson().toJson(data));
    }


    public Subscription start(final OnMessageListener onMessageListener) {
        return RxWebSocket.get(url)
                .subscribe(new WebSocketSubscriber() {
                    @Override
                    public void onOpen(@NonNull WebSocket webSocket) {
                        Log.e("test", "onOpen1:");
                    }

                    @Override
                    public void onMessage(@NonNull String text) {
                        Log.e("test", "返回数据:" + text);
                        LiveResponseInfo liveResponseInfo = new Gson().fromJson(text, LiveResponseInfo.class);
                        if(liveResponseInfo != null && onMessageListener != null){
                            onMessageListener.onMessage(liveResponseInfo.getOp());
                        }

                    }

                    @Override
                    protected void onReconnect() {
                        super.onReconnect();
                        Log.e("test","正在重新练车");
                    }
                });
    }

    /**
     * 取消订阅。
     */
    public void closeSocket(Subscription subscription) {
        //注销
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

    }

    private void subscribeLiveAction(boolean isSubscribe) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("op", isSubscribe ? "subscribe" : "unsubscribe");
        data.put("args", new String[]{"live_rating_popup", "live_rating_bubble"});
        RxWebSocket.send(url, new Gson().toJson(data));
    }


    private void init(){
        OkHttpClient client = new OkHttpClient.Builder()
                .pingInterval(20, TimeUnit.SECONDS) // 设置心跳间隔，这个是20秒检测一次
                .addInterceptor(new RequestLoggerInterceptor())
                .build();

        Config config = new Config.Builder()
                .setShowLog(true)           //show  log
                .setClient(client)   //if you want to set your okhttpClient
                .setShowLog(true, "test")
                .setReconnectInterval(2, TimeUnit.SECONDS)  //set reconnect interval
                .build();
        RxWebSocket.setConfig(config);
    }

    public interface OnMessageListener {
        void onMessage(String messageType);
    }

}
