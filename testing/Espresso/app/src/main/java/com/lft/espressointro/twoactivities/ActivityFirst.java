package com.lft.espressointro.twoactivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lft.espressointro.R;

/**
 * Created by laaptu on 3/10/16.
 */
public class ActivityFirst extends AppCompatActivity {


    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        textView = (TextView) findViewById(R.id.info_txt);
        textView.setText("This is from first");
    }

    public void btnClick(View view) {
        startActivityForResult(SecondActivity.launchActivity(this, textView.getText().toString()), 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == Activity.RESULT_OK) {
            Bundle params = data.getExtras();
            textView.setText(params.getString(SecondActivity.TAG));
        }
    }
}
