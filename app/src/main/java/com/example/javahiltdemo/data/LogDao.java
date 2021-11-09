package com.example.javahiltdemo.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
interface LogDao {

    @Query("SELECT * FROM logs ORDER BY id DESC")
    List<Log> getAll();

    @Insert
    void insertAll(Log...Logs);

    @Query("DELETE FROM logs")
    void nukeTable();
}
