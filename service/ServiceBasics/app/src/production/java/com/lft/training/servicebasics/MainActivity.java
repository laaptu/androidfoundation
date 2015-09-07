package com.lft.training.servicebasics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.lft.training.servicebasics.bound.BindServiceActivity;
import com.lft.training.servicebasics.broadcasts.BroadCastActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //goToBoundServiceActivity();
        //goToBasicServiceActivity();
        testTrace();
        goToBroadCastActivity();
    }

    private void testTrace() {
        for (int i = 0; i < 1000000; i++) {
            System.out.println(i);
        }
    }


    private void goToBroadCastActivity() {
        startActivity(new Intent(this, BroadCastActivity.class));
        this.finish();
    }

    private void goToBoundServiceActivity() {
        startActivity(new Intent(this, BindServiceActivity.class));
        this.finish();
    }

    private void goToBasicServiceActivity() {
        startActivity(new Intent(this, BasicServiceActivity.class));
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
