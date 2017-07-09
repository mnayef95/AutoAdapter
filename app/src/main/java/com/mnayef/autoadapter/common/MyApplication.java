package com.mnayef.autoadapter.common;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Mohamed Hamdan on 2017-May-28.
 * mohamed.nayef95@gmail.com
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);
    }
}
