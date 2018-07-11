package com.example.pcs.fragmentcase.ui.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.example.pcs.fragmentcase.R;
import com.example.pcs.fragmentcase.base.BaseFragment;
import com.example.pcs.fragmentcase.utils.viewpager.v4.FragmentPagerItem;
import com.example.pcs.fragmentcase.utils.viewpager.v4.FragmentPagerItemAdapter;
import com.example.pcs.fragmentcase.utils.viewpager.v4.FragmentPagerItems;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import butterknife.BindView;

/**
 * @author pcs
 * @since 2018-06-26.
 */
public class MovieFragment extends BaseFragment {

    @BindView(R.id.tabs)
    FrameLayout tabs;

    @BindView(R.id.viewpager_movie)
    ViewPager viewPager;

    FragmentPagerItemAdapter mAdapter;

    private String[] tabTitles = new String[]{"正在热映", "即将上映", "Top250", "科幻", "喜剧"};

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_movie;
    }

    @Override
    public void initData() {
        //将SmartTabLaout打气到FrameLayout并初始化SmartTabLaout
        tabs.addView(LayoutInflater.from(getActivity()).inflate(R.layout.tab_smarttablayout, tabs, false));
        SmartTabLayout smartTabLayout = tabs.findViewById(R.id.tab_viewpager);


        FragmentPagerItems pages = new FragmentPagerItems(getActivity());
        for (int i = 0; i < tabTitles.length; i++) {
            Bundle bundle = new Bundle();
            bundle.putInt("number", i);
            pages.add(FragmentPagerItem.of(tabTitles[i], MoviePageFragment.class, bundle));
        }
        mAdapter=new FragmentPagerItemAdapter(getChildFragmentManager(),pages);


        viewPager.setOffscreenPageLimit(tabTitles.length);
        viewPager.setAdapter(mAdapter);
        smartTabLayout.setViewPager(viewPager);
    }
}
