package com.ljj.commonlib.base;

import android.app.Application;

public class BaseApplication extends Application {

    public static Application application;
    private String baseUrl;
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public String getBaseUrl() {
        return baseUrl == null ? "" : baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
