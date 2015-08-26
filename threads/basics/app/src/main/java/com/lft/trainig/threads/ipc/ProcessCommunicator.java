package com.lft.trainig.threads.ipc;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.TextView;

import com.lft.trainig.threads.BaseActivity;
import com.lft.trainig.threads.R;

import butterknife.Bind;

/**
 * Simple class to carry out IPC( Inter process communication)
 * IPC can be done by
 * <ol>
 * <li>Shared User ID</li>
 * <li>Intent calls</li>
 * <li>Content Provider</li>
 * <li>Binders</li>
 * </ol>
 * We are right now focusing on Shared User Id only</br>
 * Since both apps have same user id</br>
 * they can easily access each other file systems
 */
public class ProcessCommunicator extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_processcommunicator;
    }

    @Bind(R.id.info_txt)
    TextView infoText;

    String packageName = "com.lft.threads";

    public void loadData(View view) {
        loadSharedData();
    }

    /**
     * Loading data from shared preference of app com.lft.threads
     * as both have same shared user id = com.lft
     */
    private void loadSharedData() {
        PackageManager packageManager = getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            //applicationInfo.
            if (applicationInfo != null) {
                Context context = createPackageContext(packageName, Context.MODE_PRIVATE);
                SharedPreferences prefs = context.getSharedPreferences("Thread1", Context.MODE_PRIVATE);
                String myValue = prefs.getString("MYVALUE", "No value is not stored");
                infoText.setText(myValue);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            infoText.setText("No package is installed");
        }
    }
}
