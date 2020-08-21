package com.lukemi.android.tutorial.room;

import java.util.List;

import io.reactivex.Flowable;

public class LocalBookSource implements BookSource {

    private final BookDao dao;

    public LocalBookSource(BookDao dao) {
        this.dao = dao;
    }

    @Override
    public Flowable<List<BookEntity>> getAll() {
        return dao.getAll();
    }

    @Override
    public void deleteAllBook() {
        dao.deleteAll();
    }

    @Override
    public void deleteBook(BookEntity name) {
        dao.delete(name);
    }

    @Override
    public void insert(BookEntity book) {
        dao.insert(book);
    }
}
