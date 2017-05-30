package com.mnayef.autoadapter.model;


import com.mnayef.autoadapter.R;
import com.mnayef.annotations.Adapter;
import com.mnayef.annotations.Video;

/**
 * Created by Mohamed Hamdan on 2017-May-29.
 * mohamed.nayef95@gmail.com
 */
@Adapter(adapterName = "VideoAdapter", layoutId = R.layout.video_row)
public class VideoModel {

    @Video(R.id.video_view)
    private String videoUrl = "";

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
