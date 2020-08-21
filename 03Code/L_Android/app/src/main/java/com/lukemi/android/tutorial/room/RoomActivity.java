package com.lukemi.android.tutorial.room;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lukemi.android.tutorial.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RoomActivity extends AppCompatActivity {

    private TextView mTvAdd;
    private TextView mTvDeleteAll;
    private RecyclerView mRv;
    private LocalBookSource localBookSource;
    private RoomAdapter roomAdapter;
    private Disposable subscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        initDB();
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscribe != null && !subscribe.isDisposed()) {
            subscribe.dispose();
        }
    }

    private void initDB() {
        BookDatabase instance = BookDatabase.getInstance(getApplicationContext());
        BookDao bookDao = instance.bookDao();
        localBookSource = new LocalBookSource(bookDao);
    }

    private void initView() {
        mTvAdd = findViewById(R.id.tv_add);
        mTvDeleteAll = findViewById(R.id.tv_delete_all);
        mRv = findViewById(R.id.rv);
        mTvAdd.setOnClickListener(this::onClick);
        mTvDeleteAll.setOnClickListener(this::onClick);

        roomAdapter = new RoomAdapter();
        roomAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            int id = view.getId();
            if (id == R.id.btn_delete) {
                BookEntity item = (BookEntity) adapter.getItem(position);
                localBookSource.deleteBook(item);
                refresh();
            }
        });
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(roomAdapter);
        refresh();
    }

    private void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_add) {
            localBookSource.insert(getBookEntity());
            refresh();
        } else if (id == R.id.tv_delete_all) {
            localBookSource.deleteAllBook();
            refresh();
        }
    }

    @NotNull
    private BookEntity getBookEntity() {
        long time = (long) (System.currentTimeMillis() - Math.random() * 1000_000_000 * 100);
        Date date = new Date(time);
        BookEntity entity = new BookEntity();
        entity.name = "随机数 ：" + Math.random();
        entity.price = Math.random() * 100;
        entity.date = date;
        entity.list = getAuthor();
        return entity;
    }

    private List<AuthorEntity> getAuthor() {
        ArrayList<AuthorEntity> list = new ArrayList<>();
        double random = Math.random();
        int i = (int) (random * 10);
        for (int j = 0; j < i; j++) {
            AuthorEntity entity = new AuthorEntity();
            entity.name = "路遥：" + random;
            entity.age = 40 + i;
            list.add(entity);
        }
        return list;
    }

    private void refresh() {
        if (subscribe != null && !subscribe.isDisposed()) {
            subscribe.dispose();
        }
        subscribe = localBookSource.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                    roomAdapter.replaceData(list);
                }, throwable -> {

                });
    }
}