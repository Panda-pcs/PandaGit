package com.example.pcs.fragmentcase.ui.activity;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pcs.fragmentcase.R;
import com.example.pcs.fragmentcase.adapter.MoviePerformerAdapter;
import com.example.pcs.fragmentcase.base.BaseActivity;
import com.example.pcs.fragmentcase.bean.MovieDetails;
import com.example.pcs.fragmentcase.constant.Apis;
import com.example.pcs.fragmentcase.http.HttpManager;
import com.example.pcs.fragmentcase.http.callback.StringCallback;
import com.example.pcs.fragmentcase.http.error.ErrorModel;
import com.example.pcs.fragmentcase.utils.ToastUtil;
import com.example.pcs.fragmentcase.utils.image.ImageManager;

import java.util.List;

import butterknife.BindView;

/**
 * @author pcs
 * @since 2018-07-06.
 */
public class MovieDetailActivity extends BaseActivity {


    @BindView(R.id.iv_movie_detail_bg)
    ImageView ivMovieDetailBg;  //底片背景

    @BindView(R.id.iv_movie_detail)
    ImageView ivMoviePicture;    //电影图片

    @BindView(R.id.ll_movie_detail)
    LinearLayout llMovieName; //电影名称父容器

    @BindView(R.id.tv_movie_detail_title)
    TextView tvMovieName;    //电影名称

    @BindView(R.id.tv_movie_detail_original_title)
    TextView tvMovieEnglishName;    //电影英文名称

    @BindView(R.id.tv_movie_detail_rating)
    TextView tvMovieScore;   //电影评分

    @BindView(R.id.tv_movie_detail_rating_count)
    TextView tvMovieDetailRatingCount;  //评分人数

    @BindView(R.id.tv_movie_detail_genres)
    TextView tvMovieType;   //电影类型

    @BindView(R.id.tv_movie_detail_countries)
    TextView tvMovieCountries;    //电影出产国家

    @BindView(R.id.tv_movie_detail_summary)
    TextView tvMovieDescribe;    //电影剧情描述

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;  //演员列表的RecyclerView

    private MoviePerformerAdapter moviePerformerAdapter;

    private String url;

    @Override
    public int getLayoutResID() {
        return R.layout.activity_movie_detail;
    }

    @Override
    protected Drawable getToolbarBackground() {
        return new ColorDrawable(0x00000000);
    }

    @Override
    public void initData() {
        url = Apis.MovieDetail + getIntent().getStringExtra("movieId");
        //请求数据
        loadData();
    }

    @Override
    public void loadData() {
        HttpManager.get()
                .tag(this)
                .url(url)
                .build()
                .enqueue(new StringCallback<MovieDetails>() {
                    @Override
                    public void onSuccess(MovieDetails response, Object... obj) {
                        //填充数据
                        showMovieDetail(response);
                    }

                    @Override
                    public void onFailure(ErrorModel errorModel) {
                        ToastUtil.showToast(errorModel.getMessage());
                    }

                    @Override
                    public void onAfter(boolean success) {
                        super.onAfter(success);

                        hideLoading(success);
                    }
                });
    }

    private void showMovieDetail(MovieDetails response) {
        //将请求下来的电影图片作为电影详情的背景图(毛玻璃)
        ImageManager.getBitmap(ivMoviePicture, response.getImages().getLarge(), ivMovieDetailBg);

        tvMovieName.setText(response.getTitle());
        tvMovieEnglishName.setText(response.getOriginal_title());
        tvMovieScore.setText(response.getRating().getAverage() + "");
        tvMovieDetailRatingCount.setText("(" + response.getRatings_count() + "人评)");
        tvMovieType.setText(format(response.getGenres().toString()));
        tvMovieCountries.setText(format(response.getCountries().toString()) + "/" + response.getYear());
        tvMovieDescribe.setText(response.getSummary());

        List<MovieDetails.PerformerBean> directors = response.getDirectors();
        directors.addAll(response.getCasts());

        //填充RecyclerView
        initRecyclerView(directors);
    }

    private void initRecyclerView(List<MovieDetails.PerformerBean> directors) {
        moviePerformerAdapter=new MoviePerformerAdapter(this,directors);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(moviePerformerAdapter);

/*        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                recyclerView.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });*/
    }

    private String format(String str) {
        return str.replace("[", "").replace("]", " ").replace(",", " ");
    }


}
