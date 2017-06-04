package com.mnayef.autoadapter.model;

import com.mnayef.annotations.Adapter;
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
}
