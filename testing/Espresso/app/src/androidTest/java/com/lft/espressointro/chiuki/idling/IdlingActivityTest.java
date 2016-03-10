package com.lft.espressointro.chiuki.idling;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import com.lft.espressointro.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
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
public class IdlingActivityTest {
    @Rule
    public ActivityTestRule<IdlingActivity> activityTestRule = new ActivityTestRule<>(IdlingActivity.class);

    private Context context;

    @Before
    public void someBeforeAction() {
        context = InstrumentationRegistry.getTargetContext();
    }


    @Test
    public void someIdlingTest() {

        onView(withId(R.id.btn_load)).perform(click());
        //onView(withId(R.id.info_txt)).perform(addTextAction());
        onView(withId(R.id.info_txt)).check(matches(doesContainText()));

    }

    private ViewAction addTextAction() {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isAssignableFrom(TextView.class), isDisplayed());
            }

            @Override
            public String getDescription() {
                return "Adding some text action to the textview";
            }

            @Override
            public void perform(UiController uiController, View view) {
                ((TextView) view).setText(context.getString(R.string.info_load_failure));

            }
        };
    }

    private Matcher<View> doesContainText() {
        return new BoundedMatcher<View, TextView>(TextView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("Matching whether text contains some value or not");

            }

            @Override
            protected boolean matchesSafely(TextView item) {
                return item.getText().toString().equals(context.getString(R.string.info_load_success))
                        || item.getText().toString().equals(context.getString(R.string.info_load_failure));
            }
        };
    }

    //some Idling class
    public class SomeIdlingResource implements IdlingResource {

        private final long startTime;
        private final long waitingTime;
        private ResourceCallback resourceCallback;

        public SomeIdlingResource(long waitingTime) {
            startTime = System.currentTimeMillis();
            this.waitingTime = waitingTime;
        }

        @Override
        public String getName() {
            return "This must be a unique name";
        }

        @Override
        public boolean isIdleNow() {
            //here you must add your logic
            //so that Espresso checks whether to proceed to next step or not
            long elapsedTime = System.currentTimeMillis() - startTime;
            boolean isIdle = elapsedTime >= waitingTime;
            if (isIdle)
                resourceCallback.onTransitionToIdle();
            return isIdle;
        }

        @Override
        public void registerIdleTransitionCallback(ResourceCallback callback) {
            resourceCallback = callback;
        }
    }
}
