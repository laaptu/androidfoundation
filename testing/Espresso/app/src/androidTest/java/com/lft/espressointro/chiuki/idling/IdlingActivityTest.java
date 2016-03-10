package com.lft.espressointro.chiuki.idling;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by laaptu on 3/10/16.
 */
@RunWith(AndroidJUnit4.class)
public class IdlingActivityTest {
    @Rule
    public ActivityTestRule<IdlingActivity> activityTestRule = new ActivityTestRule<>(IdlingActivity.class);


    @Test
    public void someIdlingTest() {

    }
}
