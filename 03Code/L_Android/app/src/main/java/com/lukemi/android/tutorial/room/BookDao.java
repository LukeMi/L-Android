package com.lukemi.android.tutorial.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

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
