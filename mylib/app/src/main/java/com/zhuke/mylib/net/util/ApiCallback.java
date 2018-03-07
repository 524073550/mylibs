package com.zhuke.mylib.net.util;

/**
 * Created by pc on 2016/8/31.
 */
public interface ApiCallback<T> {
    void onSuccess(T model);

    void onFailure(int msg, String reason);

    void onCompleted();
}
