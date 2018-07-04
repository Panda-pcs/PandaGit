package com.example.pcs.fragmentcase.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pcs.fragmentcase.R;
import com.example.pcs.fragmentcase.ui.view.ILoadingView;
import com.example.pcs.fragmentcase.ui.view.LoadingDataLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author pcs
 * @since 2018-07-03.
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseUI, ILoadingView {

    @Nullable
    @BindView(R.id.view_loading_container)
    protected LoadingDataLayout mLoadingDataLayout;

    protected Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(getLayoutResID());
        ButterKnife.bind(this);//必须在setContentView()之后调用

        initLoadingDataLaout();

        initToolBar();

        initView();
        initData();
        setListener();
    }

    protected void initLoadingDataLaout() {
        //如果需要progress开始就出现则需要findViewByID

        if (mLoadingDataLayout != null) {
            mLoadingDataLayout.setRetryListener(new LoadingDataLayout.OnRetryListener() {
                @Override
                public void onRetry() {
                    loadData();
                }
            });
        }
    }


    private void initToolBar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");//标题内容
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            Drawable drawable = getToolbarBackground();
            if (drawable != null) {
                ab.setBackgroundDrawable(drawable);
            }
            ab.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled());//显示返回键
        }
    }

    protected abstract void initView();

    public void setListener() {

    }

    /**
     * Toolbar背景
     *
     * @return 背景
     */
    protected Drawable getToolbarBackground() {
        return null;
    }


    /**
     * 显示返回键
     *
     * @return true为显示左上角返回键，反之为false
     */
    protected boolean displayHomeAsUpEnabled() {
        return true;
    }

    /**
     * 加载数据，如请求网络，读取本地缓存等
     */
    public void loadData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//Toolbar返回键
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        //如果关闭页面，取消请求
//        HttpManager.getInstance().cancelTag(this);

        super.onDestroy();
    }

    //-----------------------------------------------------------------------------

    /**
     * 展示网络请求各种状态
     *
     * @param status 网络请求状态
     */
    protected void showLoadingStatus(int status) {
        if (mLoadingDataLayout != null)
            mLoadingDataLayout.setStatus(status);
    }
    //-------------------------------ILoadingView-----------------------------------

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading(int status) {

    }

    @Override
    public void onLoadingComplete() {

    }
}
