package com.markdb.helper.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by anish on 03-07-2017.
 */

public class AppOpenHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "BudgetPlanner.db";
    public static final int DB_VERSION = 1;
    private static AppOpenHelper instance;
    private static final String DATABASE_PATH = "/data/data/com.markdb/databases/";
    public static final String DB_FullPATH = DATABASE_PATH + DB_NAME;

    public AppOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static AppOpenHelper getInstance(Context context) {
        if (null == instance) {
            instance = new AppOpenHelper(context);
        }
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createDataBase(Context context) {

        boolean dbExist = checkDataBase();
        if (dbExist) {
        } else {
            this.getReadableDatabase();
            try {
                copyDataBase(context);
            } catch (IOException e) {
                e.printStackTrace();
                throw new Error("Error copying database: " + e.getMessage());
            }
        }

    }


    private void copyDataBase(Context context) throws IOException {

        InputStream myInput = context.getAssets().open(DB_NAME);
        String outFileName = DATABASE_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;

        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    private boolean checkDataBase() {

        File folder = new File(DATABASE_PATH);
        if (!folder.exists()) {
            folder.mkdir();
        }
        File dbFile = new File(DATABASE_PATH + DB_NAME);
        return dbFile.exists();
    }
}
