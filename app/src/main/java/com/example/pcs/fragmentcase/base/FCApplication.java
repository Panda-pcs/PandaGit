package com.example.pcs.fragmentcase.base;

import android.app.Application;
import android.content.Context;

/**
 * @author pcs
 * @since 2018-07-02.
 */
public class FCApplication extends Application {

    /**
     * APP最先执行此方法，优先于onCreate()执行
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //初始化Context
        BaseApp.getInstance().attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //注册ActivityLifeCycleCallback
        ActivityLifeCallBack.get(this);
    }
}
