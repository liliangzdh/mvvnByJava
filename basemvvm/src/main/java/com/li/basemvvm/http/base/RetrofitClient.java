package com.li.basemvvm.http.base;

import android.text.TextUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String baseUrl = "http://www.kuaixuezai.com/";
    private static Retrofit retrofit;

    private static OkHttpClient okHttpClient;
    //超时时间
    private static final int DEFAULT_TIMEOUT = 20;
    //缓存时间
    private static final int CACHE_TIMEOUT = 10 * 1024 * 1024;

    private static class SingletonHolder {
        private static RetrofitClient INSTANCE = new RetrofitClient();
    }

    public static RetrofitClient getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private RetrofitClient() {
        this(baseUrl);
    }

    private RetrofitClient(String url) {
        if (TextUtils.isEmpty(url)) {
            url = baseUrl;
        }

        okHttpClient = new OkHttpClient.Builder().
                addInterceptor(new RequestLoggerInterceptor()).
                addInterceptor(new ResponseLogInterceptor()).
                writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).
                connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).
                connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS)).
                build();

//        okHttpClient = new OkHttpClient.Builder().
//                addInterceptor(new LoggingInterceptor.Builder().build()).
//                writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).
//                connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).
//                connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS)).
//                build();


        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    /**
     * create you ApiService
     * Create an implementation of the API endpoints defined by the {@code service} interface.
     */
    public <T> T create(final Class<T> service) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        return retrofit.create(service);
    }

    /**
     * /**
     * execute your customer API
     * For example:
     * MyApiService service =
     * RetrofitClient.getInstance(MainActivity.this).create(MyApiService.class);
     * <p>
     * RetrofitClient.getInstance(MainActivity.this)
     * .execute(service.lgon("name", "password"), subscriber)
     * * @param subscriber
     */

    public static <T> T execute(Observable<T> observable, Observer<T> subscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

        return null;
    }
}
