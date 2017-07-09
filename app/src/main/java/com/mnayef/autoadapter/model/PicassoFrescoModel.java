package com.mnayef.autoadapter.model;

import com.mnayef.autoadapter.R;
import com.mnayef.annotations.Adapter;
import com.mnayef.annotations.Image;
import com.mnayef.annotations.enums.ImageLibraries;
import com.mnayef.annotations.enums.ImageSource;

/**
 * Created by Mohamed Hamdan on 2017-May-29.
 * mohamed.nayef95@gmail.com
 */
@Adapter(adapterName = "PicassoFrescoAdapter", layoutId = R.layout.picasso_fresco_row)
public class PicassoFrescoModel {

    @Image(value = R.id.picasso_url_image, source = ImageSource.URL, library = ImageLibraries.PICASSO, progressId = R.id.picasso_url_progress)
    private String picassoUrlImage = "";
    @Image(value = R.id.picasso_file_image, source = ImageSource.FILE, library = ImageLibraries.PICASSO, progressId = R.id.picasso_file_progress)
    private String picassoFileImage = "";
    @Image(value = R.id.picasso_resource_image, source = ImageSource.RESOURCES, library = ImageLibraries.PICASSO, progressId = R.id.picasso_resource_progress)
    private int picassoResourceImage;

    @Image(value = R.id.fresco_url_image, source = ImageSource.URL, library = ImageLibraries.FRESCO, progressId = R.id.fresco_url_progress)
    private String frescoUrlImage = "";
    @Image(value = R.id.fresco_file_image, source = ImageSource.FILE, library = ImageLibraries.FRESCO, progressId = R.id.fresco_file_progress)
    private String frescoFileImage = "";
    @Image(value = R.id.fresco_resource_image, source = ImageSource.RESOURCES, library = ImageLibraries.FRESCO, progressId = R.id.fresco_resource_progress)
    private int frescoResourceImage;

    public String getPicassoUrlImage() {
        return picassoUrlImage;
    }

    public void setPicassoUrlImage(String picassoUrlImage) {
        this.picassoUrlImage = picassoUrlImage;
    }

    public String getPicassoFileImage() {
        return picassoFileImage;
    }

    public void setPicassoFileImage(String picassoFileImage) {
        this.picassoFileImage = picassoFileImage;
    }

    public int getPicassoResourceImage() {
        return picassoResourceImage;
    }

    public void setPicassoResourceImage(int picassoResourceImage) {
        this.picassoResourceImage = picassoResourceImage;
    }

    public String getFrescoUrlImage() {
        return frescoUrlImage;
    }

    public void setFrescoUrlImage(String frescoUrlImage) {
        this.frescoUrlImage = frescoUrlImage;
    }

    public String getFrescoFileImage() {
        return frescoFileImage;
    }

    public void setFrescoFileImage(String frescoFileImage) {
        this.frescoFileImage = frescoFileImage;
    }

    public int getFrescoResourceImage() {
        return frescoResourceImage;
    }

    public void setFrescoResourceImage(int frescoResourceImage) {
        this.frescoResourceImage = frescoResourceImage;
    }
}
