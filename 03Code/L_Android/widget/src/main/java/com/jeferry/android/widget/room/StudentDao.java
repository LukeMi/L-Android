package com.jeferry.android.widget.room;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface StudentDao {
    @Query("SELECT * FROM student")
    Flowable<List<Student>> getAll();

    @Insert()
    long insert(Student student);
}
