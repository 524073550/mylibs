package com.zhuke.mylib.base;

import com.zhuke.mylib.content.ContentSevers;
import com.zhuke.mylib.httputils.HttpHelp;

/**
 * Created by 15653 on 2018/3/6.
 */

public class BasePresenter {

    private final ContentSevers mApi;

    public BasePresenter() {
        mApi = HttpHelp.getApi();
    }

//    public void setLoadParameter

}
