package com.example.root.projectfsoft.app;

import android.app.Activity;
import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by root on 23/12/2016.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration configuration=new RealmConfiguration
                                         .Builder(this)
                                         .name(Realm.DEFAULT_REALM_NAME)
                                         .schemaVersion(0)
                                         .deleteRealmIfMigrationNeeded()
                                         .build();
        Realm.setDefaultConfiguration(configuration);

    }
}
