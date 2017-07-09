package com.mnayef.autoadapter.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mnayef.autoadapter.R;
import com.mnayef.autoadapter.data.TestData;
import com.mnayef.autoadapter.model.MixAdapter;


/**
 * Created by Mohamed Hamdan on 2017-May-29.
 * mohamed.nayef95@gmail.com
 */
public class MixFragment extends Fragment implements MixAdapter.OnItemClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all, container, false);

        RecyclerView rvAll = (RecyclerView) view.findViewById(R.id.rv_all);
        rvAll.setLayoutManager(new LinearLayoutManager(getContext()));

        MixAdapter mixAdapter = new MixAdapter(TestData.getInstance().getMix());
        mixAdapter.setOnItemClickListener(this);

        rvAll.setAdapter(mixAdapter);

        return view;
    }

    @Override
    public void OnItemClick(View view, int position, MixAdapter.ViewHolder holder) {
        Toast.makeText(getContext(), "Position: " + position, Toast.LENGTH_SHORT).show();
    }
}
