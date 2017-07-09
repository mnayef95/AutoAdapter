package com.mnayef.autoadapter.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.mnayef.annotations.Adapter;
import com.mnayef.annotations.Click;
import com.mnayef.annotations.Link;
import com.mnayef.autoadapter.R;

/**
 * Created by Mohamed Hamdan on 2017-May-29.
 * mohamed.nayef95@gmail.com
 */
@Adapter(adapterName = "LinksAdapter", layoutId = R.layout.link_row)
public class LinkModel {

    @Link(value = R.id.link_preview, invalidLinkMag = "Invalid Url", failedLoadMag = "Failed load url")
    private String link = "";

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Click(R.id.link_preview)
    public void comment(RecyclerView.Adapter adapter, int position, View view) {
        Toast.makeText(view.getContext(), "Link: " + getLink(), Toast.LENGTH_SHORT).show();
    }
}
