package com.training.lft.myapplication.dagger;

/**
 * Created by laaptu on 12/10/15.
 */
final class Modules {
    static Object[] list() {
        return new Object[]{
                new AndroidModule(),
                new BootstrapModule()
        };
    }

    private Modules() {

    }
}
