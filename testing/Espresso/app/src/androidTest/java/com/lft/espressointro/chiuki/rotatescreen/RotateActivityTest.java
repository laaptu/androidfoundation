package com.lft.espressointro.chiuki.rotatescreen;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.lft.espressointro.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;

/**
 * Created by laaptu on 3/8/16.
 */
@RunWith(AndroidJUnit4.class)
public class RotateActivityTest {
    @Rule
    public ActivityTestRule<RotateActivity> activityRule = new ActivityTestRule<>(RotateActivity.class);

    @Before
    public void checkInitialCount() {
        onView(withId(R.id.count))
                .check(matches(withText("0")));
    }

    @Test
    public void increment() {
        onView(withId(R.id.increment_button))
                .check(matches(withText(R.string.increment)))
                .perform(click());
        onView(withId(R.id.count))
                .check(matches(withText("1")));
    }

    @Test
    public void incrementTwiceAndRotateScreen() {
        onView(withId(R.id.increment_button))
                .check(matches(withText(R.string.increment)))
                .perform(click())
                .perform(click());
        onView(withId(R.id.count))
                .check(matches(withText("2")));

        rotateScreen();

        onView(withId(R.id.count))
                .check(matches(withText("2")));
    }

    @Test
    public void noIncrementRotateScreen() {
        rotateScreen();
        onView(withId(R.id.count))
                .check(matches(withText("0")));
    }

    private void rotateScreen() {
        Context context = InstrumentationRegistry.getTargetContext();
        int orientation = context.getResources().getConfiguration().orientation;

        Activity activity = activityRule.getActivity();
        activity.setRequestedOrientation(
                (orientation == Configuration.ORIENTATION_PORTRAIT) ?
                        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}
