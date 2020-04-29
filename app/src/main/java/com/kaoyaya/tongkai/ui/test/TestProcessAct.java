package com.kaoyaya.tongkai.ui.test;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kaoyaya.tongkai.MessageReceive;
import com.kaoyaya.tongkai.MessageSender;
import com.kaoyaya.tongkai.R;
import com.kaoyaya.tongkai.data.MessageModel;
import com.kaoyaya.tongkai.ui.server.MessageService;

public class TestProcessAct extends AppCompatActivity {

    MessageSender messageSender;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_test_process);

        setupService();
    }

    private void setupService() {
        Intent intent = new Intent(this, MessageService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        startService(intent);
    }


    IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient(){

        @Override
        public void binderDied() {
            Log.e("test","binderDied");

            if(messageSender != null ){
                messageSender.asBinder().unlinkToDeath(this,0);
                messageSender = null;
            }

            // 重连 操作。
            setupService();
        }
    };

    ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 使用 asInterface 方法 获取   AIDL 对应 的 操作接口。
            messageSender = MessageSender.Stub.asInterface(service);

            // 生产 消息 实体 对象
            MessageModel messageModel = new MessageModel();
            messageModel.setFrom("From Act");
            messageModel.setTo("to Service");
            messageModel.setContent("hello process");

            // 调用 远程 service 的 sendMessage 方法。并传递 消息实体 对象
            try {
                messageSender.sendMessage(messageModel);
                messageSender.registerReceiveListener(messageReceive);

                // 设置 Binder 死亡监听。
                messageSender.asBinder().linkToDeath(deathRecipient,0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    MessageReceive messageReceive = new MessageReceive.Stub(){

        @Override
        public void onMessageReceived(MessageModel receivedMessage) throws RemoteException {
            Log.e("test","  onMessageReceived: "+receivedMessage.toString());
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(messageSender != null && messageSender.asBinder().isBinderAlive()){
            try {
                messageSender.unRegisterReceiveListener(messageReceive);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        unbindService(serviceConnection);

    }
}
