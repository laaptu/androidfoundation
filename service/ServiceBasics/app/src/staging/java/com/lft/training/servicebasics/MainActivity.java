package com.lft.training.servicebasics;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.View;

import com.lft.training.servicebasics.base.BaseActivity;
import com.lft.training.servicebasics.data.Extras;

import java.util.List;

public class MainActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    public void openService(View view) {
        if (true) {
            //callOtherService();
            sendBroadCast1();
            return;


        }

        Intent intent = new Intent();
        intent.setAction(Extras.CALL_SERVICE);
        intent.setType("text/plain");
        startActivity(intent);

    }

    private void sendBroadCast1() {
        Intent intent = new Intent(Extras.EVENT_DOWNLOADCOMPLETE);
        intent.putExtra(Extras.URL, "This is from another call");
        sendBroadcast(intent);
    }

    private void callOtherService() {
        Intent intent = new Intent(this, BasicAnotherService.class);
        intent.putExtra(Extras.DATA, "Call another service");
        startService(intent);
    }

    private void callService() {
        //Not working from Android L and above
        Intent intent = new Intent();
        intent.setAction(Extras.CALL_SERVICE);
        intent = createExplicitFromImplicitIntent(this, intent);
        startService(intent);
    }

    /***
     * Android L (lollipop, API 21) introduced a new problem when trying to invoke implicit intent,
     * "java.lang.IllegalArgumentException: Service Intent must be explicit"
     * <p/>
     * If you are using an implicit intent, and know only 1 target would answer this intent,
     * This method will help you turn the implicit intent into the explicit form.
     * <p/>
     * Inspired from SO answer: http://stackoverflow.com/a/26318757/1446466
     *
     * @param context
     * @param implicitIntent - The original implicit intent
     * @return Explicit Intent created from the implicit original intent
     */
    public static Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);

        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            System.out.println("Intent ==null");
            return null;
        }

        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);

        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);

        // Set the component to be explicit
        explicitIntent.setComponent(component);

        return explicitIntent;
    }
}
