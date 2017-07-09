package com.mnayef.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Mohamed Hamdan on 2017-May-21.
 * mohamed.nayef95@gmail.com
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface Adapter {
    String adapterName();

    int layoutId();

    boolean clickable() default false;
}
