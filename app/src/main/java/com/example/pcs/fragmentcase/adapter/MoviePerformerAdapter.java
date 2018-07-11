package com.example.pcs.fragmentcase.adapter;

import android.content.Context;

import com.example.pcs.fragmentcase.R;
import com.example.pcs.fragmentcase.base.BaseRecyclerAdapter;
import com.example.pcs.fragmentcase.bean.MovieDetails;

import java.util.List;

/**
 * @author pcs
 * @since 2018-07-08.
 */
public class MoviePerformerAdapter extends BaseRecyclerAdapter<MovieDetails.PerformerBean> {

    public MoviePerformerAdapter(Context context, List<MovieDetails.PerformerBean> beans) {
        super(context, beans);
    }

    @Override
    public int getItemLayout(int viewType) {
        return R.layout.item_movie_photo;
    }

    @Override
    public void onBindData(RecyclerViewHolder holder, MovieDetails.PerformerBean bean, int position) {
        if(bean.getAvatars()!=null)
            holder.setImageFromInternet(R.id.iv_movie_photo,bean.getAvatars().getMedium());
        holder.setText(R.id.tv_movie_photo,bean.getName());
        if (position==0){
            holder.setText(R.id.tv_movie_photo_type,"导演");
        }else if (position==1){
            holder.setText(R.id.tv_movie_photo_type,"演员");
        }else {
            holder.setText(R.id.tv_movie_photo_type,"");
        }
    }
}
