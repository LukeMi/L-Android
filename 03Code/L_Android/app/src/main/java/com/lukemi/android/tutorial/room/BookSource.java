package com.lukemi.android.tutorial.room;

import java.util.List;

import io.reactivex.Flowable;

public interface BookSource {

    Flowable<List<BookEntity>> getAll();

    void deleteAllBook();

    void deleteBook(BookEntity name);

    void insert(BookEntity book, RoomCallback callback);
}
