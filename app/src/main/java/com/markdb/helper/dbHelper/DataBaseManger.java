package com.markdb.helper.dbHelper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by anish on 03-07-2017.
 */

public class DataBaseManger {
    private static AtomicInteger openCount = new AtomicInteger();
    private static DataBaseManger instance;
    private static AppOpenHelper openHelper;
    private static SQLiteDatabase database;

    public static synchronized DataBaseManger getInstance() {
        if (null == instance) {
            throw new IllegalStateException(DataBaseManger.class.getSimpleName()
                    + " is not initialized, call initialize(..) method first.");
        }
        return instance;
    }

    public static synchronized void initialize(AppOpenHelper helper) {
        if (null == instance) {
            instance = new DataBaseManger();
            // sqlBrite = new SqlBrite.Builder().build();

        }
        openHelper = helper;
    }

    public static void closeCursor(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    public synchronized void closeDatabase() {
        if (openCount.decrementAndGet() == 0) {
            database.close();
        }
    }

    public synchronized SQLiteDatabase openDatabase() {
        if (openCount.incrementAndGet() == 1) {
            database = openHelper.getWritableDatabase();
        }
        return database;
    }

}
