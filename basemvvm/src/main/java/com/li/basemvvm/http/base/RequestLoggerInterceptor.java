package com.li.basemvvm.http.base;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.li.basemvvm.utils.TokenUtils;
import com.li.basemvvm.utils.Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import okhttp3.FormBody;
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

//        addHeader("App-Version", "1.8.25")
//        addHeader("User-Agent", "IOS/WEBVIEW")
//        addHeader("x-token", "xxxxxxxxxx")
        Request.Builder builder = request.newBuilder();


        String token = TokenUtils.getInstance().getToken();

        if (!TextUtils.isEmpty(token)) {
            builder.addHeader("x-token", token);
        }

        builder.addHeader("App-Version", "1.8.25");
        builder.addHeader("User-Agent", Utils.getUserAgent());

        RequestBody body = request.body();
        Log.d("request", new Gson().toJson(body) + request.url());
        if ("POST".equals(request.method()) && body instanceof FormBody) {
            FormBody formBody = (FormBody) body;
            //找出 是否有json 这个字符
            boolean hasJsonParams = false;
            for (int i = 0; i < formBody.size(); i++) {
                if ("json".equals(formBody.name(i))) {
                    hasJsonParams = true;
                    break;
                }
            }
            //如果有的话，就把post formData 该成 post json
            if (hasJsonParams) {
                LinkedHashMap<String, Object> hashMap = new LinkedHashMap<>();
                Log.e("test", new Gson().toJson(formBody));
                for (int i = 0; i < formBody.size(); i++) {
                    hashMap.put(formBody.encodedName(i), formBody.encodedValue(i));
                }
                builder.post(getRequestBody(hashMap));
            }
        }
        //请求信息
        return chain.proceed(builder.build());
    }

    public static RequestBody getRequestBody(HashMap<String, Object> hashMap) {
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(hashMap));
    }

}