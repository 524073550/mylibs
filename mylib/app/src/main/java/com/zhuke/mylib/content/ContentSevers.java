package com.zhuke.mylib.content;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;


/**
 * Created by 15653 on 2018/3/6.
 */

public interface ContentSevers {

    @GET(ContentUrl.LOGINACTION)
    Observable<String> ini_loading(@QueryMap Map<String, String> map, @Query("sign") String sign);
}
