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

* ImageView (Picasso, Fresco(GIF))
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

**R.layout.mix_row file**


```
#!xml

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical">

    <android.support.v7.widget.CardView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_margin="10dp"
        android:foreground="?selectableItemBackground">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical"
            android:paddingLeft="10dp"
            android:paddingRight="10dp"
            android:paddingTop="10dp">

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:gravity="center_vertical"
                android:orientation="horizontal">

                <RelativeLayout
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content">

                    <com.makeramen.roundedimageview.RoundedImageView
                        android:id="@+id/thumbnail"
                        android:layout_width="70dp"
                        android:layout_height="70dp"
                        android:background="@drawable/rounded_background"
                        app:riv_oval="true" />

                    <ProgressBar
                        android:id="@+id/thumbnail_progress"
                        android:layout_width="16dp"
                        android:layout_height="16dp"
                        android:layout_centerInParent="true" />
                </RelativeLayout>

                <LinearLayout
                    android:layout_width="wrap_content"
                    android:layout_height="70dp"
                    android:gravity="center_vertical"
                    android:orientation="vertical">

                    <TextView
                        android:id="@+id/title"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_marginLeft="10dp"
                        android:layout_marginStart="10dp"
                        android:textColor="#000000"
                        android:textSize="16sp" />

                    <TextView
                        android:id="@+id/date"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_marginLeft="10dp"
                        android:layout_marginStart="10dp"
                        android:layout_marginTop="10dp"
                        android:textSize="12sp" />
                </LinearLayout>
            </LinearLayout>

            <RelativeLayout
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginTop="25dp">

                <com.facebook.drawee.view.SimpleDraweeView
                    android:id="@+id/image"
                    android:layout_width="match_parent"
                    android:layout_height="220dp"
                    android:background="#DDDDDD"
                    android:scaleType="fitXY" />

                <ProgressBar
                    android:id="@+id/image_progress"
                    android:layout_width="25dp"
                    android:layout_height="25dp"
                    android:layout_centerInParent="true" />
            </RelativeLayout>

            <View
                android:layout_width="match_parent"
                android:layout_height="0.5dp"
                android:layout_marginTop="15dp"
                android:background="#DDDDDD" />

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="50dp">

                <TextView
                    android:id="@+id/like"
                    android:layout_width="0dp"
                    android:layout_height="match_parent"
                    android:layout_weight="1"
                    android:background="?selectableItemBackground"
                    android:gravity="center"
                    android:text="@string/like"
                    android:textSize="18sp" />

                <View
                    android:layout_width="0.5dp"
                    android:layout_height="match_parent"
                    android:layout_marginBottom="5dp"
                    android:layout_marginTop="5dp"
                    android:background="#DDDDDD" />

                <TextView
                    android:id="@+id/comment"
                    android:layout_width="0dp"
                    android:layout_height="match_parent"
                    android:layout_weight="1"
                    android:background="?selectableItemBackground"
                    android:gravity="center"
                    android:text="@string/comment"
                    android:textSize="18sp" />

            </LinearLayout>
        </LinearLayout>
    </android.support.v7.widget.CardView>
</LinearLayout>
```

**In your activity use like this**


```
#!java

RecyclerView rvAll = (RecyclerView) view.findViewById(R.id.rv_all);
rvAll.setLayoutManager(new LinearLayoutManager(getContext()));
rvAll.setAdapter(new MixAdapter(TestData.getInstance().getMix()));
```