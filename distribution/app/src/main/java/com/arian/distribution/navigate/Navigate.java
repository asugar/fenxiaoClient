package com.arian.distribution.navigate;

import android.app.Activity;
import android.content.Intent;

import com.arian.distribution.modules.home.activity.MainActivity;
import com.arian.distribution.modules.login.LoginActivity;
import com.arian.distribution.modules.login.RegisterActivity;

public class Navigate {


    /**
     * 启动主页
     *
     * @param context
     */
    public static void startMainActivity(Activity context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    /**
     * 启动注册页面
     *
     * @param context
     */
    public static void startRegisterActivity(Activity context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转登录页面
     *
     * @param context
     */
    public static void startLoginActivity(Activity context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }


}
