package com.example.myroomdatabase;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//add database
@Database(entities = MainData.class, version = 1, exportSchema = false)
public abstract class RoomBD extends RoomDatabase {
    //create database
    public static RoomBD database;
    //define database name
    public final static String DATABASE_NAME = "database";

    //
    public static synchronized RoomBD getInstan(Context context) {
        //check condition
        if (database == null) {
            //when database is null
            //initilaze database
            database = Room.databaseBuilder(context.getApplicationContext(), RoomBD.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        //return database
        return database;
    }

    //create Dao
    public abstract MainDao mainDao();
}
