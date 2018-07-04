package com.example.pcs.fragmentcase.ui.fragment;

import android.os.Handler;

import com.example.pcs.fragmentcase.R;
import com.example.pcs.fragmentcase.base.BaseFragment;
import com.example.pcs.fragmentcase.ui.view.LoadingDataLayout;


/**
 * @author pcs
 * @since 2018-06-26.
 */
public class HomeFragment extends BaseFragment {

    private Handler handler = new Handler();

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_home;
    }

    @Override
    public void initData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showLoadingStatus(LoadingDataLayout.STATUS_SUCCESS);
            }
        },2000);
    }
}
