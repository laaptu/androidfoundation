package com.lft.realmtest;

import android.app.Application;
import android.content.Context;

import com.lft.realmtest.model.AllModel;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by laaptu on 2/10/16.
 */
public class MainApplication extends Application {
    private static RealmConfiguration realmConfiguration;

    @Override
    public void onCreate() {
        super.onCreate();
        initRealmConfig(this);
    }

    private static void initRealmConfig(Context context) {
        realmConfiguration = new RealmConfiguration.Builder(context)
                .name("test.realm")
                .schemaVersion(3)
                .migration(realmMigration)
                .setModules(new AllModel()).build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static RealmConfiguration getRealmConfig() {
        return realmConfiguration;
    }

    private static RealmMigration realmMigration = new RealmMigration() {
        @Override
        public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
            RealmSchema realmSchema = realm.getSchema();
//            if (oldVersion == 0) {
//                realmSchema.create("ThirdModel")
//                        .addField("name", String.class);
//            }

            if (oldVersion == 1) {
                realmSchema.create("FourthModel")
                        .addField("model", String.class);
            }

            if (oldVersion == 2) {
                realmSchema.create("FifthModel")
                        .addField("id", Integer.class)
                        .addField("modelName", String.class);
            }
        }
    };
}
