package com.example.pcs.fragmentcase.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.LinkedList;
import java.util.List;

/**
 * 全局Activity生命周期回调管理(eg:可利用来判断程序是否是前后端)
 *
 * @author pcs
 * @since 2018-07-02.
 */
public class ActivityLifeCallBack implements Application.ActivityLifecycleCallbacks {
    /***
     * 寄存整个应用Activity
     **/
    private final List<Activity> activityList = new LinkedList<>();

    private static ActivityLifeCallBack instance;

    /**
     * finish所有的Activity（用于整个应用退出）
     */
    public void finishAll() {
        synchronized (activityList) {
            for (Activity activity : activityList) {
                activity.finish();
            }
            activityList.clear();
        }
    }

    public static ActivityLifeCallBack init(Application application) {
        if (instance == null) {
            instance = new ActivityLifeCallBack();
            application.registerActivityLifecycleCallbacks(instance);
        }
        return instance;
    }
    /**
     * 拿到该类实例
     */
    public static ActivityLifeCallBack get(Application application) {
        if (instance == null) {
            init(application);
        }
        return instance;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        activityList.add(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        activityList.remove(activity);
    }
}
