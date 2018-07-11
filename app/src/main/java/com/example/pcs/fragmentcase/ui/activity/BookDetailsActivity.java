package com.example.pcs.fragmentcase.ui.activity;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pcs.fragmentcase.R;
import com.example.pcs.fragmentcase.base.BaseActivity;
import com.example.pcs.fragmentcase.bean.BookDetails;
import com.example.pcs.fragmentcase.constant.Apis;
import com.example.pcs.fragmentcase.http.HttpManager;
import com.example.pcs.fragmentcase.http.callback.StringCallback;
import com.example.pcs.fragmentcase.http.error.ErrorModel;
import com.example.pcs.fragmentcase.utils.ToastUtil;
import com.example.pcs.fragmentcase.utils.image.ImageManager;

import java.util.List;

import butterknife.BindView;

public class BookDetailsActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;

    @BindView(R.id.iv_book_detail_bg)
    ImageView ivBookDetailBg;
    @BindView(R.id.iv_book_detail)
    ImageView ivBookDetail;
    @BindView(R.id.tv_book_detail_title)
    TextView tv_book_detail_title;
    @BindView(R.id.tv_book_detail_rating)
    TextView tv_book_detail_rating;
    @BindView(R.id.tv_book_detail_rating_count)
    TextView tv_book_detail_rating_count;
    @BindView(R.id.tv_book_detail_other_info)
    TextView tv_book_detail_other_info;
    @BindView(R.id.tv_book_detail_summary)
    TextView tv_book_detail_summary;
    @BindView(R.id.tv_book_detail_author_summary)
    TextView tv_book_detail_author_summary;
    @BindView(R.id.tv_book_detail_catalog)
    TextView tv_book_detail_catalog;

    private String url;


    @Override
    public int getLayoutResID() {
        return R.layout.activity_book_details;
    }

    @Override
    public void initData() {
        url = Apis.BookDetail + getIntent().getStringExtra("bookId");
        loadData();
    }

    @Override
    public void loadData() {
        HttpManager.get()
                .tag(this)
                .url(url)
                .build()
                .enqueue(new StringCallback<BookDetails>() {
                    @Override
                    public void onSuccess(BookDetails response, Object... obj) {
                        showBookDetail(response);
                    }

                    @Override
                    public void onFailure(ErrorModel errorModel) {
                        ToastUtil.showToast(errorModel.getMessage());
                    }
                });

    }

    private void showBookDetail(BookDetails response) {
        ImageManager.getBitmap(ivBookDetail, response.getImages().getLarge(), ivBookDetailBg);
        collapsing_toolbar.setTitle(response.getTitle());
        tv_book_detail_title.setText(response.getTitle());
        tv_book_detail_rating.setText(response.getRating().getAverage());
        tv_book_detail_rating_count.setText(getString(R.string.book_number_raters, response.getRating().getNumRaters()));
        List<String> author = response.getAuthor();
        tv_book_detail_other_info.setText(getString(R.string.book_author_info, author.size() == 0 ? "" : author.get(0), response.getPublisher(), response.getPubdate()));
        tv_book_detail_summary.setText(response.getSummary());
        tv_book_detail_author_summary.setText(response.getAuthor_intro());
        tv_book_detail_catalog.setText(response.getCatalog());
    }
}
