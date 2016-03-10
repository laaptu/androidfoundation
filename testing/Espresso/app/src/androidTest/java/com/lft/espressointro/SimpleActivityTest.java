package com.lft.espressointro;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
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
 * Created by laaptu on 3/10/16.
 */
@RunWith(AndroidJUnit4.class)
public class SimpleActivityTest {

    @Rule
    public ActivityTestRule<SimpleActivity> activityTestRule = new ActivityTestRule<>(SimpleActivity.class);

    private Context context;

    @Before
    public void generateContext() {
        //context = activityTestRule.getActivity().getApplicationContext();
        context = InstrumentationRegistry.getTargetContext();
    }

    @Ignore
    @Test
    public void firstTest() {
        onView(allOf(withId(R.id.btn_add), ViewMatchers.withText(context.getString(R.string.add_text)))).check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.info_txt)).check(matches(hasTextWithinIt()));
        //onView(withId(R.id.info_edittxt)).check(matches(hasTextWithinIt()));
        onView(withId(R.id.info_edittxt)).perform(clearText(), typeText("This is great"), closeSoftKeyboard());
    }

    @Test
    public void customTest() {
        onView(getViewWithIdAndText(R.id.btn_add, context.getString(R.string.add_text))).perform(performClickAction());
        onView(withId(R.id.info_txt)).check(matches(hasTextWithinIt()));
        System.out.println("Hello");
    }

    private Matcher<View> getViewWithIdAndText(final int id, final String text) {
        return new BoundedMatcher<View, TextView>(TextView.class) {
            @Override
            public void describeTo(Description description) {

            }

            @Override
            protected boolean matchesSafely(TextView item) {
                return item.getId() == id && item.getText().equals(text);
            }
        };
    }



    private ViewAction performClickAction() {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(Button.class);
            }

            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public void perform(UiController uiController, View view) {
                if (view instanceof Button) {
                    view.callOnClick();
                }

            }
        };
    }


    private Matcher<View> hasTextWithinIt() {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                if (item instanceof TextView) {
                    return ((TextView) item).getText().toString().length() > 0;
                }

                return false;
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }
}
