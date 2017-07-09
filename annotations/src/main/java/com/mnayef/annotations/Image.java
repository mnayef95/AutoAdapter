package com.mnayef.annotations;

import com.mnayef.annotations.enums.ImageLibraries;
import com.mnayef.annotations.enums.ImageSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Mohamed Hamdan on 2017-May-21.
 * mohamed.nayef95@gmail.com
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.CLASS)
public @interface Image {
    int value();

    int progressId() default -1;

    boolean fit() default true;

    boolean gif() default false;

    boolean resize() default false;

    int height() default 0;

    int width() default 0;

    ImageSource source();

    ImageLibraries library() default ImageLibraries.PICASSO;
}
