package com.example.pcs.fragmentcase.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioGroup;

import com.example.pcs.fragmentcase.R;
import com.example.pcs.fragmentcase.base.BaseActivity;
import com.example.pcs.fragmentcase.base.BaseApp;
import com.example.pcs.fragmentcase.base.BaseFragment;
import com.example.pcs.fragmentcase.ui.fragment.HomeFragment;
import com.example.pcs.fragmentcase.ui.fragment.MovieFragment;
import com.example.pcs.fragmentcase.ui.fragment.SettingFragment;
import com.example.pcs.fragmentcase.utils.ToastUtil;

import java.lang.reflect.InvocationTargetException;

/**
 * @author pcs
 * @since 2018-06-26.
 */
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup controller;

    private FragmentManager fm;

    private BaseFragment cacheFragment;

    private String[] title = {"首页", "电影", "设置"};

    @Override
    public int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean displayHomeAsUpEnabled() {
        return false;
    }

    @Override
    protected void initView() {
        setTitle(title[0]);
        controller = findViewById(R.id.radioGroup_main);
        // 为RadioGroup添加点击监听
        controller.setOnCheckedChangeListener(this);
        // 获取FragmentManager实例
        fm = getSupportFragmentManager();
        // 开始事务
        FragmentTransaction transaction = fm.beginTransaction();
        // 获取第一个页面，并持有引用
        cacheFragment = new HomeFragment();
        // 事务中将第一个页面添加到container当中，并且设置标记TAG
        transaction.add(R.id.container, cacheFragment, HomeFragment.class.getSimpleName());
        // 提交事务
        transaction.commit();

    }

    @Override
    public void initData() {

    }

    /**
     * 重写RadioGroup切换监听
     */
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        FragmentTransaction transaction = fm.beginTransaction();
        switch (i) {
            case R.id.rbtn_main_home:
                // 进化版
                switchPage(transaction, HomeFragment.class, HomeFragment.class.getSimpleName());
                setTitle(title[0]);
                break;
            case R.id.rbtn_main_movie:
                switchPage(transaction, MovieFragment.class, MovieFragment.class.getSimpleName());
                setTitle(title[1]);
                break;
            case R.id.rbtn_main_setting:
                switchPage(transaction, SettingFragment.class, SettingFragment.class.getSimpleName());
                setTitle(title[2]);
                break;
        }
        transaction.commit();
    }

    private void switchPage(FragmentTransaction transaction, Class<? extends BaseFragment> cls, String tag) {
        // 隐藏显示的页面，我们进行一个非空判断
        if (cacheFragment != null) {
            // 使用fragment的隐藏属性
            transaction.hide(cacheFragment);
        }
        // 先去我们的Fragment栈中根据TAG进行查找
        cacheFragment = (BaseFragment) fm.findFragmentByTag(tag);
        // 如果查找结果非空
        if (cacheFragment != null) {
            // 使用fragment的显示属性
            transaction.show(cacheFragment);
        } else {
            // 如果查找结果为空，我们获取一个新的实例，并将引用缓存下来
            try {
                cacheFragment = cls.getConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            // 将我们新的页面添加到显示容器中，并且根据TAG添加到Fragment栈中
            transaction.add(R.id.container, cacheFragment, tag);
        }
    }



    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                ToastUtil.showToast("再按一次退出应用");
                exitTime = System.currentTimeMillis();
            } else {
                BaseApp.getInstance().exitApp();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}