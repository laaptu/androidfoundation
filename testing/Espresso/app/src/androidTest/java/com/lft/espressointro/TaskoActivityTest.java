package com.lft.espressointro;

import android.os.Handler;
import android.os.Looper;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.lft.espressointro.tasko.TaskMainActivity;
import com.lft.espressointro.tasko.db.DbHelper;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

/**
 * Created by laaptu on 3/3/16.
 */
@RunWith(AndroidJUnit4.class)
public class TaskoActivityTest {

    //@Rule should also be public
    @Rule
    public ActivityTestRule<TaskMainActivity> activityTestRule = new ActivityTestRule<>(TaskMainActivity.class);

    /**
     * Each test must be public
     *
     * @Ignore will not run this test
     */
    @Ignore
    @Test
    public void addNewItemTest() {
        final String taskName = "This is new task";
        final String taskDescription = "I am manually adding a new description";
        onView(withId(R.id.menu_main_new_task)).perform(click());
        onView(withId(R.id.new_task_task_name)).perform(typeText(taskName), closeSoftKeyboard());
        onView(withId(R.id.new_task_task_desc)).perform(typeText(taskDescription), closeSoftKeyboard());
        onView(withId(R.id.new_task_add)).perform(click());
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Assert.assertEquals(true, DbHelper.getInstance().checkIfTaskIsAdded(taskName, taskDescription));
            }
        }, 1000);

    }

    @Test
    public void addItemAndCheckVisibility() {
        String taskName = "Again add new task";
        String taskDescription = "Again you are adding a new task descripiton";
        onView(withId(R.id.menu_main_new_task)).perform(click());
        onView(withId(R.id.new_task_task_name)).perform(typeText(taskName));
        /**
         * If we won't close the keyboard, after this test,
         * it won't satisfy the btn click on the next test
         * as Espresso requires the view to be 90% visible if any
         * view action is to be performed*/
        onView(withId(R.id.new_task_task_desc)).perform(typeText(taskDescription), closeSoftKeyboard());
        onView(withId(R.id.new_task_add)).perform(click());
        onView(withText(ResourceUtils.getString(R.string.info_task_added))).check(matches(isDisplayed()));
    }
}


