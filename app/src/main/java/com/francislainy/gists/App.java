package com.francislainy.gists;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    private static App sInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
    }

    public static Context getContext(){
        return sInstance.getApplicationContext();
    }

    public static App getInstance() {
        return sInstance;
    }
}