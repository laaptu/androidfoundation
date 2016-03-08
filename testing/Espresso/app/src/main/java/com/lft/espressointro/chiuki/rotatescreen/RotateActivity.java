package com.lft.espressointro.chiuki.rotatescreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lft.espressointro.R;

public class RotateActivity extends AppCompatActivity {
    private static final String KEY_COUNT = "count";

    private int count = 0;
    private TextView countView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate);
        countView = (TextView) findViewById(R.id.count);

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt(KEY_COUNT, 0);
        }

        updateCount();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNT, count);
    }

    public void increment(View v) {
        count += 1;
        updateCount();
    }

    private void updateCount() {
        countView.setText(String.valueOf(count));
    }
}