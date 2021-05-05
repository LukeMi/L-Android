package com.lukemi.android.tutorial.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.List;

@Entity(tableName = "bookTable")
public class BookEntity {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public String name;

    public double price;

    public Date date;

    public List<AuthorEntity> list;
}
