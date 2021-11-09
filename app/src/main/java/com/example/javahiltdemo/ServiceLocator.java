package com.example.javahiltdemo;

import android.app.Application;

import androidx.fragment.app.FragmentActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.javahiltdemo.data.AppDatabase;
import com.example.javahiltdemo.data.LoggerLocalDataSource;
import com.example.javahiltdemo.navigator.AppNavigatorImpl;
import com.example.javahiltdemo.util.DateFormatter;

public class ServiceLocator {
    AppDatabase logsDatabase;
    public LoggerLocalDataSource loggerLocalDataSource;

    ServiceLocator(Application applicationContext){
        logsDatabase = Room.databaseBuilder(applicationContext,
                AppDatabase.class,
                "logging.db").build();
        loggerLocalDataSource = new LoggerLocalDataSource(logsDatabase.logDao()); // pass logsDatabase.logDao()
    }

    public DateFormatter provideDateFormatter(){
        return new DateFormatter();
    }

    public AppNavigatorImpl provideNavigator(FragmentActivity activity){
        return new AppNavigatorImpl(activity);
    }
}
