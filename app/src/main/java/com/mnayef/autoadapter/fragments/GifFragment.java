package com.mnayef.autoadapter.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mnayef.autoadapter.R;
import com.mnayef.autoadapter.data.TestData;
import com.mnayef.autoadapter.model.GifAdapter;

/**
 * Created by Mohamed Hamdan on 2017-May-29.
 * mohamed.nayef95@gmail.com
 */
public class GifFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all, container, false);

        RecyclerView rvAll = (RecyclerView) view.findViewById(R.id.rv_all);
        rvAll.setLayoutManager(new LinearLayoutManager(getContext()));

        rvAll.setAdapter(new GifAdapter(TestData.getInstance().getGif()));

        return view;
    }
}
