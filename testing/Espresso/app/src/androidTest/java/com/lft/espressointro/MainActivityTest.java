package com.lft.espressointro;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by laaptu on 3/1/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
/**If you want to order  the execution, just put on
 * the following, and it will execute each test in order*/
//http://stackoverflow.com/questions/3693626/how-to-run-test-methods-in-specific-order-in-junit4
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    /**
     * Remember when each and every test is performed, the previous
     * case won't be stored.
     * For example , if firstTest() runs, it will change the textView value
     * to R.string.replace_value
     * Now when twoOrMoreViewTest() runs, entire new activity is created, i.e.
     * the changes of firstTest() won't be there. Meaning all tests are
     * independent of each other*/
    @Test
    public void firstTest() {
        onView(withId(R.id.btn)).perform(click());
        onView(withId(R.id.txt_view)).perform(setTextInTextView(ResourceUtils.getString(R.string.replace_value)));
        onView(withId(R.id.txt_view)).check(ViewAssertions.matches(withText(ResourceUtils.getString(R.string.replace_value))));
    }


    @Test
    public void twoOrMoreViewTest() {
        onView(withText(ResourceUtils.getString(R.string.replace_value))).check(ViewAssertions.matches(isDisplayed()));
    }

    //http://stackoverflow.com/questions/32846738/android-testing-espresso-change-text-in-a-textview
    public static ViewAction setTextInTextView(final String value) {
        return new ViewAction() {

            /**
             * allOf = maybe meaning that all of the following must match
             * to extract the view
             * right now, the view must be visible
             * and second the view must be of TextView*/
            @Override
            public Matcher<View> getConstraints() {
                return allOf(ViewMatchers.isDisplayed(), ViewMatchers.isAssignableFrom(TextView.class));
            }

            /**
             * As per Squi blog, it is just used for debugging purpose*/
            @Override
            public String getDescription() {
                return "Normal Text View";
            }

            @Override
            public void perform(UiController uiController, View view) {
                ((TextView) view).setText(value);
            }
        };
    }
}
