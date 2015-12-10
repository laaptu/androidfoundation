package com.lft.training.customviewbasic;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.lft.training.customviewbasic.widgets.CustomView;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends Activity {

    @Bind(R.id.customview)
    CustomView customView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Probe.deploy(this, new OvermeasureInterceptor(R.id.root_layout));
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main_draw);
        //for layoutmodule i.e. second module which demonstrates onLayout pass
        //setContentView(R.layout.activity_main_layoutmodule);

        ButterKnife.bind(this);

        int[] screnDimen = DataHolder.getScreenDimension();
        Timber.d("screenWidth , screenHeight = %d , %d", screnDimen[0], screnDimen[1]);

        int[] locationInWindow = new int[2];
        int[] locationInScreen = new int[2];
        customView.getLocationInWindow(locationInWindow);
        customView.getLocationOnScreen(locationInScreen);
        Timber.d("Custom view locationin window %d:%d",locationInWindow[0],locationInWindow[1]);
        Timber.d("Custom view location on screen %d:%d",locationInScreen[0],locationInScreen[1]);
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
