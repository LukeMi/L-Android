package com.lukemi.android.tutorial.room;

import android.annotation.SuppressLint;
import android.arch.core.executor.ArchTaskExecutor;

import com.socks.library.KLog;

import java.util.List;
import java.util.concurrent.Executor;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LocalBookSource implements BookSource {

    private final String TAG = LocalBookSource.class.getSimpleName();

    private final BookDao dao;

    @SuppressLint("RestrictedApi")
    private Executor mQueryExecutor = ArchTaskExecutor.getIOThreadExecutor();

    public LocalBookSource(BookDao dao) {
        this.dao = dao;
    }

    @Override
    public Flowable<List<BookEntity>> getAll() {
        return dao.getAll();
    }

    @Override
    public void deleteAllBook() {
        Disposable disposable = Observable.create(s -> {
            dao.deleteAll();
            s.onComplete();
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(obj -> {

                        }, throwable -> KLog.d(TAG, throwable.getMessage())
                        , () -> KLog.d(TAG, "onComplete"));
    }

    @Override
    public void deleteBook(BookEntity name) {
        Disposable disposable = Observable.create(s -> {
            dao.delete(name);
            s.onComplete();
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(obj -> {

                        }, throwable -> KLog.d(TAG, throwable.getMessage())
                        , () -> KLog.d(TAG, "onComplete"));
    }

    @SuppressLint("CheckResult")
    @Override
    public void insert(BookEntity book, RoomCallback callback) {
        if (callback != null) {
            callback.onStart();
        }
        mQueryExecutor.execute(() -> {
            Observable.just(dao.insert(book))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aLong -> {
                                if (callback != null) {
                                    callback.onSuccess(aLong);
                                }
                            }
                            , throwable -> {
                                if (callback != null) {
                                    callback.onError(throwable);
                                    callback.onComplete();
                                }
                            }
                            , () -> {
                                if (callback != null) {
                                    callback.onComplete();
                                }
                            });

        });
    }
}
