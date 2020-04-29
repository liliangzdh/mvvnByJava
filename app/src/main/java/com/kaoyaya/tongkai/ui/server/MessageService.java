package com.kaoyaya.tongkai.ui.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.kaoyaya.tongkai.MessageReceive;
import com.kaoyaya.tongkai.MessageSender;
import com.kaoyaya.tongkai.data.MessageModel;

import java.util.concurrent.atomic.AtomicBoolean;

public class MessageService extends Service {

    private static final String TAG = "test";

    private AtomicBoolean serverStop = new AtomicBoolean();

    // 专门用来 管理 多进程 回调接口。
    private RemoteCallbackList<MessageReceive> list = new RemoteCallbackList<>();


    @Override
    public void onCreate() {
        super.onCreate();
        // 模拟  连接
        new Thread(new TaskRun()).start();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messageSender;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        serverStop.set(true);
    }

    IBinder messageSender = new MessageSender.Stub() {
        @Override
        public void sendMessage(MessageModel message) throws RemoteException {
            Log.e(TAG, message.toString());
        }

        @Override
        public void registerReceiveListener(MessageReceive messageReceive) throws RemoteException {
            list.register(messageReceive);
        }

        @Override
        public void unRegisterReceiveListener(MessageReceive messageReceive) throws RemoteException {
            list.unregister(messageReceive);
        }
    };


    private class TaskRun implements Runnable {

        @Override
        public void run() {
            while (!serverStop.get()) {
                try {
                    Thread.sleep(5 * 1000);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                MessageModel messageModel = new MessageModel();

                messageModel.setFrom("Service");
                messageModel.setTo("client");
                messageModel.setContent(String.valueOf(System.currentTimeMillis()));

                Log.e(TAG, messageModel.getContent());

                int count = list.beginBroadcast();
                for (int i = 0; i < count; i++) {
                    MessageReceive broadcastItem = list.getBroadcastItem(i);
                    try {
                        broadcastItem.onMessageReceived(messageModel);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                    list.finishBroadcast();
                }
            }
        }

    }
}