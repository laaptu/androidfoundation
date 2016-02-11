package com.lft.realmtest.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by laaptu on 2/11/16.
 */
public class FifthModel extends RealmObject {
    @PrimaryKey
    private int id;
    private String modelName;

    //realm object requires an empty constructor as well
    public FifthModel(){

    }
    public FifthModel(int id, String modelName) {
        setId(id);
        setModelName(modelName);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
