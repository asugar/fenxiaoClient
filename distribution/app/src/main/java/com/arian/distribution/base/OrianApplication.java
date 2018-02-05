package com.arian.distribution.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDelegate;

import com.arian.distribution.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyi on 2018/2/5.
 */

public class OrianApplication extends Application {

    // 环信相关数据初始化
    public static OrianApplication applicationContext;

    /**
     * 当前用户nickname,为了苹果推送不是userid而是昵称
     */
    public static String currentUserNick = "";

    private List<Activity> mLstActivity = new ArrayList<Activity>();

    /**
     * greenDao helper
     */
//    private DaoMaster.DevOpenHelper devOpenHelper;
//    private SQLiteDatabase db;
//    private DaoMaster daoMaster;
//    private DaoSession daoSession;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setDragRate(1.0f);//显示下拉高度/手指真实下拉高度=阻尼效果
                layout.setPrimaryColorsId(R.color.colorPrimaryDark, android.R.color.white);//全局设置主题颜色
                return new BezierRadarHeader(context);
            }
        });
    }


//    public void addActivity(Activity aActi) {
//        mLstActivity.add(aActi);
//    }
//
//    public void onExitApplication() {
//        for (int i = 0; i < mLstActivity.size(); i++) {
//            Activity act = mLstActivity.get(i);
//            if (act == null) {
//                continue;
//            }
//            act.finish();
//        }
//        System.exit(0);
//    }


    public static synchronized OrianApplication getInstance() {
        return applicationContext;
    }

    public void onCreate() {
        super.onCreate();
        // 全局 抓异常
//        if(UQiAPI.ENVIRONMENT_ONLINE == UQiAPI.ENVIRONMENT){
//            UQiOnLineCrashHandler crashHandler = UQiOnLineCrashHandler.getInstance();// 正式发布前需开放这段代码
//            crashHandler.init(this);
//        }
        // 环信相关数据初始化 start
        applicationContext = this;

        // init demo helper
//        HXHelper.getInstance().init(applicationContext);
        // 环信相关数据初始化 end

        /**
         * 已经接入Bugly用户改用上面的初始化方法,不影响原有的crash上报功能;.
         * init方法会自动检测更新，不需要再手动调用Beta.checkUpgrade(),如需增加自动检查时机可以使用Beta.checkUpgrade(false,false);
         * 参数1：applicationContext
         * 参数2：appId
         * 参数3：是否开启debug
         */
//        Bugly.init(getApplicationContext(), "c54723cb86", true);
//        Bugly.init(getApplicationContext(), BuildConfig.BUGLY_APPID, true);

//        initDataBase();

//        initAlog();


//        QueuedWork.isUseThreadPool = false;
//        //友盟初始化
//        UMShareAPI.get(this);


        //  检查内存泄漏  初始化  //
//        initLeakCanary();


    }

//    private void initLeakCanary() {
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//
////        enabledStrictMode();
//        LeakCanary.install(this);
//
//    }

//    private static void enabledStrictMode() {
//        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder() //
//                .detectAll() //
//                .penaltyLog() //
//                .penaltyDeath() //
//                .build());
//    }





    /**
     * 初始化 数据库
     */
//    private void initDataBase() {
//        // do this once, for example in your Application class
//        devOpenHelper = new DaoMaster.DevOpenHelper(this, "uqi-db", null);
//        db = devOpenHelper.getWritableDatabase();
//        daoMaster = new DaoMaster(db);
//        daoSession = daoMaster.newSession();
//
//        QueryBuilder.LOG_SQL = true;
//        // 输出带有具体数值的sql日志
//        QueryBuilder.LOG_VALUES = true;
//    }

    /**
     * 初始化 Alog
     */
//    private void initAlog() {
//        new ALog.Builder(this)
//                .setLogSwitch(BuildConfig.DEBUG)// 设置log总开关，默认开
//                .setGlobalTag("")// 设置log全局标签，默认为空
//                // 当全局标签不为空时，我们输出的log全部为该tag，
//                // 为空时，如果传入的tag为空那就显示类名，否则显示tag
//                .setLogHeadSwitch(true)// 设置log头部是否显示，默认显示
//                .setLog2FileSwitch(false)// 打印log时是否存到文件的开关，默认关
//                .setBorderSwitch(true)// 输出日志是否带边框开关，默认开
//                .setLogFilter(ALog.V);// log过滤器，和logcat过滤器同理，默认Verbose
//    }

    //各个平台的配置，建议放在全局Application或者程序入口
    {
//        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
//        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        //开启debug模式，方便定位错误，具体错误检查方式可以查看http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式
//        Config.DEBUG = true;
//        PlatformConfig.setWeixin("wxccf3c7c973bb3c76", "d7d86aa4c8029341a6f6a0eaf4a6c532");
//        PlatformConfig.setQQZone("1106107794", "cD9GG0QELll3AAp1");


    }

    public static Context getContext() {
        return applicationContext;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }

//    public DaoSession getDaoSession() {
//        return daoSession;
//    }

}
