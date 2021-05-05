package com.lukemi.android.tutorial.room;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface BookDao {

    @Query("SELECT * FROM bookTable")
    Flowable<List<BookEntity>> getAll();

    @Query("DELETE  FROM bookTable")
    void deleteAll();

    @Delete
    void delete(BookEntity bookEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(BookEntity bookEntity);

}
