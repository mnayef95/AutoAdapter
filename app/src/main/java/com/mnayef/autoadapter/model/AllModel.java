package com.mnayef.autoadapter.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mnayef.autoadapter.R;
import com.mnayef.annotations.Adapter;
import com.mnayef.annotations.Check;
import com.mnayef.annotations.Click;
import com.mnayef.annotations.Radio;
import com.mnayef.annotations.Text;
import com.mnayef.annotations.Visibility;

/**
 * Created by Mohamed Hamdan on 2017-May-29.
 * mohamed.nayef95@gmail.com
 */
@Adapter(adapterName = "AllAdapter", layoutId = R.layout.all_row)
public class AllModel {

    @Text(R.id.text)
    private String text = "";
    @Radio(R.id.radio)
    private boolean radio;
    @Check(R.id.check)
    private boolean check;
    @Visibility(R.id.visibility)
    private boolean visibility;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isRadio() {
        return radio;
    }

    public void setRadio(boolean radio) {
        this.radio = radio;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    @Click(R.id.click)
    public void click(RecyclerView.Adapter adapter, int position, View view) {
        AllAdapter allAdapter = (AllAdapter) adapter;
        allAdapter.getList().get(position).setVisibility(!allAdapter.getList().get(position).isVisibility());
        allAdapter.getList().get(position).setCheck(!allAdapter.getList().get(position).isCheck());
        allAdapter.getList().get(position).setRadio(!allAdapter.getList().get(position).isRadio());
        allAdapter.notifyDataSetChanged();
    }

}
