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
                .schemaVersion(21)
                .migration(realmMigration)
                .setModules(new AllModel()).build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static RealmConfiguration getRealmConfig() {
        System.out.println("Real Configuration ==null " + String.valueOf(realmConfiguration == null));
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
            //if any new realms are created like any class is added
            if (oldVersion == 2) {
                realmSchema.create("FifthModel")
                        .addField("id", Integer.class)
                        .addField("modelName", String.class);
            }


            //remove any field or property of class,don't forget to update the schema version as well
            if (oldVersion == 17) {
                realmSchema.get("Person").removeField("address");
            }

            if (oldVersion == 19) {
                realmSchema.get("Person").addField("address", String.class);
            }

            if (oldVersion == 20) {
                realmSchema.get("Person").addField("phNum",int.class)
                .removeField("phNo");
            }

            System.out.println("OldVersion = " + oldVersion + " NewVersion = " + newVersion);
        }
    };
}
