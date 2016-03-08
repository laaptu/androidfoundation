package com.lft.espressointro.chiuki.toolbar;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.Toolbar;

import com.lft.espressointro.R;
import com.lft.espressointro.castor.CustomMatchers;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.core.Is.is;


/**
 * Created by laaptu on 3/8/16.
 */
@RunWith(AndroidJUnit4.class)
public class ToolbarActivityTest {
    @Rule
    public ActivityTestRule<ToolbarActivity> activityTestRule = new ActivityTestRule<>(ToolbarActivity.class);

    @Test
    public void toolbarTitleTest() {
        CharSequence title = InstrumentationRegistry.getTargetContext().getString(R.string.my_title);
        onView(isAssignableFrom(Toolbar.class)).check(
                matches(CustomMatchers.withToolbarTitleNoBound(is(title)))
        );
    }
}
