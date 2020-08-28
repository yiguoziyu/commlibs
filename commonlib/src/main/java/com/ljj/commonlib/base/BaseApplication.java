package com.ljj.commonlib.base;

import android.app.Application;

public class BaseApplication extends Application {

    public static Application application;
    public static String baseUrl;
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

}
