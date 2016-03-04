package com.lft.espressointro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.lft.espressointro.chiuki.list.SimpleListActivity;
import com.lft.espressointro.tasko.TaskMainActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //goToTaskActivity();
        goToSimpleListActivity();
    }

    private void goToTaskActivity() {
        startActivity(new Intent(this, TaskMainActivity.class));
        this.finish();
    }

    private void goToSimpleListActivity() {
        startActivity(new Intent(this, SimpleListActivity.class));
        this.finish();
    }

    public void btnClick(View view) {
        //navigate to next activity
        Toast.makeText(this, "Button is clicked", Toast.LENGTH_SHORT).show();
    }
}
