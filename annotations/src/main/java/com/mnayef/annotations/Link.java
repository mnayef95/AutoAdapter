package com.mnayef.annotations;

/**
 * Created by Mohamed Hamdan on 2017-Jun-04.
 * mohamed.nayef95@gmail.com
 */
public @interface Link {

    int value();

    String invalidLinkMag() default "";

    int invalidLinkMagRes() default 0;

    String failedLoadMag() default "";

    int failedLoadMagRes() default 0;

}
