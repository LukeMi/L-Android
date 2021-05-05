package com.jeferry.android.widget.room;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student")
public class Student {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
}
