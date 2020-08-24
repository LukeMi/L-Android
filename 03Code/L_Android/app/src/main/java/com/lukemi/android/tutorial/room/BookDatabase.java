package com.lukemi.android.tutorial.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities = {BookEntity.class}, version = 1, exportSchema = false)
@TypeConverters({BookConverters.class})
public abstract class BookDatabase extends RoomDatabase {

    public static BookDatabase INSTANCE;

    public BookDatabase() {

    }

    public abstract BookDao bookDao();

    public static BookDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (BookDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            BookDatabase.class,
                            "book.dp")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
