package com.lft.espressointro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by laaptu on 3/10/16.
 */
public class SimpleActivity extends AppCompatActivity {

    TextView infoTxt;
    EditText infoEditTxt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        infoTxt = (TextView) findViewById(R.id.info_txt);
        infoEditTxt = (EditText) findViewById(R.id.info_edittxt);
    }

    public void btnClick(View view) {
        String randomValue = "This is random value "+String.valueOf(Math.random()*10000);
        infoTxt.setText(randomValue);
        infoEditTxt.setText(randomValue);
    }
}
