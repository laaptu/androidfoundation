package com.lft.espressointro.chiuki.list;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lft.espressointro.R;
import com.lft.espressointro.castor.CustomMatchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import timber.log.Timber;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

/**
 * Created by laaptu on 3/4/16.
 */
@RunWith(AndroidJUnit4.class)
public class SimpleListActivityTest {
    @Rule
    public ActivityTestRule<SimpleListActivity> activityTestRule = new ActivityTestRule<>(SimpleListActivity.class);

    @Ignore
    @Test
    public void scrollNClickTest() {
        //textview is only displayed when list item is clicked
        onView(withId(R.id.text)).check(matches(not(isDisplayed())));

        onData(withValue(23)).inAdapterView(withId(R.id.list)).perform(click());
        onView(withId(R.id.text)).check(matches(allOf(isDisplayed(), withText("23"))));
    }

    @Test
    public void scrollTest() {
        //this is just a matcher, which specifies how items are to be matched
        //at this point, not data is passed onto it
        //onData(withValue(27));
        //by doing this we want to iterate all the data that is passed on to the list view
        //which is itself Item, so when all the Item[] of listview is found
        // it passes that to the Matcher i.e withValue()
        //here also nothing happens as we are not specifying the view actions
        //onData(withValue(27)).inAdapterView(withId(R.id.list));
        //it will work only when we provide a view action
        //onData(withValue(27)).inAdapterView(withId(R.id.list)).perform(click());
        for (int i = 0; i < 30; i++) {
            onView(withId(R.id.list)).perform(getScrollAction(27));
            onView(withId(R.id.list)).perform(getScrollAction(0));
            onView(withId(R.id.list)).perform(getScrollAction(10));
            onView(withId(R.id.list)).perform(getScrollAction(29));
            onView(withId(R.id.list)).perform(getScrollAction(0));
        }


    }


    private ViewAction getScrollAction(final int value) {
        return new ViewAction() {
            /**
             * This is extra matcher. Meaning
             * you will get the view that matches your criteria,
             * but you still want to recheck some extra params like
             * the view must be a ListView ( check scrollTo view actions
             * of Espresso) or the view must be visible ( as most of the
             * Espresso actions are performed to a visible view). In this
             * case we are just leaving to blank i.e. we are not checking any
             * criteria*/
            @Override
            public Matcher<View> getConstraints() {
                return CustomMatchers.getEmptyMatcher();
            }

            @Override
            public String getDescription() {
                return null;
            }


            /**
             * Here we get a view that matches our criteria
             * .So we know our view is a ListView, so just simply
             * perform actions to a listview. Meaning in this portion
             * we get our view and we all know what that view actions can be
             * so it is upto you to identify what actions are you going to perform
             * So if our matchers is good, we will only get a single view out here*/
            @Override
            public void perform(UiController uiController, View view) {
                Timber.d("View id =%d", view.getId());
                if (view instanceof ListView) {
                    ListView listView = (ListView) view;
                    ArrayAdapter<SimpleListActivity.Item> arrayAdapter = (ArrayAdapter<SimpleListActivity.Item>) listView.getAdapter();
                    SimpleListActivity.Item item;
                    int index = -1;
                    for (int i = 0; i < arrayAdapter.getCount(); i++) {
                        item = arrayAdapter.getItem(i);
                        if (item.toString().equals(String.valueOf(value))) {
                            index = i;
                            break;
                        }
                    }
                    System.out.println("Scroll to index =" + String.valueOf(index));
                    if (index >= 0)
                        listView.smoothScrollToPosition(index);
                }


            }
        };
    }

    public static Matcher<SimpleListActivity.Item> withValue(final int value) {
        return new TypeSafeMatcher<SimpleListActivity.Item>() {
            @Override
            protected boolean matchesSafely(SimpleListActivity.Item item) {
                System.out.println("Item value = " + item.toString());
                return item.toString().equals(String.valueOf(value));
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Finding list item with value =" + String.valueOf(value));

            }
        };
    }

    //there is bounded matcher as well as per chiuki SimpleListActivityTest
    public static Matcher<Object> withBoundedValue(final int value) {
        return new BoundedMatcher<Object, SimpleListActivity.Item>(SimpleListActivity.Item.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has value " + value);
            }

            @Override
            public boolean matchesSafely(SimpleListActivity.Item item) {
                return item.toString().equals(String.valueOf(value));
            }
        };
    }

}
