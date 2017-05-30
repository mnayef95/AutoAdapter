# AutoAdapter #

Make RecyclerView adapter easy with Adapter annotations.

### What is AutoAdapter? ###

* Auto create RecyclerView adapter from model with annotations.
* 1.0.0

### How do I get set up? ###

**Project-level build.gradle** \(\<project>/build.gradle):
```
#!Groovy

allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
**App-level build.gradle** \(\<project>/\<app-module>/build.gradle):

```
#!Groovy

compile 'com.github.mnayef:AAdapter:v1.0.1'
```



### Support components ###

* ImageView
* TextView
* CheckBox
* RadioButton
* VideoView
* Visibility
* Clickable

### How to use? ###

* Create Java Model.
* Add **Adapter Annotation** above of class name

**Example:**

```
#!java


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.mnayef.autoadapter.R;
import com.mnayef.annotations.Adapter;
import com.mnayef.annotations.Click;
import com.mnayef.annotations.Image;
import com.mnayef.annotations.Text;
import com.mnayef.annotations.enums.ImageLibraries;
import com.mnayef.annotations.enums.ImageSource;


/**
 * Created by Mohamed Hamdan on 2017-May-26.
 * mohamed.nayef95@gmail.com
 */
@Adapter(adapterName = "MixAdapter", layoutId = R.layout.mix_row, clickable = true)
public class MixModel {

    private int id;
    @Text(R.id.title)
    private String title = "";
    @Text(R.id.date)
    private String date = "";
    @Image(value = R.id.thumbnail, library = ImageLibraries.PICASSO, progressId = R.id.thumbnail_progress, source = ImageSource.URL)
    private String thumbnail = "";
    @Image(value = R.id.image, library = ImageLibraries.FRESCO, progressId = R.id.image_progress, source = ImageSource.URL)
    private String image = "";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Click(R.id.like)
    public void like(RecyclerView.Adapter adapter, int position, View view) {
        Toast.makeText(view.getContext(), "Like: " + getId(), Toast.LENGTH_SHORT).show();
    }

    @Click(R.id.comment)
    public void comment(RecyclerView.Adapter adapter, int position, View view) {
        Toast.makeText(view.getContext(), "Comment: " + getId(), Toast.LENGTH_SHORT).show();
    }
}
```