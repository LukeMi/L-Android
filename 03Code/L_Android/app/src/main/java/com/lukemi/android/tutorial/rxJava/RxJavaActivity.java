package com.lukemi.android.tutorial.rxJava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lukemi.android.common.util.Logcat;
import com.lukemi.android.tutorial.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity {
    int i = 0;
    private final String TAG = this.getClass().getSimpleName();
    private Disposable subscribe = null;
    private List<Disposable> list = new ArrayList<>();
    private Disposable intervalDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
//     cc();

//        commonOP();
//        threadTransform();
//        map();
//        concatMap();
//        flatMap();
//        map();
//        just();
//        amb();
//        rang();
//        disposableObserver();

//        compose();
        interval();
    }

    private void cc() {
        Observable<String> just = Observable
                .just("hello", "java", "ios");
        Disposable accept = just.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                i++;
                Log.i("accept", s + " - " + i + " ");
            }
        });
        accept.dispose();
    }

    private void just() {
        Observable.just("jjjjj", "22")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "----just-----subscribe----" + "thread_name : " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.i(TAG, "----just-----onNext----" + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "----just-----onError----" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "----just-----onComplete----");
                    }
                });
    }

    private void amb() {
        Disposable subscribe = Observable.ambArray(new ObservableSource<String>() {
            @Override
            public void subscribe(Observer<? super String> observer) {
                Log.i(TAG, "string ");
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, "accept ");
            }
        });
    }

    @Override
    protected void onDestroy() {
        destroyDisposable();
        super.onDestroy();
    }

    private void destroyDisposable() {
        for (int j = 0; j < list.size(); j++) {
            Disposable disposable = list.get(j);
            if (disposable != null && !disposable.isDisposed()) {
                disposable.dispose();
            }
        }

        if (null != intervalDisposable && !intervalDisposable.isDisposed()) {
            intervalDisposable.dispose();
        }
    }

    private void commonOP() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
//                emitter.onComplete();
                emitter.onError(new Exception("test error")); // 当 error 下面都不在发送
                emitter.onNext(2);
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "\n");
                Log.i(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG, "onNext : " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError" + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
            }
        };

        observable.subscribe(observer);
    }

    private void threadTransform() {
        // 线程间变换
        Observable<Integer> transThreadObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(9527);
                Log.i(TAG, "subscribe thread : " + Thread.currentThread().getName());
            }
        });

        final Disposable threadDisposable = transThreadObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "accept thread : " + Thread.currentThread().getName());
                      /* if (threadDisposable != null){
                           threadDisposable.dispose();
                       }*/
                    }
                });
        list.add(threadDisposable);
    }

    /**
     * concatMap 操作符
     */
    private void concatMap() {
        // Map FlatMap concatMap 操作符
        Observable<Integer> mapObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(123);
                emitter.onNext(113);
                emitter.onNext(133);
            }
        });
        Disposable conCatMapDisposable = mapObservable.concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(final Integer integer) throws Exception {
                return Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        for (int j = 0; j < 3; j++) {
                            String str = "the result is " + integer;
                            emitter.onNext(str);
                        }
                    }
                }).delay(10, TimeUnit.SECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, "concatMap : " + s);
            }
        });
        list.add(conCatMapDisposable);
    }

    /**
     * flatMap 操作符
     */
    private void flatMap() {
        Observable<Integer> mapObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(123);
                emitter.onNext(113);
                emitter.onNext(133);
            }
        });

        Disposable flatMapDisposable = mapObservable.flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(final Integer integer) throws Exception {

                return Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        for (int j = 0; j < 4; j++) {
                            String str = "the result is " + integer;
                            emitter.onNext(str);
                        }
                    }
                }).delay(10, TimeUnit.SECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String o) throws Exception {
                Log.i(TAG, "flatMap : " + o);
            }
        });
        list.add(flatMapDisposable);
    }

    /**
     * map 操作符
     */
    private void map() {
        ObservableOnSubscribe<Integer> observableOnSubscribe = new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int j = 0; j < 15; j++) {
                    if (j % 3 == 1) {
                        emitter.onNext(j);
                    }
                }
            }
        };
        Disposable subscribe = Observable
                .create(observableOnSubscribe)
                .map(new Function<Integer, Boolean>() {
                    @Override
                    public Boolean apply(Integer integer) {
                        Log.e("TAG", "integer : " + integer);
                        return integer % 10 == 0;
                    }
                }).subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) {
                        if (aBoolean) {
                            Log.e("TAG", "aBoolean : " + aBoolean);
                        }
                    }
                });
        list.add(subscribe);
    }

    private void rang() {
        Observable.range(2, 6)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Logcat.log("onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Logcat.log("onNext : " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logcat.log("onError");
                    }

                    @Override
                    public void onComplete() {
                        Logcat.log("onComplete");
                    }
                });
    }


    private void disposableObserver() {

        DisposableObserver<Integer> disposableObserver = Observable
                .range(1, 7)
                .delay(1, TimeUnit.SECONDS)
                .skipLast(3)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        Logcat.log("onTest method : " + integer + " ;thread name is " + Thread.currentThread().getName());
                        return integer > 3;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Integer>() {
                    @Override
                    public void onStart() {
                        System.out.println("onStart!" + " ;thread name is " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(Integer t) {
                        System.out.println("onNext : " + t);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete!");
                    }
                });
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        disposableObserver.dispose();
    }


    private void compose() {
        Disposable subscribe = Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            emitter.onNext(30);
            emitter.onNext(29);
        }).compose(upstream -> upstream.map(integer -> {
            Logcat.log("integer = " + integer);
            return integer > 29;
        }).subscribeOn(Schedulers.io())).subscribe(aBoolean -> Logcat.log("aBoolean : " + aBoolean));
//        subscribe.dispose();
    }

    private void interval() {
        intervalDisposable = Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribe(aLong -> System.out.println("aLong : " + aLong));
    }

}
