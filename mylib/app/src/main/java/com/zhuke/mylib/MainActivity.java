package com.zhuke.mylib;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhuke.mylib.net.manager.RetrofitManage;
import com.zhuke.mylib.net.util.ApiCallback;
import com.zhuke.mylib.net.util.SubscriberCallBack;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Map<String,Object> map = new HashMap<>();
        map.put("phone", "18550553875");
        map.put("pwd", "a123456");
        map.put("type", "B");
        map.put("platform", "Android");
        map.put("deviceno", Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID));
        Observable<JSONObject> observable = RetrofitManage.getInstance().getHttpServiceConnection().login(map);
         observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io()) //在io线程中处理网络请求
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new SubscriberCallBack<>(new ApiCallback<JSONObject>() {
                    @Override
                    public void onSuccess(JSONObject model) {

                    }

                    @Override
                    public void onFailure(int msg, String reason) {

                    }

                    @Override
                    public void onCompleted() {

                    }
                }));

    }
}
