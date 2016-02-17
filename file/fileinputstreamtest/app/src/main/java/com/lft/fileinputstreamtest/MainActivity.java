package com.lft.fileinputstreamtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_write:
                writeTextFile();
                break;
            case R.id.btn_write_byte:
                writeSimplyBytes();
                break;
        }
    }

    //http://unicode-table.com/en/search/?q=T
    // Equivalent int value  =  T   h   i   s      i   s      h   e   l    l   0     \n
    private int[] allTxtValue = {84, 104, 105, 115, 32, 105, 115, 32, 104, 101, 108, 108, 111, 32, 10};

    private void writeTextFile() {
        //getFilesDir() = /data/data/packagename/files/
        File file = new File(getFilesDir(), "hello.txt");
        try {
            InputStream is = getAssets().open("hello.txt");
            /**
             * Doing this we get stream of all integers which are basically the
             * decimal representation of byte(not our Java byte). So all the text are
             * now represented in integer(unicode value)*/
            //Just to check the int value
//            int a = 1;
//            while (a > 0) {
//                //here we are just reading int value
//                a = is.read();
//                Timber.d("byte value or int value =%d", a);
//            }

            OutputStream outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            while (is.read(buffer) > 0) {
                outputStream.write(buffer);
            }
            outputStream.flush();
            outputStream.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void writeSimplyBytes() {
        File file = new File(getFilesDir(), "hello_byte.txt");
        try {
            OutputStream outputStream = new FileOutputStream(file);
            for (int ints : allTxtValue) {
                outputStream.write(ints);
            }
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
