package com.lft.espressointro;

import android.os.Handler;
import android.os.Looper;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.lft.espressointro.tasko.TaskMainActivity;
import com.lft.espressointro.tasko.db.DbHelper;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

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
     */
    @Test
    public void addNewItemTest() {
        final String taskName = "This is new task";
        final String taskDescription = "I am manually adding a new description";
        Espresso.onView(ViewMatchers.withId(R.id.menu_main_new_task)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.new_task_task_name)).perform(ViewActions.typeText(taskName), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.new_task_task_desc)).perform(ViewActions.typeText(taskDescription), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.new_task_add)).perform(ViewActions.click());
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
}


