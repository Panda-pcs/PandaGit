package com.example.pcs.fragmentcase.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pcs.fragmentcase.R;
import com.example.pcs.fragmentcase.http.HandlerMain;
import com.example.pcs.fragmentcase.ui.view.ILoadingView;
import com.example.pcs.fragmentcase.ui.view.LoadingDataLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author pcs
 * @since 2018-06-26.
 */
public abstract class BaseFragment extends Fragment implements IBaseUI, ILoadingView {

    protected final String TAG = getClass().getSimpleName();

    private Unbinder unBind;//用于解绑ButterKnife

    @Nullable
    @BindView(R.id.view_loading_container)
    protected LoadingDataLayout mLoadingDataLayout;//网络请求加载的View

    /**
     * 是否允许懒加载
     */
    private boolean allowLazyLoading = true;
    /**
     * Fragment视图是否已初始化完成
     */
    private boolean isViewCreated = false;

    /**
     * 使用mActivity代替getActivity()，保证Fragment即使在onDetach后，仍持有Activity的引用<p>
     * 有引起内存泄露的风险，但相比空指针应用闪退，这种做法更“安全”
     */
    protected Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        prepareLazyLoading(isVisibleToUser, isViewCreated);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //视图肯定已经加载完成
        isViewCreated = true;
        View layout = inflater.inflate(getLayoutResID(), container, false);
        //绑定ButterKnife注解,返回一个Unbinder值（进行解绑），注意这里的this不能使用getActivity()
        unBind = ButterKnife.bind(this, layout);
        return layout;
    }

    /**
     * onViewCreated是在onCreateView()后被触发的事件，前后关系
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化加载菊花
        initLoadingDataLayout();

        HandlerMain.getHandler().post(new Runnable() {
            @Override
            public void run() {
                initData();
                setListener();
                //Fragment初始化时setUserVisibleHint方法会先于onCreateView执行
                prepareLazyLoading(getUserVisibleHint(), isViewCreated);
            }
        });
    }

    /**
     * onDestroyView中进行解绑ButterKnife操作
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unBind.unbind();
    }

    private void initLoadingDataLayout() {
        if (mLoadingDataLayout != null) {

            showLoading();

            //接口回调点击事件,加载失败重新加在数据
            mLoadingDataLayout.setRetryListener(new LoadingDataLayout.OnRetryListener() {
                @Override
                public void onRetry() {
                    loadData();
                }
            });
        }
    }

    /**
     * 预备懒加载
     *
     * @param isVisibleToUser Fragment用户可见
     * @param isViewCreated   Fragment视图已初始化完成
     */
    private void prepareLazyLoading(boolean isVisibleToUser, boolean isViewCreated) {
        if (!allowLazyLoading) return;

        if (isVisibleToUser && isViewCreated) {
            allowLazyLoading = false;//保证onLazyLoadingData（）只调用一次
            onLazyLoadingData();
        }
    }

    /**
     * 懒加载数据，只加载一次
     */
    public void onLazyLoadingData() {

    }

    /**
     * 重新加载数据，如请求网络，读取本地缓存等
     */
    public void loadData() {

    }

    public void setListener() {

    }


    /**
     * 展示网络请求各种状态
     *
     * @param networkStatus 网络请求状态
     */
    protected void showLoadingStatus(int networkStatus) {
        if (mLoadingDataLayout != null && !mLoadingDataLayout.isSuccess()) {
            mLoadingDataLayout.setStatus(networkStatus);
        }

    }
    //---------------------------------------ILoadingView-----------------------------------

    /**
     * 等待调用
     */
    @Override
    public void showLoading() {
        showLoadingStatus(LoadingDataLayout.STATUS_LOADING);
    }

    @Override
    public void hideLoading(boolean ifHide) {
        if (ifHide) {
            showLoadingStatus(LoadingDataLayout.STATUS_SUCCESS);
        } else {
            showLoadingStatus(LoadingDataLayout.STATUS_ERROR);
        }
    }
}
