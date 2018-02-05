package com.arian.distribution.modules.login;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arian.distribution.R;
import com.arian.distribution.base.BaseActivity;
import com.arian.distribution.navigate.Navigate;
import com.arian.distribution.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xiaoyi on 2018/2/5.
 */

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_reference)
    EditText etReference;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_confirm_password)
    EditText etConfirmPassword;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(toolbar, "用户注册");

        // 需要加入二维码扫码

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_scan:

//                Intent intentsa = new Intent(getContext(), VinHistoryActivity.class);
//                startActivity(intentsa);
                ToastUtil.show(getContext(), "启动扫码");

                return true;

            default:
                return super.onOptionsItemSelected(item);

        }


    }

    @OnClick(R.id.btn_register)
    public void onViewClicked() {
        // 推荐人
        String reference = etReference.getText().toString().trim();
        if (TextUtils.isEmpty(reference)) {
            ToastUtil.showShort(getContext(), "推荐人不能为空！");
            return;
        }

        // 用户名
        String userName = etUsername.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            ToastUtil.showShort(getContext(), "用户名不能为空！");
            return;
        }

        // 手机号
        String mobile = etMobile.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            ToastUtil.showShort(getContext(), "手机号不能为空！");
            return;
        }
        // 密码
        String password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            ToastUtil.showShort(getContext(), "用户密码不能为空！");
            return;
        }
        // 密码
        String confirmPassword = etConfirmPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            ToastUtil.showShort(getContext(), "确认密码不能为空！");
            return;
        }

        // 密码一致性
        if (!confirmPassword.equals(password)) {
            ToastUtil.showShort(getContext(), "密码与确认密码不一致，请重新输入！");
            return;
        }

        Navigate.startLoginActivity(getContext());

    }
}
