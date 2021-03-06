package com.li.basemvvm.http.base;

import android.util.Log;

import com.google.gson.Gson;
import com.hdl.elog.ELog;
import com.li.basemvvm.bus.Messenger;
import com.li.basemvvm.http.logging.Printer;
import com.li.basemvvm.utils.TokenUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ResponseLogInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Response response = chain.proceed(request);
        ResponseBody responseBody = response.body();
        MediaType contentType = responseBody.contentType();
        String subtype = null;
        ResponseBody body;

        if (contentType != null) {
            subtype = contentType.subtype();
        }
        if (subtype != null && (subtype.contains("json")
                || subtype.contains("xml")
                || subtype.contains("plain")
                || subtype.contains("html"))) {
            String string = responseBody.string();
            String bodyString = Printer.getJsonString(string);
            body = ResponseBody.create(contentType, bodyString);

            Log.d("response", bodyString);
            if(response.code() == 200){
                BaseResponse baseResponse = new Gson().fromJson(bodyString, BaseResponse.class);
                if(baseResponse.getCode() == 401){
                    ELog.e("test","清除cookie ，退出登录");
                    TokenUtils.getInstance().clearToken();


                    //退出登录。发出。退出登录通知
                    Messenger.getDefault().sendNoMsg("login");
                }
            }

        } else {
            return response;
        }
        return response.newBuilder().body(body).build();
    }
}
