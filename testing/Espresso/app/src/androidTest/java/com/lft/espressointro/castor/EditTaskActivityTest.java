package com.lft.espressointro.castor;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.lft.espressointro.R;
import com.lft.espressointro.castor.realmespressocontrib.RealmRecyclerViewActions;
import com.lft.espressointro.tasko.TaskMainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;

/**
 * Created by laaptu on 3/4/16.
 */
@RunWith(AndroidJUnit4.class)
public class EditTaskActivityTest {
    @Rule
    public ActivityTestRule<TaskMainActivity> activityTestRule = new ActivityTestRule<>(TaskMainActivity.class);

    /**
     * This is same as Caster Tasko App, CreateNewTaskTest=>shouldBeAbleToCreateANewTaskAndThenEditTheTask
     */
    @Test
    public void shouldBeAbleToCreateANewTaskAndThenEditTheTask() {
        //withText(allOf(equals("hello"));


        onView(ViewMatchers.withId(R.id.fab)).perform(click());




        onView(withId(R.id.new_task_task_name)).perform(click());
        onView(withId(R.id.new_task_task_name)).perform(typeText("Task1"));
        onView(withId(R.id.new_task_task_desc)).perform(click());
        onView(withId(R.id.new_task_task_desc)).perform(typeText("Description1"), closeSoftKeyboard());

        if(true){
            onView(withText("Task1")).check(matches(isDisplayed()));
            onView(withText(CustomMatchers.isValidTest())).check(matches(isDisplayed()));
            return;
        }


        onView(withId(R.id.new_task_add)).perform(click());

        onView(withId(R.id.main_task_list))
                .perform(RealmRecyclerViewActions.actionOnItem(CustomMatchers.withTaskViewName("Task1"), click()));

        onView(withText("Edit")).check(matches(isDisplayed()));
        onView(withText("Save")).check(matches(isDisplayed()));
        onView(withId(R.id.new_task_task_name)).perform(click(), clearText(), replaceText("Task2"), closeSoftKeyboard());
        onView(withId(R.id.new_task_task_desc)).perform(click(), clearText(), replaceText("Description2"), closeSoftKeyboard());
        onView(withText("Save")).perform(click());

        onView(withText("Task2")).check(matches(isDisplayed()));
       // onView(withText(CustomMatchers.isValidTest("Task2"))).check(matches(isDisplayed()));
    }
}
