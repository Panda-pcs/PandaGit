package com.example.pcs.fragmentcase.base;

import android.content.Context;

/**
 * @author pcs
 * @since 2018-07-02.
 */
public class BaseApp {
    private Context context;

    private BaseApp(){}

    public static BaseApp getInstance(){
        return Holder.INSTANCE;
    }

    private static class Holder{
        private final static BaseApp INSTANCE=new BaseApp();
    }

    public static Context getContext(){
        return getInstance().context;
    }
}
