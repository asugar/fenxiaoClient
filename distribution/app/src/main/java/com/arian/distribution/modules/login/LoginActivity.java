package com.arian.distribution.modules.login;

import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;

import com.arian.distribution.R;
import com.arian.distribution.base.BaseActivity;
import com.arian.distribution.navigate.Navigate;
import com.arian.distribution.utils.Constant;
import com.arian.distribution.utils.SpUtil;
import com.arian.distribution.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xiaoyi on 2018/2/5.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_login_name)
    EditText etLoginName;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {

        String username = SpUtil.getString(getContext(), Constant.USER_NAME, "");
        if (!TextUtils.isEmpty(username)) {
            etLoginName.setText(username);
        }

        String password = SpUtil.getString(getContext(), Constant.USER_PASSWORD, "");
        if (!TextUtils.isEmpty(password)) {
            etLoginPassword.setText(password);
        }

    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String loginName = etLoginName.getText().toString().trim();
                String loginPassWord = etLoginPassword.getText().toString().trim();

                if (TextUtils.isEmpty(loginName)) {

                    ToastUtil.showShort(getContext(), "用户名不能为空！");
                    etLoginName.startAnimation(getShakeAnimation(3));
                    return;
                }
                if (TextUtils.isEmpty(loginPassWord)) {

                    ToastUtil.showShort(getContext(), "用户密码不能为空！");
                    etLoginPassword.startAnimation(getShakeAnimation(3));
                    return;
                }

                getDataforNet(loginName, loginPassWord);
                break;
            case R.id.btn_register:
                Navigate.startRegisterActivity(getContext());
                break;
        }
    }


    private void getDataforNet(String loginName, String loginPassword) {
        Navigate.startMainActivity(getContext());

//        if (!AppInfo.checkInternet(getContext())) {
//            ToastUtil.show(getContext(), R.string.network_is_not_connected);
//            return;
//        }
//
//
//        startLoading();
//
//        String clientType = "android";
//        Observable<LogCallBean> login = RetrofitHelper.getBaseApis().login(loginName, loginPassWord, clientType);
//
//        login.subscribeOn(Schedulers.io())
//                .compose(this.<LogCallBean>bindToLifecycle())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<LogCallBean>() {
//                    @Override
//                    public void onSubscribe(Disposable disposable) {
//
//                    }
//
//                    @Override
//                    public void onNext(LogCallBean logCallBean) {
//
//
//                        stopLoading();
//
//                        Log.e(TAG, "onNext" + logCallBean.getMessage());
//
//
//                        String code = logCallBean.getCode();
//
//                        String message = logCallBean.getMessage();
//
//
//                        if ("000000".equals(code)) {
//
//
//                            /**　存储登录的  ｊｓｏｎ　　数据　供免登陆使用　**/
//
//                            LogCallBean.ResultBean result = logCallBean.getResult();
//
//
//                            int companyId = result.getCompanyId();
//
//                            SpUtil.putString(getContext(), Constant.USERNAME, loginName);
//                            SpUtil.putString(getContext(), Constant.PASSWORLD, loginPassWord);
//                            SpUtil.putString(getContext(), Constant.COMPANY_ID, companyId + "");
//
//
////                            SpUtil.putString(getContext(), Constant.PHONENUMBER, result.getUserAccount());
////                            SpUtil.putString(getContext(), Constant.USERNAME, loginName);
////                            SpUtil.putString(getContext(), Constant.PASSWORD, loginPassWord);
////                            SpUtil.putString(getContext(), Constant.CITYID, result.getCityId() + "");
////                            SpUtil.putString(getContext(), Constant.COMPANYID, result.getCompanyId() + "");
////                            SpUtil.putString(getContext(), Constant.IM_USERNAME, result.getImUserName() + "");//环信IM的userName
////                            SpUtil.putString(getContext(), Constant.STOREID, result.getStoreId() + "");
//
//                            Intent intent = new Intent(getContext(), HomeActivity.class);
//                            startActivity(intent);
//                            finish();
//
//
//                        } else {
//
//
//                            ToastUtil.showShort(getContext(), message);
//
//                        }
//
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//                        stopLoading();
//
//                        Log.e(TAG, "onError" + throwable.getMessage());
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        stopLoading();
//
//                        Log.e(TAG, "onCompleted");
//
//                    }
//                });


    }


    /**
     * 晃动动画
     *
     * @param counts 半秒钟晃动多少下
     * @return
     */
    public Animation getShakeAnimation(int counts) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(500);
        return translateAnimation;
    }


}
