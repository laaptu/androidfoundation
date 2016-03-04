package com.lft.espressointro;

import android.os.Handler;
import android.os.Looper;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.lft.espressointro.realmespressocontrib.RealmRecyclerViewActions;
import com.lft.espressointro.tasko.TaskMainActivity;
import com.lft.espressointro.tasko.db.DbHelper;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static com.lft.espressointro.CustomMatchers.withTaskViewName;
import static org.hamcrest.Matchers.not;

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

    @Ignore
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

    /**
     * This test is demonstrating of adding multiple items on the list view and on database
     */
    @Ignore
    @Test
    public void addMultipleItemsOnTheList() {
        String taskName;
        String taskDescription;
        for (int i = 0; i < 11; i++) {
            taskName = "Task " + i;
            taskDescription = "Description " + i;
            onView(withId(R.id.menu_main_new_task)).perform(click());
            onView(withId(R.id.new_task_task_name)).perform(typeText(taskName));
            onView(withId(R.id.new_task_task_desc)).perform(typeText(taskDescription), closeSoftKeyboard());
            onView(withId(R.id.new_task_add)).perform(click());
        }
    }

    /**
     * To perform actions on RecyclerView and other appcompat views,
     * datepickr,drawerlayout,recyclerview
     * we again need espresso-contrib
     * http://stackoverflow.com/questions/30578243/why-would-adding-espresso-contrib-cause-an-inflateexception
     * Further we are again using RealmRecyclerView , so we again
     * have to add custom view actions
     */
    @Ignore
    @Test
    public void addMultipleItemsOnTheListAndScrollToLastItem() {
        String taskName, taskDescription;
        for (int i = 0; i < 11; i++) {
            taskName = "Task " + i;
            taskDescription = "Description " + i;
            onView(withId(R.id.menu_main_new_task)).perform(click());
            onView(withId(R.id.new_task_task_name)).perform(typeText(taskName));
            onView(withId(R.id.new_task_task_desc)).perform(typeText(taskDescription), closeSoftKeyboard());
            onView(withId(R.id.new_task_add)).perform(click());
        }

        onView(withText("Task 0")).check(matches(isDisplayed()));
        //this comes with espresso-contrib, but we need to add custom ViewActions i.e. custom RecyclerViewActions
        //onView(withId(R.id.main_task_list)).perform(RecyclerViewActions.scrollToPosition(10));
        onView(withId(R.id.main_task_list)).perform(RealmRecyclerViewActions.scrollToPosition(10));
        onView(withText("Task 10")).check(matches(isDisplayed()));
    }

    /**
     * Same as {@code addMultipleItemsOnTheListAndScrollToLastItem},
     * but we are now using CustomMatchers for that
     */
    @Ignore
    @Test
    public void addMulitpleItemsOnListNScrollWithCustomMatchers() {
        String taskName, taskDescription;
        for (int i = 0; i < 11; i++) {
            taskName = "Task " + i;
            taskDescription = "Description " + i;
            onView(withId(R.id.menu_main_new_task)).perform(click());
            onView(withId(R.id.new_task_task_name)).perform(typeText(taskName));
            onView(withId(R.id.new_task_task_desc)).perform(typeText(taskDescription), closeSoftKeyboard());
            onView(withId(R.id.new_task_add)).perform(click());
        }

        onView(withText("Task 0")).check(matches(isDisplayed()));
        onView(withId(R.id.main_task_list)).perform(RealmRecyclerViewActions.scrollTo(
                CustomMatchers.withTaskViewName("Task 10")
        ));

    }

    /**
     * Working with some custom matchers and going from viewId
     */
    @Ignore
    @Test
    public void customMatcherTest() {
        onView(withId(R.id.menu_main_new_task)).perform(click());
        onView(CustomMatchers.withSomeId(R.id.new_task_add)).perform(click());
    }

    @Ignore
    @Test
    public void fabVisibilityTest() {
        onView(withId(R.id.fab)).check(matches(isDisplayed()));
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.fab)).check(matches(not(isDisplayed())));
        onView(withId(R.id.new_task_task_name)).perform(typeText("Fab visibility"));
        onView(withId(R.id.new_task_task_desc)).perform(typeText("Writing test to check whether the fab is visible"), closeSoftKeyboard());
        onView(withId(R.id.new_task_add)).perform(click());
        onView(withId(R.id.fab)).check(matches(isDisplayed()));
    }


    /**
     * Testing for editing
     * this doesn't properly work, check
     * {@link EditTaskActivityTest}
     * Specially on performing click on RealmRecyclerView
     */
    @Test
    public void editGivenItem() {
        onView(withId(R.id.main_task_list)).check(matches(CustomMatchers.doesRecyclerViewHasFirstItem(0)));
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.new_task_task_name)).perform(typeText("Fab visibility"));
        onView(withId(R.id.new_task_task_desc)).perform(typeText("Writing test to check whether the fab is visible"), closeSoftKeyboard());
        onView(withId(R.id.new_task_add)).perform(click());
        onView(withId(R.id.main_task_list)).perform(RealmRecyclerViewActions.actionOnItem(withTaskViewName("Fab visibility"), click()));
        onView(withId(R.id.new_task_task_name)).perform(typeText("Fab visibility 1"));
        onView(withId(R.id.new_task_task_desc)).perform(typeText("Writing test to check whether the fab is visible1"), closeSoftKeyboard());
        onView(withId(R.id.new_task_add)).perform(click());
    }

}

