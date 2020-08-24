package com.jeferry.android.widget.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

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
