package com.lft.training.servicebasics;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.lft.training.servicebasics.base.BaseActivity;
import com.lft.training.servicebasics.data.Extras;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by laaptu on 8/31/15.
 */
public class BasicServiceActivity extends BaseActivity {


    public ArrayList<String> someString = new ArrayList<String>() {{
        add("FirstCall");
        add("SecondCall");
        add("ThirdCall");
        add("FourthCall");
        add("FifthCall");
        add("SixthCall");
        add("SeventhCall");
    }};

    @Bind(R.id.info_txt)
    TextView infoTxt;


    @Override
    public int getLayoutId() {
        return R.layout.activity_basicservice;
    }

    int count = 0;

    public void startBasicService(View view) {

        if (true) {
            startForegroundService();
            return;
        }
        //if (someString.size() > 0) {
        Intent intent = new Intent(this, BasicService.class);
        intent.putExtra(Extras.DATA, someString.get(count));
        count++;
        //someString.remove(0);
        startService(intent);
        //}
    }

    private void startForegroundService() {
        appendText();
        if (count == someString.size())
            count = 0;
        Intent intent = new Intent(this, BasicForegroundService.class);
        intent.putExtra(Extras.DATA, someString.get(count));
        count++;
        startService(intent);

    }

    public void stopBasicService(View view) {
        Intent intent = new Intent(this, BasicForegroundService.class);
//        intent.putExtra(Extras.DATA, "Stop");
//        intent.putExtra(Extras.STOP, "Stop");
//        startService(intent);
        stopService(intent);
    }

    public void startOtherService(View view) {
        appendText();
        Intent intent = new Intent(this, BasicService.class);
        intent.putExtra(Extras.DATA, someString.get(0));
        startService(intent);
    }

    public void stopOtherService(View view) {

    }

    private void appendText() {
        String someText = "sdflskfjsafksafjsalfjasdflsafjasdlfjasfsadfsdalfas\nflsjfslfjsklfsajflsafjsalfasfklasdfjsalfjsflsj\nsfljalsjflsfjsalfjslfjsflsajflasfjsalfsjflsjfslfjsaflsdkfsljfsafjslfsjflsafjsalfjaslfasjflasfjsalfjslfsajflsdafjsdflsdjflsdfjsdlfjsdflsjfslfjslfsjf";
        infoTxt.append(someText);
    }
}
