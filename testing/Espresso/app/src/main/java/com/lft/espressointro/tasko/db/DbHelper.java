package com.lft.espressointro.tasko.db;

import com.lft.espressointro.tasko.models.Task;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by laaptu on 3/3/16.
 */
public class DbHelper {

    static volatile DbHelper instance = null;

    private DbHelper() {

    }

    public static DbHelper getInstance() {
        if (instance == null) {
            synchronized (DbHelper.class) {
                if (instance == null)
                    instance = new DbHelper();
            }
        }
        return instance;
    }

    public boolean checkIfTaskIsAdded(String taskName, String taskDescription) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Task> getTasks = realm.where(Task.class).equalTo("name", taskName).equalTo("description", taskDescription).findAll();
        boolean isAdded = getTasks.size() > 0;
        realm.close();
        return isAdded;

    }
}
