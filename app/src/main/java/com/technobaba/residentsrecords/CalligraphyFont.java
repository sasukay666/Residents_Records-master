package com.technobaba.residentsrecords;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by shubhamrawat on 27/07/17.
 */

public class CalligraphyFont extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Aileron-Regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
