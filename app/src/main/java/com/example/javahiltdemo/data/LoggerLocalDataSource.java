package com.example.javahiltdemo.data;

import android.os.Handler;
import android.os.Looper;

import com.example.javahiltdemo.callback.LogCallback;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoggerLocalDataSource{
    private ExecutorService executorService;
    private Handler mainThreadHandler;
    private LogDao logDao;

    public LoggerLocalDataSource(LogDao logDao){
        this.logDao = logDao;
        executorService = Executors.newFixedThreadPool(4);
        mainThreadHandler = new Handler(Looper.getMainLooper());
    }
    public void addLog(String msg){
        executorService.execute(()->{
            logDao.insertAll(new Log(msg, System.currentTimeMillis()));
        });
    }

    public void getAllLogs(LogCallback callback){
        executorService.execute(()->{
            List<Log> logs = logDao.getAll();
            mainThreadHandler.post(()->{
                callback.LogCallback(logs);
            });
        });
    }

    public void removeLogs(){
        executorService.execute(()->{
            logDao.nukeTable();
        });
    }
}
