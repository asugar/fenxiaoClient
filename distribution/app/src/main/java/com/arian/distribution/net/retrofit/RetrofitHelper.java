package com.arian.distribution.net.retrofit;

import com.arian.distribution.base.OrianApplication;
import com.arian.distribution.utils.AppInfo;
import com.arian.distribution.utils.NetworkUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gushi on 2017/3/3.
 */
public class RetrofitHelper {


    private static BaseApis baseApis = null;
//    private static UDataApis uDataApis = null;

    private static OkHttpClient okHttpClient = null;
    public static final String PATH_DATA = OrianApplication.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    static {

        init();
    }

    private static void init() {

        initOkHttp();
        baseApis = getApiService(BaseApis.HOST, BaseApis.class);
//        uDataApis = getApiService(UDataApis.HOST, UDataApis.class);
    }


    private static void initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        if (BuildConfig.DEBUG) {
        // https://drakeet.me/retrofit-2-0-okhttp-3-0-config
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);


        /**
         * 添加公共参数
         */

        CommonParamsInterceptor commonParamsInterceptor = new CommonParamsInterceptor() {
            @Override
            public Map<String, String> getHeaderMap() {


                return null;

            }

            @Override
            public Map<String, String> getQueryParamMap() {


                return null;
            }

            @Override
            public Map<String, String> getFormBodyParamMap() {

//                String clientInfo = DataUtils.geCommInfos(OrianApplication.getContext(), OrianApplication.getContext().getPackageName());
                Map<String, String> headersMap = new HashMap<>();
                headersMap.put("v", AppInfo.getAppVersionName(OrianApplication.getContext(), OrianApplication.getContext().getPackageName()));

//                headersMap.put("client_type", "android");
//                headersMap.put("clientInfo", clientInfo);
                headersMap.put("client_type", "android");
//                headersMap.put("access_token", SpUtil.getString(OrianApplication.getContext(), Constant.ACCESSTOKEN, "1234567890"));


                return headersMap;
            }
        };

        builder.addInterceptor(commonParamsInterceptor);
//        }
        // http://www.jianshu.com/p/93153b34310e
        File cacheFile = new File(PATH_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetworkUtils.isConnected(OrianApplication.applicationContext)) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (NetworkUtils.isConnected(OrianApplication.applicationContext)) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
//        Interceptor apikey = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//                request = request.newBuilder()
////                        .addHeader("apikey",Constants.KEY_API)
//                        .b
//                        .build();
//                return chain.proceed(request);
//            }
//        };
////        设置统一的请求头部参数
//        builder.addInterceptor(apikey);


        //设置缓存
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);
        builder.cache(cache);
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        okHttpClient = builder.build();
    }


    private static <T> T getApiService(String baseUrl, Class<T> clz) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        T t = retrofit.create(clz);
        return t;

    }


    public static BaseApis getBaseApis() {

        if (baseApis != null) {
            return baseApis;

        } else {
//            baseApis = getApiService(UQiAPI.BASE_SERVER_URL, BaseApis.class);
            //逐步替换
            baseApis = getApiService(BaseApis.HOST, BaseApis.class);
            return baseApis;
        }
    }

//    public static UDataApis getUDataApis() {
//
//        if (uDataApis != null) {
//            return uDataApis;
//        } else {
//            uDataApis = getApiService(UDataApis.HOST, UDataApis.class);
//            return uDataApis;
//        }
//    }


    public static void cleanBaseApis() {

        baseApis = null;


    }


}
