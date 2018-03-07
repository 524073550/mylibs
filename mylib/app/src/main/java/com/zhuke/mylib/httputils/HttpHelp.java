package com.zhuke.mylib.httputils;

import android.util.Log;

import com.zhuke.mylib.content.ContentSevers;
import com.zhuke.mylib.content.ContentUrl;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * Created by 15653 on 2018/3/6.
 */

public class HttpHelp {
    private static ContentSevers service;

    /**
     * 获得接口
     *
     * @param * @return
     */
    public static ContentSevers getApi() {
        if (service == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient.build())
                    .baseUrl(ContentUrl.baseUrl)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            service = retrofit.create(ContentSevers.class);
        }
        return service;
    }

    public static OkHttpClient.Builder okHttpClient = new OkHttpClient().newBuilder()
            .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger(){
                @Override
                public void log(String message) {
                    Log.i("RetrofitLog","retrofitBack = "+message);
                }
            }).setLevel(HttpLoggingInterceptor.Level.HEADERS ))
            .connectTimeout(2000, TimeUnit.SECONDS);
}
