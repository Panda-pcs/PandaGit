package com.example.pcs.fragmentcase.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pcs.fragmentcase.base.BaseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rock on 16/1/29.
 */
public abstract class BaseRecyclerViewAdapter extends RecyclerView.Adapter<BaseRecyclerViewAdapter.ViewHolder> {

    private List<BaseModel> data;

    private LayoutInflater inflater;

    private int[] layoutRes;

    public BaseRecyclerViewAdapter(Context context, int... layoutRes){
        this.layoutRes = layoutRes;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        data = new ArrayList<>();
    }

    public void addRes(List<BaseModel> data){
        if (data != null){
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = inflater.inflate(layoutRes[viewType],parent,false);
        return new ViewHolder(layout);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        bindData(holder, data.get(position));
    }

    public abstract void bindData(ViewHolder holder, BaseModel model);

    @Override
    public int getItemViewType(int position) {
        int type = 0;
        type = data.get(position).getType();
        return type;
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private View layout;

        private Map<Integer,View> cacheView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.layout = itemView;
            cacheView = new HashMap<>();
        }

        public View getView(int resId){
            View view;
            if (cacheView.containsKey(resId)){
                view = cacheView.get(resId);
            }else{
                view = layout.findViewById(resId);
                cacheView.put(resId,view);
            }
            return view;
        }
    }

}
