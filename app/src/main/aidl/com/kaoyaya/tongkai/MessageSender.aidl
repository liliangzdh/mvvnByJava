// MessageSender.aidl
package com.kaoyaya.tongkai;


import com.kaoyaya.tongkai.data.MessageModel;
import com.kaoyaya.tongkai.MessageReceive;

interface MessageSender {

    void sendMessage(in MessageModel message);

    void registerReceiveListener(MessageReceive messageReceive);

    void unRegisterReceiveListener(MessageReceive messageReceive);
}
