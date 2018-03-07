package com.zhuke.mylib.net.util;

import android.util.Log;

import com.google.gson.Gson;
import com.zhuke.mylib.utils.DesUtils;
import com.zhuke.mylib.utils.EncryptExcption;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Created by pc on 2016/8/25.
 */
public class JsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    public JsonRequestBodyConverter() {

    }

    public RequestBody convert(T value) throws IOException {
        String encrypt = null;
        try {
            encrypt = DesUtils.encrypt(value.toString());
        } catch (EncryptExcption encryptExcption) {
            encryptExcption.printStackTrace();
        }
        ApiData apiData = new ApiData();
        apiData.setData(encrypt);
        String postBody = new Gson().toJson(apiData); //对象转化成json
        Log.i("xiaozhang", "转化后的数据：" + postBody);

        return RequestBody.create(MEDIA_TYPE, postBody);
    }
}
