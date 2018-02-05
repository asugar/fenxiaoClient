package com.arian.distribution.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.arian.distribution.utils.ActivityManager;
import com.arian.distribution.view.MyProgressDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xiaoyi on 2018/1/22.
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    protected Activity mContext;
    protected Bundle mSavedInstanceState;
    private Unbinder mUnBinder;
    private MyProgressDialog progressDialog;

    public Activity getContext() {
        return mContext;
    }

    //为了给地图使用
    public Bundle getSavedInstanceState() {
        return mSavedInstanceState;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置锁定坚屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        PushAgent.getInstance(context).onAppStart();
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        mSavedInstanceState = savedInstanceState;

        // 每打开一个新页面都加栈里
        ActivityManager.getInstance().addActivity(this);

        initEventAndData();
    }

    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    protected void onDestroy() {
        ActivityManager.getInstance().finishActivity(this);
        super.onDestroy();

        mUnBinder.unbind();
    }

    protected abstract int getLayout();

    protected abstract void initEventAndData();


    /**
     * 启动loading
     */
    public void startLoading() {
        if (progressDialog == null) {
            progressDialog = MyProgressDialog.createDialog(this);
        }
        progressDialog.show();
    }

    //这个方法dialog不可取消
    public void startProgressDialog(String content, boolean isCancelDialog) {
        if (progressDialog == null) {
            progressDialog = MyProgressDialog.createDialog(this);
            progressDialog.setCanceledOnTouchOutside(false);//取消dialog点击事件
            progressDialog.setMessage(content);
        }

        progressDialog.show();
    }


    /**
     * 停止loading
     */
    public void stopLoading() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }


}
