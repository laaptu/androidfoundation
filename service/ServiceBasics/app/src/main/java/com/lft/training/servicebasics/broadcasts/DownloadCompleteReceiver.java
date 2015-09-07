package com.lft.training.servicebasics.broadcasts;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.lft.training.servicebasics.R;
import com.lft.training.servicebasics.data.Extras;

/**
 * Created by laaptu on 9/4/15.
 */
public class DownloadCompleteReceiver extends BroadcastReceiver {

    private static final String TAG = "DownloadCompleteReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        /**
         * Place to start service,activity or notification or any other action
         */

        System.out.println(TAG + " @OnReceive :" + intent.getAction());
        if (intent.hasExtra(Extras.URL)) {
            String url = intent.getStringExtra(Extras.URL);
            Intent intent1 = new Intent(context, BroadCastActivity.class);
            intent1.setAction(Intent.ACTION_MAIN);
            intent1.setAction(Intent.CATEGORY_LAUNCHER);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

            Notification notification = new NotificationCompat.Builder(context)
                    .setContentTitle("Downloaded json")
                    .setContentText(url)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.ic_stat_av_play_circle_outline)
                    .setWhen(System.currentTimeMillis())
                    .build();
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify((int) (Math.random() * 100), notification);
        }
    }
}
