package com.lukemi.android.tutorial.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

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
