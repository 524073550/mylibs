package com.zhuke.mylib.net.service;

import com.zhuke.mylib.content.ContentUrl;

import org.json.JSONObject;

import java.util.Map;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by pc on 2016/8/23.
 */
public interface HttpService {

    //检查版本更新接口
    @FormUrlEncoded
    @POST(ContentUrl.LOGINACTION)
    Observable<JSONObject> checkVersion(@FieldMap() Map<String, String> maps);

    //用户登录接口
    @FormUrlEncoded
    @POST(ContentUrl.LOGINACTION)
    Observable<JSONObject> login(@FieldMap() Map<String, Object> maps);


}
