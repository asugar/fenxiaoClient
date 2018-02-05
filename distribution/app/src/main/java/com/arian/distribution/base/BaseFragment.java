package com.arian.distribution.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;
import com.arian.distribution.view.MyProgressDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xiaoyi on 2018/1/22.
 */

public abstract class BaseFragment extends RxFragment {
    private Activity context;

    protected View mView;

    private Unbinder unbinder;
    private MyProgressDialog progressDialog;

    @Override
    public Activity getContext() {
        return context;
    }

    public View getView() {
        return mView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = activity;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        return mView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initEventAndData();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }


    protected abstract int getLayoutId();

    protected abstract void initEventAndData();


    /**
     * 启动loading
     */
    public void startLoading(String content) {
        if (progressDialog == null) {
            progressDialog = MyProgressDialog.createDialog(context);
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


    // 兼容先前fragement，后期整理删除
    protected Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            onHandlerThread(msg);
        }
    };

    /**
     * dispatch mHandler's handleMessage method
     *
     * @param msg
     */
    protected void onHandlerThread(Message msg) {
        switch (msg.what) {
            case 1:
                startLoading("");
                break;
            case 2:
                stopLoading();
                break;
            default:
                break;
        }

    }

}
