package com.kaoyaya.tongkai.http;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 请求拦截 响应
 */
public class RequestLoggerInterceptor implements Interceptor {

    public RequestLoggerInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Request.Builder builder = request.newBuilder();

        builder.addHeader("Uuid", "121221212121212");
        //请求信息
        return chain.proceed(builder.build());
    }

    public static RequestBody getRequestBody(HashMap<String, Object> hashMap) {
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(hashMap));
    }

}