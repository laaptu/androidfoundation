package com.training.lft.glidetest;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by laaptu on 11/25/15.
 */
//TestRunner I want to use
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public final ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void firstTest() {
        Espresso.onView(ViewMatchers.withId(R.id.btn_simple_glide)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
