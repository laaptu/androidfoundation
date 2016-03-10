package com.lft.espressointro.twoactivities;

import android.app.Activity;
import android.content.Context;
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
public class SecondActivity extends AppCompatActivity {

    public static String TAG = "StringValue";

    public static Intent launchActivity(Context context, String value) {
        Intent intent = new Intent(context, SecondActivity.class);
        Bundle params = new Bundle();
        params.putString(TAG, value);
        intent.putExtras(params);
        return intent;
    }

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = (TextView) findViewById(R.id.info_txt);
        Bundle params = getIntent().getExtras();
        textView.setText(params.getString(TAG));
    }

    public void btnClick(View view) {
        String text = textView.getText().toString() + " Thank you from second text";
        Bundle params = new Bundle();
        params.putString(TAG, text);
        Intent intent = new Intent();
        intent.putExtras(params);
        setResult(Activity.RESULT_OK, intent);
        this.finish();
    }
}
