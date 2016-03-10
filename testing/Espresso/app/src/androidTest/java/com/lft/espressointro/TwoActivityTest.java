package com.lft.espressointro;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import com.lft.espressointro.twoactivities.ActivityFirst;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by laaptu on 3/10/16.
 */

@RunWith(AndroidJUnit4.class)
public class TwoActivityTest {
    @Rule
    public ActivityTestRule<ActivityFirst> activityFirstActivityTestRule = new ActivityTestRule<>(ActivityFirst.class);


    @Test
    public void navigateTest() {
        onView(withId(R.id.btn_first)).perform(click());
        onView(withId(R.id.info_txt)).check(matches(ViewMatchers.isDisplayed()));
        onView(withId(R.id.btn_second)).perform(click());
        onView(withId(R.id.info_txt)).check(matches(doesContainSomeText()));


    }

    private Matcher<View> doesContainSomeText() {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                if (item instanceof TextView) {
                    return ((TextView) item).getText().toString().contains("Thank");
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }
}
