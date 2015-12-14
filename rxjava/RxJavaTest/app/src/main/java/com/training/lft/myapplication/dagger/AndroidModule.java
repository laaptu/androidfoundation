package com.training.lft.myapplication.dagger;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.training.lft.myapplication.MainApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by laaptu on 12/10/15.
 */
@Module(library = true)
public class AndroidModule {

    @Provides
    @Singleton
    Context provideAppContext() {
        return MainApplication.getInstance().getApplicationContext();
    }

    @Provides
    TelephonyManager provideTelephonyManager(Context context) {
        return getSystemService(context, Context.TELEPHONY_SERVICE);
    }

    public <T> T getSystemService(Context context, String serviceConstant) {
        return (T) context.getSystemService(serviceConstant);
    }
}
