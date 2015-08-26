package com.lft.trainig.threads;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple class which saves some value to shared preferences</br>
 * since this app and our main app has same shared user id i.e. com.lft,
 * both app can access each other private storage
 */

public class SecondMainActivity extends AppCompatActivity {


    @Bind(R.id.info_txt)
    EditText infoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    /**
     * Saving some value to shared preferences</br>
     * this value will be accessed from </br>
     * production flavor app i.e. com.lft.trainig.threads
     */
    public void saveToPreferences(View view) {
        String text = infoText.getText().toString();
        SharedPreferences preferences = getSharedPreferences("Thread1", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("MYVALUE", text);
        editor.commit();
    }
}
