package com.example.myroomdatabase;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MainDao {
    //insert query
    @Insert(onConflict = REPLACE)
    void insert(MainData mainData);
    //delete query
    @Delete
    void delete(MainData mainData);
    //delete all query
    @Delete
    void reset(List<MainData> list);
    //update query
    @Query("update `table name` set text=:stex where id=:sid")
    void update (int sid, String stex);
    //get all data
    @Query("Select * from `table name`")
    List<MainData> getAllData();

}
