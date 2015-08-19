package com.lft.trainig.threads.process;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.lft.trainig.threads.BaseActivity;
import com.lft.trainig.threads.R;

import java.io.File;

/**
 * Created by laaptu on 8/19/15.
 */
public class ProcessActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_process;
    }

    public void makeSharedPreferences(View view) {
        SharedPreferences preferences = getSharedPreferences("Test", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("This is our first test", "On File System");
        editor.commit();
    }

    public void makePrivateFolder(View view) {
        File file = getFilesDir();
        File newFile = new File(file, "demo/test");
        newFile.mkdirs();

    }

    public void makePublicFolder(View view) {
        File file = getExternalFilesDir(null);
        File ourFile = new File(file, "Test");
        ourFile.mkdirs();
    }

    private DictionaryOpenHelper dbOpenHelper;

    public void makeDatabase(View view) {
        if (dbOpenHelper == null) {
            dbOpenHelper = new DictionaryOpenHelper(this);
            dbOpenHelper.getWritableDatabase();
        }
    }
}
