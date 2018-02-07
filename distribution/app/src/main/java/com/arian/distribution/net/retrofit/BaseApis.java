package com.arian.distribution.net.retrofit;

import com.arian.distribution.BuildConfig;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by gushi on 2017/3/3.
 */

public interface BaseApis {


    String HOST = BuildConfig.BASE_URL;


    /**
     * 登录页面
     *
     * @param userName
     * @param password
     * @param clientType
     * @return
     */
    @FormUrlEncoded
    @POST("/uplus/interface/wms/user/login.do")
    Observable<Object> login(@Field("userName") String userName,
                             @Field("password") String password,
                             @Field("clientType") String clientType

    );


}
