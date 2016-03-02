package com.lft.espressointro;

/**
 * Created by laaptu on 3/2/16.
 */
public class ResourceUtils {

    public static String getString(int stringId) {
        return MainApplication.getContext().getString(stringId);
    }
}
