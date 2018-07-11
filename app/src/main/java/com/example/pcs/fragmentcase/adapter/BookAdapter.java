package com.example.pcs.fragmentcase.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.pcs.fragmentcase.R;
import com.example.pcs.fragmentcase.base.BaseRecyclerAdapter;
import com.example.pcs.fragmentcase.bean.BookBean;
import com.example.pcs.fragmentcase.ui.activity.BookDetailsActivity;
import com.example.pcs.fragmentcase.utils.animators.ActivityAnimationUtils;

import java.util.List;

/**
 * @author pcs
 * @since 2018-07-09.
 */
public class BookAdapter extends BaseRecyclerAdapter<BookBean> {
    public BookAdapter(Context context, List<BookBean> beans) {
        super(context, beans);
    }

    @Override
    public int getItemLayout(int viewType) {
        return R.layout.item_book;
    }

    @Override
    protected void onItemClick(int position, View item) {
        super.onItemClick(position, item);
        Intent intent = new Intent(mContext, BookDetailsActivity.class);
        intent.putExtra("bookId", mBeans.get(position).getId());
        ActivityAnimationUtils.transition((Activity) mContext, intent, item);
    }

    @Override
    public void onBindData(RecyclerViewHolder holder, BookBean bean, int position) {
        holder.setText(R.id.tv_title,bean.getTitle());
        holder.setText(R.id.tv_num_rating,"豆瓣评分"+bean.getRating().getNumRaters());
        holder.setImageFromInternet(R.id.iv_image,bean.getImage());
    }
}
