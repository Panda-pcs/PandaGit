package com.example.pcs.fragmentcase.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * @author pcs
 * @since 2018-07-06.
 */
public class IntentUtils {
    public static void jump(Activity activity, Class<?> cls) {
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
    }

    public static void jump(Activity activity, Class<?> cls,Bundle bundle) {
        Intent intent = new Intent(activity, cls);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    public static void jump(Activity activity, Class<?> cls, boolean isFinish) {
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
        if (isFinish) {
            activity.finish();
        }
    }

    public static void jump(Activity activity, Class<?> cls, boolean isFinish, Bundle bundle) {
        Intent intent = new Intent(activity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
        if (isFinish) {
            activity.finish();
        }
    }

    public static void jump(Activity activity, Class<?> cls, boolean isFinish, int resquestCode) {
        Intent intent = new Intent(activity, cls);
        activity.startActivityForResult(intent, resquestCode);
        if (isFinish) {
            activity.finish();
        }
    }

    public static void jump(Activity activity, Class<?> cls, boolean isFinish, Bundle bundle, int resquestCode) {
        Intent intent = new Intent(activity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivityForResult(intent, resquestCode);
        if (isFinish) {
            activity.finish();
        }
    }

}
