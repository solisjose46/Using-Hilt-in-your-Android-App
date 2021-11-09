package com.example.javahiltdemo;

import android.app.Application;

public class LogApplication extends Application {

    public ServiceLocator serviceLocator;

    @Override
    public void onCreate(){
        super.onCreate();
        serviceLocator = new ServiceLocator(this);
    }
}
