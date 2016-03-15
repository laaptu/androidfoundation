package com.lft.espressointro.mockito;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by laaptu on 3/15/16.
 */
public class GeneralUtils {

    public ConnectivityManager getConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }
}
