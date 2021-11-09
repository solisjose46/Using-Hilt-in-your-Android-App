package com.example.javahiltdemo.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "logs")
public class Log {
    public String message;
    public long timeStamp;
    @PrimaryKey(autoGenerate = true)
    long id = 0;

    public Log(String message, long timeStamp){
        this.message = message;
        this.timeStamp = timeStamp;
    }
}
