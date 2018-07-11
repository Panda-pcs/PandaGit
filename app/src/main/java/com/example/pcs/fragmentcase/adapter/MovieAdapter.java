package com.example.pcs.fragmentcase.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.pcs.fragmentcase.R;
import com.example.pcs.fragmentcase.base.BaseRecyclerAdapter;
import com.example.pcs.fragmentcase.bean.MovieBean;
import com.example.pcs.fragmentcase.ui.activity.MovieDetailActivity;
import com.example.pcs.fragmentcase.utils.animators.ActivityAnimationUtils;

import java.util.List;

/**
 * @author pcs
 * @since 2018-07-06.
 */
public class MovieAdapter extends BaseRecyclerAdapter<MovieBean> {
    public MovieAdapter(Context context, List<MovieBean> beans) {
        super(context, beans);
    }

    @Override
    public int getItemLayout(int viewType) {
        return R.layout.item_movie;
    }

    @Override
    protected void onItemClick(int position, View item) {
        super.onItemClick(position, item);
        Intent intent = new Intent(mContext, MovieDetailActivity.class);
        intent.putExtra("movieId", mBeans.get(position).getId());
//        mContext.startActivity(intent);
        ActivityAnimationUtils.transition((Activity) mContext, intent, item);


    }

    @Override
    public void onBindData(RecyclerViewHolder holder, MovieBean bean, int position) {
        holder.setText(R.id.tv_title, bean.getTitle());
        if (!bean.getDirectors().isEmpty())
            holder.setText(R.id.tv_author, "导         演：" + bean.getDirectors().get(0).getName());
        holder.setText(R.id.tv_date, "上映日期：" + bean.getYear());
        holder.setText(R.id.tv_publisher, "电影剧情：" + bean.getGenres().toString());
        holder.setText(R.id.tv_num_rating, "观众评分：" + bean.getRating().getAverage());
        holder.setImageFromInternet(R.id.iv_image, bean.getImages().getMedium());

    }
}
