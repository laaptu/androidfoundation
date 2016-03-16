package com.lft.espressointro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.lft.espressointro.chiuki.idling.IdlingActivity;
import com.lft.espressointro.chiuki.list.SimpleListActivity;
import com.lft.espressointro.chiuki.recylerview.RecyclerViewActivity;
import com.lft.espressointro.chiuki.rotatescreen.RotateActivity;
import com.lft.espressointro.intent.LoadDataActivity;
import com.lft.espressointro.tasko.TaskMainActivity;
import com.lft.espressointro.twoactivities.ActivityFirst;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //goToTaskActivity();
        //goToSimpleListActivity();
//        goToRecyclerViewActivity();
//        goToRotateActivity();
//        goToSimpleActivity();
//        goToIdlingActivity();
//        goToTwoActivities();
        goToLoadDataActivity();
    }

    private void goToLoadDataActivity() {
        startActivity(new Intent(this, LoadDataActivity.class));
        this.finish();
    }

    private void goToTwoActivities() {
        startActivity(new Intent(this, ActivityFirst.class));
        this.finish();
    }

    private void goToIdlingActivity() {
        startActivity(new Intent(this, IdlingActivity.class));
        this.finish();
    }

    private void goToSimpleActivity() {
        startActivity(new Intent(this, SimpleActivity.class));
        this.finish();
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

    private void goToRecyclerViewActivity() {
        startActivity(RecyclerViewActivity.launchActivity(this, 10));
        this.finish();
    }

    private void goToRotateActivity() {
        startActivity(new Intent(this, RotateActivity.class));
        this.finish();
    }
}
