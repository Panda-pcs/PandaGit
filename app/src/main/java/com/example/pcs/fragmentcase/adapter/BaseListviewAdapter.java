package com.example.pcs.fragmentcase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pcs
 * @since 2018-07-02.
 */
public abstract class BaseListviewAdapter<T> extends BaseAdapter {
    private List<T> data;
    private int[] layoutRes;

    private LayoutInflater inflater;

    public BaseListviewAdapter(Context context, int... layoutRes) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layoutRes = layoutRes;
        this.data = new ArrayList<>();
    }

    public void updateRes(List<T> data) {
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }
    public void addModel(T o){
        if (o!=null){
            this.data.add(o);
            notifyDataSetChanged();
        }
    }
    public void addRes(List<T> data) {
        if (data != null) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getViewTypeCount() {
        return layoutRes.length;
    }

    @Override
    public int getItemViewType(int position) {
        T t = getItem(position);
        Class<?> cls = t.getClass();
        //
        int type = 0;
        try {
            Field field = cls.getDeclaredField("type");
            field.setAccessible(true);
            try {
                type = field.getInt(t);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return type;
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {

            convertView = inflater.inflate(layoutRes[getItemViewType(position)], parent, false);

            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // 加载数据
        bindViewHolder(holder, getItem(position));

        return convertView;
    }

    public abstract void bindViewHolder(ViewHolder holder, T t);

    protected class ViewHolder {

        View layout;

        Map<Integer, View> cacheView;

        public ViewHolder(View layout) {
            this.layout = layout;
            cacheView = new HashMap<>();
        }

        public View getView(int resId) {
            View view;
            if (cacheView.containsKey(resId)) {
                view = cacheView.get(resId);
            } else {
                view = layout.findViewById(resId);
                cacheView.put(resId, view);
            }
            return view;
        }


    }

}
