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
@Adapter(adapterName = "FrescoAdapter", layoutId = R.layout.fresco_row)
public class FrescoModel {

    @Image(value = R.id.url_image, source = ImageSource.URL, library = ImageLibraries.FRESCO, progressId = R.id.url_progress)
    private String urlImage = "";
    @Image(value = R.id.file_image, source = ImageSource.FILE, library = ImageLibraries.FRESCO, progressId = R.id.file_progress)
    private String fileImage = "";
    @Image(value = R.id.resource_image, source = ImageSource.RESOURCES, library = ImageLibraries.FRESCO, progressId = R.id.resource_progress)
    private int resourceImage;

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getFileImage() {
        return fileImage;
    }

    public void setFileImage(String fileImage) {
        this.fileImage = fileImage;
    }

    public int getResourceImage() {
        return resourceImage;
    }

    public void setResourceImage(int resourceImage) {
        this.resourceImage = resourceImage;
    }
}
