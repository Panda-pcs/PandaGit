package com.example.pcs.fragmentcase.base;

import android.content.Context;

/**
 * @author pcs
 * @since 2018-07-02.
 */
public class BaseApp {
    //--------单例----------
    private BaseApp() {
    }

    public static BaseApp getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private final static BaseApp INSTANCE = new BaseApp();
    }

    public static Context getContext() {
        return getInstance().context;
    }
//----------------------


    private Context context;

    /**
     * Application中用于赋值Context的方法
     */
    public void attachBaseContext(Context base) {
        context = base;
    }

    private ActivityLifeCallBack actCallBack;

    /**
     * 退出整个应用
     */
    public void exitApp(){
        actCallBack.finishAll();
    }
}
