package com.example.pcs.fragmentcase.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pcs.fragmentcase.R;


/**
 * @author pcs
 * @since 2018-06-26.
 */
public class RelationPage extends BaseFragment {
    public static final String TAG = RelationPage.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout=inflater.inflate(R.layout.fragment_relation,container,false);
        return layout;
    }
}
