package com.lft.trainig.threads.process;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.lft.trainig.threads.BaseActivity;
import com.lft.trainig.threads.R;

import java.io.File;

/**
 * Just a demo class to show
 * <ol>
 * <li>shared pref are written on app private storage</li>
 * <li>database are created on app private storage</li>
 * <li>files are also created on app private storage</li>
 * <li>app private storage is /data/data/packagename</li>
 * </ol>
 */
public class ProcessFileSystemDemo extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_process;
    }

    /**
     * Method to create shared preference at app private storage
     * </br>
     * /data/data/packagename/shared_prefs/
     */
    public void makeSharedPreferences(View view) {
        SharedPreferences preferences = getSharedPreferences("Test", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("This is our first test", "On File System");
        editor.commit();
    }

    /**
     * Method to create a private folder ,only this app has access to
     * </br>
     * or other app having same shared user id</br>
     * /data/data/packagename/
     */
    public void makePrivateFolder(View view) {
        //createAnr();
        File file = getFilesDir();
        File newFile = new File(file, "demo/test");
        newFile.mkdirs();
    }

    private void createAnr() {
        while (true)
            System.out.println("Great");
    }

    /**
     * Method to create a public folder @ /sdcard/Android/data/packagename/files
     */
    public void makePublicFolder(View view) {
        File file = getExternalFilesDir(null);
        File ourFile = new File(file, "Test");
        ourFile.mkdirs();
    }

    private DictionaryOpenHelper dbOpenHelper;


    /**
     * Method to create  a database within app
     */
    public void makeDatabase(View view) {
        if (dbOpenHelper == null) {
            dbOpenHelper = new DictionaryOpenHelper(this);
            dbOpenHelper.getWritableDatabase();
        }
    }
}
