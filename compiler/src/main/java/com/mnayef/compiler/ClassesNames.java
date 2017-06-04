package com.mnayef.compiler;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

/**
 * Created by Mohamed Hamdan on 2017-May-21.
 * mohamed.nayef95@gmail.com
 */
public class ClassesNames {

    public static final ClassName FILE = ClassName.get("java.io", "File");
    public static final ClassName RECYCLER_HOLDER = ClassName.get("android.support.v7.widget", "RecyclerView", "ViewHolder");
    public static final ClassName VIEW = ClassName.get("android.view", "View");
    public static final ClassName ON_CLICK = ClassName.get("android.view", "View.OnClickListener");
    public static final ClassName ADAPTER = ClassName.get("android.support.v7.widget", "RecyclerView", "Adapter");
    public static final ClassName VIEW_GROUP = ClassName.get("android.view", "ViewGroup");
    public static final ClassName TEXT_VIEW = ClassName.get("android.widget", "TextView");
    public static final ClassName VIDEO_VIEW = ClassName.get("android.widget", "VideoView");
    public static final ClassName PROGRESS_BAR = ClassName.get("android.widget", "ProgressBar");
    public static final ClassName IMAGE_VIEW = ClassName.get("android.widget", "ImageView");
    public static final ClassName CHECK_BOX = ClassName.get("android.widget", "CheckBox");
    public static final ClassName RADIO_BUTTON = ClassName.get("android.widget", "RadioButton");
    public static final ClassName LINK_PREVIEW = ClassName.get("com.mnayef.library.view", "LinkPreviewView");
    public static final ClassName SIMPLE_DRAWEE_VIEW = ClassName.get("com.facebook.drawee.view", "SimpleDraweeView");
    public static final ClassName FRESCO = ClassName.get("com.facebook.drawee.backends.pipeline", "Fresco");
    public static final ClassName URI_UTIL = ClassName.get("com.facebook.common.util", "UriUtil");
    public static final ClassName PICASSO = ClassName.get("com.squareup.picasso", "Picasso");
    public static final ClassName IMAGE_INFO = ClassName.get("com.facebook.imagepipeline.image", "ImageInfo");
    public static final ClassName BASE_CONTROLLER_LISTENER = ClassName.get("com.facebook.drawee.controller", "BaseControllerListener");
    public static final ClassName REQUEST_CREATOR = ClassName.get("com.squareup.picasso", "RequestCreator");
    public static final ClassName LAYOUT_INFLATER = ClassName.get("android.view", "LayoutInflater");
    public static final ClassName CONTEXT = ClassName.get("android.content", "Context");
    public static final ClassName URI = ClassName.get("android.net", "Uri");
    public static final ClassName PICASSO_CALLBACK = ClassName.get("com.squareup.picasso", "Callback");
    public static final ClassName ANIMATABLE = ClassName.get("android.graphics.drawable", "Animatable");
    public static final ClassName THROWABLE = ClassName.get("java.lang", "Throwable");


    public static TypeName getListClassName(String pkg, String objectName) {
        ClassName model = ClassName.get(pkg, objectName);
        ClassName list = ClassName.get("java.util", "List");
        return ParameterizedTypeName.get(list, model);
    }

    public static TypeName getBaseControllerListener() {
        return ParameterizedTypeName.get(BASE_CONTROLLER_LISTENER, IMAGE_INFO);
    }

    public static ClassName getViewHolder(String adapterName) {
        return ClassName.get("", adapterName + ".ViewHolder");
    }

    public static ClassName getOnItemClickListener(String adapterName) {
        return ClassName.get("", adapterName + ".OnItemClickListener");
    }
}
