package com.lukemi.android.tutorial.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

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
