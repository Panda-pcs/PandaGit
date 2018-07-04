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
import com.example.pcs.fragmentcase.ui.view.ILoadingView;
import com.example.pcs.fragmentcase.ui.view.LoadingDataLayout;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author pcs
 * @since 2018-06-26.
 */
public abstract class BaseFragment extends Fragment implements IBaseUI, ILoadingView {

    protected final String TAG = getClass().getSimpleName();

    private Unbinder unBind;//用于解绑ButterKnife

    protected LoadingDataLayout mLoadingDataLayout;//加载的View

    private boolean isSuccess;//网络是否是请求成功

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
        initLoadingDataLayout(view);

        initData();
        setListener();
    }

    /**
     * onDestroyView中进行解绑ButterKnife操作
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unBind.unbind();
    }

    /**
     * @param view
     */
    private void initLoadingDataLayout(View view) {
        mLoadingDataLayout = view.findViewById(R.id.view_loading_container);
        if (mLoadingDataLayout != null) {
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
        if (mLoadingDataLayout == null || isSuccess) return;
        mLoadingDataLayout.setStatus(networkStatus);
        if (LoadingDataLayout.STATUS_SUCCESS == networkStatus) isSuccess = true;
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
    public void hideLoading(int status) {
        switch (status) {
            case LoadingDataLayout.STATUS_SUCCESS:
                showLoadingStatus(LoadingDataLayout.STATUS_SUCCESS);
                break;
            case LoadingDataLayout.STATUS_ERROR:
                showLoadingStatus(LoadingDataLayout.STATUS_ERROR);
                break;
        }
    }

    @Override
    public void onLoadingComplete() {

    }
}
