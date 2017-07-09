package com.mnayef.autoadapter.data;


import com.mnayef.autoadapter.R;
import com.mnayef.autoadapter.model.AllModel;
import com.mnayef.autoadapter.model.FrescoModel;
import com.mnayef.autoadapter.model.GifModel;
import com.mnayef.autoadapter.model.LinkModel;
import com.mnayef.autoadapter.model.MixModel;
import com.mnayef.autoadapter.model.PFWithoutProgressModel;
import com.mnayef.autoadapter.model.PicassoFrescoModel;
import com.mnayef.autoadapter.model.PicassoModel;
import com.mnayef.autoadapter.model.VideoModel;
import com.mnayef.autoadapter.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohamed Hamdan on 2017-May-26.
 * mohamed.nayef95@gmail.com
 */
public class TestData {

    private static TestData instance;
    private String png;
    private String gifImage;

    public static TestData getInstance() {
        if (instance == null) {
            instance = new TestData();
        }
        return instance;
    }

    private TestData() {
        png = FileUtils.getFirstImage("png");
        gifImage = FileUtils.getFirstImage("gif");
    }

    public List<MixModel> getMix() {
        List<MixModel> mix = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            MixModel mixModel = new MixModel();
            mixModel.setId(i);
            mixModel.setTitle("Wayne Kelley");
            mixModel.setDate("21 Jul 2017");
            mixModel.setThumbnail("https://robohash.org/laborequidemsuscipit?size/u003d600x400/u0026set/u003dset1");
            mixModel.setImage("https://cdn.pixabay.com/photo/2016/03/28/12/35/cat-1285634_960_720.png");
            mix.add(mixModel);
        }
        return mix;
    }

    public List<AllModel> getAll() {
        List<AllModel> all = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            AllModel allModel = new AllModel();
            allModel.setRadio(true);
            allModel.setVisibility(true);
            allModel.setCheck(true);
            allModel.setText("Text");
            all.add(allModel);
        }
        return all;
    }

    public List<FrescoModel> getFresco() {
        List<FrescoModel> fresco = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            FrescoModel frescoModel = new FrescoModel();
            frescoModel.setFileImage(png != null ? png : "");
            frescoModel.setResourceImage(R.mipmap.ic_launcher);
            frescoModel.setUrlImage("https://cdn.pixabay.com/photo/2016/03/28/12/35/cat-1285634_960_720.png");
            fresco.add(frescoModel);
        }
        return fresco;
    }

    public List<GifModel> getGif() {
        List<GifModel> gif = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            GifModel gifModel = new GifModel();
            gifModel.setFileImage(gifImage != null ? gifImage : "");
            gifModel.setUrlImage("https://media2.giphy.com/avatars/nikdudukovic/ylDRTR05sy6M.gif");
            gifModel.setResourceImage(R.mipmap.gif_image);
            gif.add(gifModel);
        }
        return gif;
    }

    public List<PFWithoutProgressModel> getPFWithoutProgress() {
        List<PFWithoutProgressModel> pfWithoutProgress = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            PFWithoutProgressModel pfWithoutProgressModel = new PFWithoutProgressModel();
            pfWithoutProgressModel.setFrescoFileImage(png);
            pfWithoutProgressModel.setPicassoFileImage(png);
            pfWithoutProgressModel.setFrescoResourceImage(R.mipmap.ic_launcher);
            pfWithoutProgressModel.setPicassoResourceImage(R.mipmap.ic_launcher);
            pfWithoutProgressModel.setFrescoUrlImage("https://cdn.pixabay.com/photo/2016/03/28/12/35/cat-1285634_960_720.png");
            pfWithoutProgressModel.setPicassoUrlImage("https://cdn.pixabay.com/photo/2016/03/28/12/35/cat-1285634_960_720.png");
            pfWithoutProgress.add(pfWithoutProgressModel);
        }
        return pfWithoutProgress;
    }

    public List<PicassoModel> getPicasso() {
        List<PicassoModel> picasso = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            PicassoModel picassoModel = new PicassoModel();
            picassoModel.setFileImage(png);
            picassoModel.setResourceImage(R.mipmap.ic_launcher);
            picassoModel.setUrlImage("https://cdn.pixabay.com/photo/2016/03/28/12/35/cat-1285634_960_720.png");
            picasso.add(picassoModel);
        }
        return picasso;
    }

    public List<PicassoFrescoModel> getPF() {
        List<PicassoFrescoModel> pf = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            PicassoFrescoModel pfModel = new PicassoFrescoModel();
            pfModel.setFrescoFileImage(png);
            pfModel.setPicassoFileImage(png);
            pfModel.setFrescoResourceImage(R.mipmap.ic_launcher);
            pfModel.setPicassoResourceImage(R.mipmap.ic_launcher);
            pfModel.setFrescoUrlImage("https://cdn.pixabay.com/photo/2016/03/28/12/35/cat-1285634_960_720.png");
            pfModel.setPicassoUrlImage("https://cdn.pixabay.com/photo/2016/03/28/12/35/cat-1285634_960_720.png");
            pf.add(pfModel);
        }
        return pf;
    }

    public List<VideoModel> getVideo() {
        List<VideoModel> video = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            VideoModel videoModel = new VideoModel();
            videoModel.setVideoUrl("http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4");
            video.add(videoModel);
        }
        return video;
    }

    public List<LinkModel> getLinks() {
        List<LinkModel> links = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            LinkModel link = new LinkModel();
            link.setLink("https://github.com/mnayef/AndroidLinkPreview");
            links.add(link);
        }
        return links;
    }
}
