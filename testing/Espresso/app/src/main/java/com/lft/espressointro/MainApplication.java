package com.lft.espressointro;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by laaptu on 3/2/16.
 * https://github.com/CasterIO/Tasko
 */
public class MainApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initRealmConfig(this);
    }

    public static Context getContext() {
        return context;
    }

    private void initRealmConfig(Context context) {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(context)
                .name("Espresso.realm").build();
        Realm.deleteRealm(realmConfiguration);
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
