package com.example.pcs.fragmentcase.ui.fragment;

import android.support.v7.widget.GridLayoutManager;

import com.example.pcs.fragmentcase.R;
import com.example.pcs.fragmentcase.adapter.BookAdapter;
import com.example.pcs.fragmentcase.base.BaseFragment;
import com.example.pcs.fragmentcase.bean.BookBean;
import com.example.pcs.fragmentcase.bean.BookListBean;
import com.example.pcs.fragmentcase.constant.Apis;
import com.example.pcs.fragmentcase.http.HttpManager;
import com.example.pcs.fragmentcase.http.callback.StringCallback;
import com.example.pcs.fragmentcase.http.error.ErrorModel;
import com.example.pcs.fragmentcase.utils.ToastUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;


/**
 * @author pcs
 * @since 2018-06-26.
 */
public class BookFragment extends BaseFragment {

    @BindView(R.id.XRecyclerView_home)
    XRecyclerView mRecyclerView;

    private BookAdapter bookAdapter;

    private List<BookBean> books = new ArrayList<>();

    private LinkedHashMap<String, String> params;

    private int start = 0;
    private int count = 20;

    private String tag = "热门";

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_home;
    }

    @Override
    public void initData() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        bookAdapter = new BookAdapter(getActivity(), books);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        mRecyclerView.setAdapter(bookAdapter);
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
        mRecyclerView.refresh();
    }

    @Override
    public void loadData() {
        params = new LinkedHashMap<>();
        params.put("tag", tag);
        params.put("start", start + "");
        params.put("count", count + "");

        HttpManager.get()
                .tag(this)
                .url(Apis.BookSearch)
                .params(params)
                .build()
                .enqueue(new StringCallback<BookListBean>() {
                    @Override
                    public void onSuccess(BookListBean response, Object... obj) {
                        refreshBookList(response);
                    }

                    @Override
                    public void onFailure(ErrorModel errorModel) {
                        ToastUtil.showToast(errorModel.getMessage());
                    }

                    @Override
                    public void onAfter(boolean success) {
                        mRecyclerView.refreshComplete();
                        mRecyclerView.loadMoreComplete();
                        hideLoading(success);
                    }
                });
    }

    /**
     * 刷新所有书籍列表
     * @param response
     */
    private void refreshBookList(BookListBean response) {
        if (start==0)books.clear();
        books.addAll(response.getBooks());
        bookAdapter.notifyDataSetChanged();
        start+=count;
        mRecyclerView.setLoadingMoreEnabled(start<response.getTotal());
    }
}