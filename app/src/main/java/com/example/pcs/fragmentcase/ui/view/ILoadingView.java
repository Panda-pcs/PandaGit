package com.example.pcs.fragmentcase.ui.view;

/**
 * @author pcs
 * @since 2018-06-26.
 */
public interface ILoadingView {

    /**
     * 显示 Loading View
     */
    void showLoading();

    /**
     * 隐藏 Loading View
     *
     * @param status 1正常 2加载失败 3数据为空
     */
    void hideLoading(int status);

    /**
     * 加载完成
     */
    void onLoadingComplete();

}
