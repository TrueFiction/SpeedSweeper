package com.app.speedsweeper.speedsweeper;

import android.app.Application;
import android.content.Context;

public class ThisApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        ThisApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return ThisApplication.context;
    }

}
