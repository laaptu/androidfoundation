package com.lft.training.customviewbasic;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import timber.log.Timber;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Probe.deploy(this, new OvermeasureInterceptor(R.id.root_layout));
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //for layoutmodule i.e. second module which demonstrates onLayout pass
        setContentView(R.layout.activity_main_layoutmodule);

        int[] screnDimen = DataHolder.getScreenDimension();
        Timber.d("screenWidth , screenHeight = %d , %d", screnDimen[0], screnDimen[1]);
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
