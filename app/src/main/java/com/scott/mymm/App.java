package com.scott.mymm;

import android.app.Application;

/**
 * time:2020/4/15
 **/
public class App extends Application {
    public static App context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;

    }
    public static App getContext(){
        return context;
    }
}
