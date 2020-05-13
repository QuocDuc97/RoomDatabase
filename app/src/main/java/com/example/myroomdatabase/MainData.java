package com.example.myroomdatabase;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//define table name
@Entity (tableName = "table name")
public class MainData implements Serializable {

    //create id column
    //key auto
    @PrimaryKey (autoGenerate = true)
    private int id;
    @ColumnInfo(name = "text")
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
