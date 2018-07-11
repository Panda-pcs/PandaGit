package com.example.pcs.fragmentcase.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;

import com.example.pcs.fragmentcase.R;
import com.example.pcs.fragmentcase.adapter.MovieAdapter;
import com.example.pcs.fragmentcase.base.BaseFragment;
import com.example.pcs.fragmentcase.bean.MovieBean;
import com.example.pcs.fragmentcase.bean.MovieListBean;
import com.example.pcs.fragmentcase.constant.Apis;
import com.example.pcs.fragmentcase.http.HttpManager;
import com.example.pcs.fragmentcase.http.callback.StringCallback;
import com.example.pcs.fragmentcase.http.error.ErrorModel;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;

/**
 * @author pcs
 * @since 2018-07-04.
 */
public class MoviePageFragment extends BaseFragment {

    @BindView(R.id.x_recyclerview)
    XRecyclerView mRecyclerView;

    private int number;//页数

    private String url = Apis.MovieInTheaters;  //  请求路径

    private LinkedHashMap<String, String> params = new LinkedHashMap<>();//请求参数

    private MovieAdapter movieAdapter;

    private List<MovieBean> data = new ArrayList<>();

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_moviepage;
    }

    @Override
    public void initData() {
        //0."正在热映", 1."即将上映", 2."Top250",3. "科幻电影",4. "喜剧电影"
        number = getArguments().getInt("number");
        switch (number) {
            case 0:
                url = Apis.MovieInTheaters;
                break;
            case 1:
                url = Apis.MovieComingSoon;
                break;
            case 2:
                url = Apis.MovieTop250;
                break;
            case 3:
                url = Apis.MovieSearch;
                params.put("tag", "科幻");
                break;
            case 4:
                url = Apis.MovieSearch;
                params.put("tag", "喜剧");
                break;
        }
        initRecyclerView();
    }

    private void initRecyclerView() {
        movieAdapter = new MovieAdapter(getActivity(), data);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(movieAdapter);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                start = 0;
                loadData();
            }

            @Override
            public void onLoadMore() {
                loadData();
            }
        });
    }

    @Override
    public void onLazyLoadingData() {
        super.onLazyLoadingData();
        mRecyclerView.refresh();
    }

    private int start = 0;
    private int count = 20;

    @Override
    public void loadData() {
        params.put("start", start + "");
        params.put("count", count + "");
        HttpManager.get()
                .tag(this)
                .url(url)
                .params(params)
                .build()
                .enqueue(new StringCallback<MovieListBean>() {

                    @Override
                    public void onSuccess(MovieListBean response, Object... obj) {
                        refreshMovieList(response);
                    }

                    @Override
                    public void onFailure(ErrorModel errorModel) {

                    }

                    @Override
                    public void onAfter(boolean success) {
                        mRecyclerView.refreshComplete();
                        mRecyclerView.loadMoreComplete();
                        //加载成功移除加载View
                        hideLoading(success);
                    }
                });
    }

    private void refreshMovieList(MovieListBean response) {
        //①如果是第一页则先清空数据 data不做非空判断，不可能为空
        if (start==0) data.clear();
        //②获取数据
        data.addAll(response.getSubjects());
        //③刷新RecyclerView
        movieAdapter.notifyDataSetChanged();
        //④页码自增
        start+=count;
        //⑤如果没有数据了，禁用加载更多功能
        mRecyclerView.setLoadingMoreEnabled(start<response.getTotal());
    }
}
