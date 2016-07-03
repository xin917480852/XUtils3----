package com.example.xutils3_demo;

import android.app.Application;

import org.xutils.x;

/**
 * Created by 小新 on 2016/7/2.
 */
public class Xutils3Test extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
