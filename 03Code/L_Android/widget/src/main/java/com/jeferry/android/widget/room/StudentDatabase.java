package com.jeferry.android.widget.room;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.socks.library.KLog;

@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class StudentDatabase extends RoomDatabase {

    private static final String TAG = StudentDatabase.class.getSimpleName();

    public static StudentDatabase INSTANCE;

    public abstract StudentDao studentDao();

    public static StudentDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (StudentDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room
                            .databaseBuilder(context, StudentDatabase.class, "d.db")
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    KLog.d(TAG, "db onCreate");
                                }

                                @Override
                                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                                    super.onOpen(db);
                                    super.onCreate(db);
                                    KLog.d(TAG, "db onOpen");
                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
