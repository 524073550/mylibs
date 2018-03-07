package com.zhuke.mylib.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.zhuke.mylib.R;
import com.zhuke.mylib.utils.ActivityUtils;
import com.zhuke.mylib.utils.DisplayUtil;
import com.zhuke.mylib.utils.MyToast;
import com.zhuke.mylib.utils.StatusBarUtils;


/**
 * Created by 15653 on 2017/9/28.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected InputMethodManager inputMethodManager;
    public ImageView mBack;
    public RelativeLayout mRLTitle;
    public TextView mTitle;
    public ImageView rightIV;
    public FrameLayout mContainer;
    public ImageView mUnBindIv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
        setContentView(R.layout.activity_base);
        ActivityUtils.addActivity(this);
//        StatusBarUtils.setColor(this, getResources().getColor(R.color.titlebg));
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        initView();
        View view = addContent(getLayoutInflater(), mContainer);
        initContent(view);
        initTitle();
    }


    //添加布局内容
    protected abstract View addContent(LayoutInflater layoutInflater, FrameLayout container);

    //初始化内容布局
    protected abstract void initContent(View view);

    protected abstract void initTitle();


    public void showToast(String text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, DisplayUtil.getMobileHeight(getApplicationContext()) / 2);
        toast.show();
    }
    protected ProgressDialog progressBar;

    protected void showProgressBar(String message) {
        try {
            progressBar = new ProgressDialog(this);
            progressBar.setCanceledOnTouchOutside(false);
            progressBar.setMessage(message);
            progressBar.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void dismissProgressBar() {
        try {
            if (progressBar != null && progressBar.isShowing()) {
                progressBar.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.base_iv_back);
        mRLTitle = (RelativeLayout) findViewById(R.id.base_rl_titie);
        mTitle = (TextView) findViewById(R.id.base_tv_title);
        rightIV = (ImageView) findViewById(R.id.base_iv_right1);
        mUnBindIv = (ImageView) findViewById(R.id.base_iv_right2);
        mContainer = (FrameLayout) findViewById(R.id.base_fl_container);
    }

    /**
     * 设置标题栏是否显示
     *
     * @param isShow
     */
    public void setRLTitleVisibility(boolean isShow) {
        mRLTitle.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置标题内容
     * @param title
     */
    public void setTitle(String title){
        mTitle.setText(title == null ? "" : title);

    }

    /**
     *
     * @param isShow 是否显示
     * @param listener 点击监听
     */
    public void setLeftIV(boolean isShow,View.OnClickListener listener) {
        mBack.setVisibility(isShow ? View.VISIBLE : View.GONE);
        if (listener == null) {
            mBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else {
            mBack.setOnClickListener(listener);
        }
    }

    /**
     * 添加右边图标
     * @param resId
     * @param listener
     */
    protected void enableRightImage(int resId, View.OnClickListener listener) {
        rightIV.setImageResource(resId);
        rightIV.setOnClickListener(listener);
        rightIV.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        if (isFastClick()){
            return;
        }
    }



    private long mExitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            MyToast.show(this, "再按一次退出App");
            mExitTime = System.currentTimeMillis();
        } else {
            ActivityUtils.removeAllActivity();
            System.exit(0);
        }
    }


    private static long lastClickTime;

    /**
     * 防止点击频率
     * @return
     */
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        long result = time - lastClickTime;
        if (result < 500 && result > 0) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
