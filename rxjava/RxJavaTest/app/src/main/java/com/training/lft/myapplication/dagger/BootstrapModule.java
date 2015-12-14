package com.training.lft.myapplication.dagger;

import com.training.lft.myapplication.MainActivity;

import dagger.Module;

/**
 * Created by laaptu on 12/11/15.
 */
@Module(complete = false,
        injects = {
                MainActivity.class
        }
)
public class BootstrapModule {
}
