package com.jeferry.android.widget.room;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class LocalStudentSource {
    private final StudentDao dao;

    public LocalStudentSource(StudentDao dao) {
        this.dao = dao;
    }

    public Flowable<List<Student>> getAll() {
        return dao.getAll();
    }

    public void insert(Student student, Action action) {
        Observable<Long> objectObservable = Observable.create(emitter -> {
            emitter.onNext(dao.insert(student));
        });
        objectObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> action.run()
                        , throwable -> action.run()
                        , () -> {
                        });

    }
}
