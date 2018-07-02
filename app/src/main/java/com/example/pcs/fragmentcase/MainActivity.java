package com.example.pcs.fragmentcase;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.RadioGroup;

import com.example.pcs.fragmentcase.fragment.BaseFragment;
import com.example.pcs.fragmentcase.fragment.DiscoveryPage;
import com.example.pcs.fragmentcase.fragment.MinePage;
import com.example.pcs.fragmentcase.fragment.RelationPage;
import com.example.pcs.fragmentcase.fragment.StrategagyPage;
import com.example.pcs.fragmentcase.utils.ToastUtil;

import java.lang.reflect.InvocationTargetException;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup controller;

    private FragmentManager fm;

    private BaseFragment cacheFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
//        setTitle("ssss");
        controller = findViewById(R.id.controller);
        // 为RadioGroup添加点击监听
        controller.setOnCheckedChangeListener(this);
        // 获取FragmentManager实例
        fm = getSupportFragmentManager();
        // 开始事务
        FragmentTransaction transaction = fm.beginTransaction();
        // 获取第一个页面，并持有引用
        cacheFragment = new RelationPage();
        // 事务中将第一个页面添加到container当中，并且设置标记TAG
        transaction.add(R.id.container, cacheFragment, RelationPage.TAG);
        // 提交事务
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        FragmentTransaction transaction = fm.beginTransaction();
        switch (i) {
            case R.id.controller_one:
                // 进化版
                switchPage(transaction,RelationPage.class,RelationPage.TAG);

                /**
                 // Lower
                 if (cacheFragment != null){
                 transaction.hide(cacheFragment);
                 }
                 cacheFragment = (BaseFragment) fm.findFragmentByTag(RelationPage.TAG);
                 if (cacheFragment != null){
                 transaction.show(cacheFragment);
                 }else{
                 cacheFragment = new RelationPage();
                 transaction.add(R.id.container,cacheFragment,RelationPage.TAG);
                 }
                 */
                break;
            case R.id.controller_two:
                switchPage(transaction,StrategagyPage.class,StrategagyPage.TAG);
                break;
            case R.id.controller_three:
                switchPage(transaction, DiscoveryPage.class,DiscoveryPage.TAG);
                break;
            case R.id.controller_four:
                switchPage(transaction, MinePage.class, MinePage.TAG);
                break;
        }
        transaction.commit();
    }
    private void switchPage(FragmentTransaction transaction ,Class<? extends BaseFragment> cls,String tag){
        // 隐藏显示的页面，我们进行一个非空判断
        if (cacheFragment != null){
            // 使用fragment的隐藏属性
            transaction.hide(cacheFragment);
        }
        // 先去我们的Fragment栈中根据TAG进行查找
        cacheFragment = (BaseFragment) fm.findFragmentByTag(tag);
        // 如果查找结果非空
        if (cacheFragment != null){
            // 使用fragment的显示属性
            transaction.show(cacheFragment);
        }else{
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
            transaction.add(R.id.container,cacheFragment,tag);
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
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}