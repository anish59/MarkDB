package com.markdb;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.markdb.helper.dbHelper.AppOpenHelper;
import com.markdb.helper.dbHelper.DataBaseManger;

/**
 * Created by anish on 05-12-2017.
 */

public class AppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initDataBase();
        initStetho();
    }

    private void initStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }

    private void initDataBase() {
        DataBaseManger.initialize(AppOpenHelper.getInstance(this));
        AppOpenHelper.getInstance(this).createDataBase(this);
    }
}

