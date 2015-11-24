package com.training.lft.alarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    /**
     * Set of tasks to be done
     * 1. Create two alarms with RTC,ELAPSED and simply add start after time without System.currentimeinmillis
     * for RTC and Systemclock.elapsedtime for ELAPSED. And then add both to see the difference on how to
     * properly add time
     * 2. Create two alarms with same pending intent . The two alarms have different time
     * 3. Demonstrate RTC,ELAPSED with or without WAKEUP, right now there doesn't seem any difference visually
     * as part sleep mode is hard to emulate. Explain sleep mode theoretically right now
     * 4. Difference between RTC and ELAPSED. Add 2 -3 minutes delay with same RTC and ELAPSED. And then once added
     * modify the time i.e. decrease the time , the RTC will some what be lost but ELAPSED will come on time
     */

    //1 minutes
    private static final long INTERVAL = 1 * 10 * 1000L;
    private static final long START_AFTER = 1 * 30 * 1000L;
    private static final long JITTER = 5000L;

    private int ELAPSED_ID = 0x1, RTC_ID = 0x2;

    @Bind(R.id.info_txt)
    TextView infoTxt;

    private PendingIntent pendingIntent, pendingIntentElapsed;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (getIntent() != null && getIntent().hasExtra(Extras.ALARM_MESSAGE)) {
            infoTxt.append("\n\n" + getIntent().getStringExtra(Extras.ALARM_MESSAGE));
        }
    }


    @OnClick({R.id.btn_set_repeat, R.id.btn_set_repeat_exact, R.id.btn_single_set, R.id.btn_cancel_alrm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_single_set:
                setSingleAlarms();
                break;
            case R.id.btn_set_repeat:
                setRepeatingAlarm();
                break;
            case R.id.btn_set_repeat_exact:
                break;
            case R.id.btn_cancel_alrm:
                cancelAlarm();
                break;
        }
    }

    private void setSingleAlarms() {
        setSimpleAlarmWithWakeUp();

    }

    /**
     * Simple Set Alarms to show difference between WAKEUP.
     * Right now it is hard to emulate the sleep state of CPU
     * And wake up not necessarily mean is screen on as always.
     * It is not guaranteed that screen also will be on
     * Right now tested on Api 19 and above.
     * Need to test it on Api 19 below
     */
    private void setSimpleAlarmWithWakeUp() {
        AlarmManager alarmManager = AlarmApplication.getAlarmManager();
        String message = Extras.RTC_WAKEUP + " :: started at " + Extras.getCurrentTime();
        //setPendingIntentWithMessage(message);
        setPendingIntentWithMessage(message, RTC_ID);
        /**this is right way to add after time*/
        alarmManager.set(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + START_AFTER, pendingIntent);
        /**this is wrong way to add after time*/
        // alarmManager.set(AlarmManager.RTC_WAKEUP,START_AFTER,pendingIntent);
//        alarmManager.set(AlarmManager.RTC_WAKEUP,
//                SystemClock.elapsedRealtime() + START_AFTER, pendingIntent);


        message = Extras.ELAPSED_WAKEUP + " :: started at " + Extras.getCurrentTime();
        //setPendingIntentWithMessage(message);
        setPendingIntentElapsedWithMessage(message, ELAPSED_ID);
        /**this is right way to add after time*/
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + START_AFTER, pendingIntentElapsed);
        /**this is wrong way to add after time*/
        //alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, START_AFTER, pendingIntent);
        //alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, System.currentTimeMillis() + START_AFTER, pendingIntent);


        //alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + START_AFTER+JITTER, pendingIntent);
//        alarmManager.set(AlarmManager.RTC_WAKEUP,
//                System.currentTimeMillis() + START_AFTER+JITTER, pendingIntent);

    }


    private void setSimpleAlarmWithoutWakeUp() {
        AlarmManager alarmManager = AlarmApplication.getAlarmManager();
        String message = Extras.RTC + " :: started at " + Extras.getCurrentTime();
        setPendingIntentWithMessage(message);
        alarmManager.set(AlarmManager.RTC,
                System.currentTimeMillis() + START_AFTER, pendingIntent);
//        message = Extras.ELAPSED + " :: started at " + Extras.getCurrentTime();
//        setPendingIntentWithMessage(message);
//        alarmManager.set(AlarmManager.ELAPSED_REALTIME, System.currentTimeMillis() + START_AFTER, pendingIntent);
    }

    /**
     * While using this you are creating the same pendingIntent as ID =0,
     * so the last alarm manager using this intent will override all other alarm set using this intent.
     * Meaning only the last alarm manager will only be listened or evoked
     */
    private void setPendingIntentWithMessage(String message) {
        intent = new Intent(this, AlarmNotificationReceiver.class);
        intent.putExtra(Extras.ALARM_MESSAGE, message);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    private void setPendingIntentElapsedWithMessage(String message,int id) {
        intent = new Intent(this, AlarmNotificationReceiver.class);
        intent.putExtra(Extras.ALARM_MESSAGE, message);
        pendingIntentElapsed = PendingIntent.getBroadcast(this, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    /**
     * Pending Intent are cached,
     * so if you are passing the same pending intent while setting two or more
     * alarms, and you want both to be listened, better make them different,
     * otherwise the last one getting the intent i.e. the last alarm setting will override
     * the previous one
     * e.g. use {@code setSimpleAlarmWithWakeUp} with {@code setPendingIntentWithMessage} without id,
     * only the last alarm i.e. alarm with elapsed time will only come to effect
     * Given the fact be both set at same time or different time
     */
    private void setPendingIntentWithMessage(String message, int id) {
        intent = new Intent(this, AlarmNotificationReceiver.class);
        intent.putExtra(Extras.ALARM_MESSAGE, message);
        pendingIntent = PendingIntent.getBroadcast(this, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }


    private void setRepeatingAlarm() {
        AlarmManager alarmManager = AlarmApplication.getAlarmManager();
        String message = Extras.RTC + " :: startedAt " + Extras.getCurrentTime();
        setPendingIntentWithMessage(message, RTC_ID);
        alarmManager.setRepeating(AlarmManager.RTC,
                System.currentTimeMillis() + START_AFTER, INTERVAL, pendingIntent);
        message = Extras.ELAPSED + " :: startedAt " + Extras.getCurrentTime();
        setPendingIntentElapsedWithMessage(message, ELAPSED_ID);
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + START_AFTER, INTERVAL, pendingIntentElapsed);
    }


    private void cancelAlarm() {
        AlarmManager alarmManager = AlarmApplication.getAlarmManager();
        setPendingIntentWithMessage("",RTC_ID);
        alarmManager.cancel(pendingIntent);
        setPendingIntentWithMessage("", ELAPSED_ID);
        alarmManager.cancel(pendingIntent);
    }

    private void forceSleep() {
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        //PowerManager.
    }


}
