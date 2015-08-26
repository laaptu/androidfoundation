package com.lft.trainig.threads;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lft.trainig.threads.multithread.ExecutorServiceMultiThreading;

/**
 * This is just a navigation class i.e. an activity which helps to
 * navigate to different activities
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (true) {
            Intent intent = new Intent(this, ExecutorServiceMultiThreading.class);
            startActivity(intent);
            this.finish();
            return;
        }
    }

}
