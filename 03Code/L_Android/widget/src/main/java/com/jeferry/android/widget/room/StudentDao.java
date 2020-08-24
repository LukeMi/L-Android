package com.jeferry.android.widget.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface StudentDao {
    @Query("SELECT * FROM student")
    Flowable<List<Student>> getAll();

    @Insert()
    long insert(Student student);
}
