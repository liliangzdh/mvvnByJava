// MessageReceive.aidl
package com.kaoyaya.tongkai;

import com.kaoyaya.tongkai.data.MessageModel;

interface MessageReceive {

    void onMessageReceived(in MessageModel receivedMessage);
}
