package com.lft.espressointro.chiuki.recylerview;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lft.espressointro.R;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Ignore;
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
 * You can initialize or start an activity in following ways
 * 1. While initializing the test rule, declare the intent as in Method 1
 * 2. Or simply start the activity as done in Method 2 i.e. inside the test itself
 * 3. Modification of 2, where you can start the activity with intent on @Before
 */
@RunWith(AndroidJUnit4.class)
public class RecyclerViewActivityTest {

    /**
     * Method 1
     */
//    @Rule
//    public ActivityTestRule<RecyclerViewActivity> activityTestRule = new ActivityTestRule<RecyclerViewActivity>(RecyclerViewActivity.class) {
//        @Override
//        protected Intent getActivityIntent() {
//            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//            return RecyclerViewActivity.launchActivity(targetContext, 10);
//        }
//    };

//    @Test
//    public void firstTest() {
//        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(20, click()));
//        onView(withId(R.id.text)).check(matches(allOf(isDisplayed(), withText("20"))));
//    }

    @Rule
    public ActivityTestRule<RecyclerViewActivity> activityTestRule = new ActivityTestRule<>(RecyclerViewActivity.class, true, false);

/**
 * Method 2*/
//    @Test
//    public void someRecyclerTest(){
//        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//        activityTestRule.launchActivity(RecyclerViewActivity.launchActivity(targetContext, 10));
//        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(20, click()));
//        onView(withId(R.id.text)).check(matches(allOf(isDisplayed(), withText("20"))));
//    }

    /**
     * Method 3
     */
    @Before
    public void initializeActivity() {
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        activityTestRule.launchActivity(RecyclerViewActivity.launchActivity(targetContext, 10));
    }

    @Ignore
    @Test
    public void someRecyclerTest1() {
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(20, click()));
        onView(withId(R.id.text)).check(matches(allOf(isDisplayed(), withText("20"))));
    }

    @Test
    public void recyclerViewScrollTest() {
        for (int i = 0; i < 30; i++) {
            onView(withId(R.id.recycler_view)).perform(getRecylerScrollAction(10));
            onView(withId(R.id.recycler_view)).perform(getRecylerScrollAction(20));
            onView(withId(R.id.recycler_view)).perform(getRecylerScrollAction(29));
            onView(withId(R.id.recycler_view)).perform(getRecylerScrollAction(0));
        }
    }


    public ViewAction getRecylerScrollAction(final int position) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                //just checking whether thew view is recyclerview and is displayed
                return allOf(isAssignableFrom(RecyclerView.class),isDisplayed());
            }

            @Override
            public String getDescription() {
                return "recyclerScrollto:";
            }

            @Override
            public void perform(UiController uiController, View view) {
                RecyclerView recyclerView = (RecyclerView) view;
                if (recyclerView != null) {
                    recyclerView.scrollToPosition(position);
                }

            }
        };
    }


}
