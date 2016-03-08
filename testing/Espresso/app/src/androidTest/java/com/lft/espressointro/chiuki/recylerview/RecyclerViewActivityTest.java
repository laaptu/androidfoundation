package com.lft.espressointro.chiuki.recylerview;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.lft.espressointro.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by laaptu on 3/8/16.
 * http://blog.xebia.com/android-intent-extras-espresso-rules/
 */
@RunWith(AndroidJUnit4.class)
public class RecyclerViewActivityTest {
    @Rule
    public ActivityTestRule<RecyclerViewActivity> activityTestRule = new ActivityTestRule<RecyclerViewActivity>(RecyclerViewActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            return RecyclerViewActivity.launchActivity(targetContext, 10);
        }
    };

    @Test
    public void firstTest() {
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(10, click()));

        onView(withId(R.id.text)).check(matches(allOf(isDisplayed(), withText("10"))));
    }
}
