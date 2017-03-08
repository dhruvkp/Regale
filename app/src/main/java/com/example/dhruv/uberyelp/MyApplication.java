package com.example.dhruv.uberyelp;

import android.app.Application;
import android.content.Context;

/**
 * Created by dhruv on 3/4/17.
 */

public class MyApplication extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}
