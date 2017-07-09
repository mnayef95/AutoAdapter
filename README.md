# AutoAdapter #

Make RecyclerView adapter easy with Adapter annotations.

### What is AutoAdapter? ###

* Auto create RecyclerView adapter from model with annotations.
* 1.0.0

### How do I get set up? ###

**Project-level build.gradle** \(\<project>/build.gradle):
-----
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
**App-level build.gradle** \(\<project>/\<app-module>/build.gradle):
-----

```
provided 'com.github.mnayef.AutoAdapter:annotations:v1.0.1'
annotationProcessor 'com.github.mnayef.AutoAdapter:compiler:v1.0.1'
```



### Support components ###

* ImageView (Picasso, Fresco(GIF))
* TextView
* CheckBox
* RadioButton
* VideoView
* Visibility
* Click
* LongClick
* LinkPreview ([AndroidLinkPreview](https://github.com/mnayef/AndroidLinkPreview))

### How to use? ###

* Create Java Model.
* Add **Adapter Annotation** above of class name

**Example:**


```
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

    public String getDate() {
        return date;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    @LongClick(R.id.comment)
    public void commentLongClick(RecyclerView.Adapter adapter, int position, View view) {
        Toast.makeText(view.getContext(), "LongClick Comment: " + getId(), Toast.LENGTH_SHORT).show();
    }
}
```

**Rebuild your project and use in your activity like this**


```
RecyclerView rvAll = (RecyclerView) view.findViewById(R.id.rv_all);
rvAll.setLayoutManager(new LinearLayoutManager(getContext()));
rvAll.setAdapter(new MixAdapter(TestData.getInstance().getMix()));
```


**License**
-----
```
Copyright 2017 Mohamed Hamdan

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```