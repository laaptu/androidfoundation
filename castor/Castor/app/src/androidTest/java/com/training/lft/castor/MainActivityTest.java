package com.training.lft.castor;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.training.lft.castor.espresso.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


/**
 * Created by laaptu on 12/3/15.
 */
//Adding TestRunner
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    //invoking which class to use for testing
    @Rule
    public final ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class);

    public String getString(int id) {
        return main.getActivity().getString(id);
    }

    @Test
    public void firstTestOnEspresso() {
        onView(withText(getString(R.string.login_label))).check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.edit_uname)).perform(ViewActions.typeText("Santosh is genius"));
        onView(withId(R.id.edit_pwd)).perform(ViewActions.typeText("Awesome"));
        onView(withId(R.id.edit_email)).perform(ViewActions.typeText("sanimap@gmail.com"));
        onView(withId(R.id.scrollView)).perform(ViewActions.scrollTo());
        onView(withId(R.id.edit_phone)).perform(ViewActions.typeText("9801079923"));
        onView(withId(R.id.edit_firstname)).perform(ViewActions.typeText("Santosh"));
        onView(withId(R.id.edit_middlename)).perform(ViewActions.typeText("Awesome"));
        onView(withId(R.id.scrollView)).perform(ViewActions.scrollTo());
        onView(withId(R.id.edit_lastname)).perform(ViewActions.typeText("Is Chilling with test cases."));
        onView(withId(R.id.edit_address)).perform(ViewActions.typeText("Is in Nirvana and is enjoying to the fullest"));
        onView(withId(R.id.btn_login)).perform(click());
    }
}
