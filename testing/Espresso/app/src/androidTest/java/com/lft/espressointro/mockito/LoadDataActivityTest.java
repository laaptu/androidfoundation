package com.lft.espressointro.mockito;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import com.lft.espressointro.R;
import com.lft.espressointro.mockito.basics.Person;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Mockito.when;

/**
 * Created by laaptu on 3/15/16.
 */
@RunWith(AndroidJUnit4.class)
public class LoadDataActivityTest {
    @Rule
    public ActivityTestRule<LoadDataActivity> activityTestRule = new ActivityTestRule<>(LoadDataActivity.class);

    //    @Rule
//    public MockitoRule mockitoRule = MockitoJUnit.rule();
//    @Mock
    ConnectivityManager connectivityManager;
    GeneralUtils generalUtils;

    Context context;

    //    @Mock
    public Person person;


    @Before
    public void init() {
//        MockitoAnnotations.initMocks(connectivityManager);
//        MockitoAnnotations.initMocks(this);
        person = Mockito.mock(Person.class);
        generalUtils = Mockito.mock(GeneralUtils.class);
        connectivityManager = Mockito.mock(ConnectivityManager.class);
        context = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void mockitoNEspressoTest() {
        System.out.println("ConnectivityManager null " + (connectivityManager == null));
        activityTestRule.getActivity().setGeneralUtils(generalUtils);
        when(generalUtils.getConnectivityManager(context)).thenReturn(connectivityManager);

        ConnectivityManager someConnectivityManager = generalUtils.getConnectivityManager(context);

        when(connectivityManager.getActiveNetworkInfo()).thenReturn(null);

        System.out.println("Some Network Info null "+(someConnectivityManager.getActiveNetworkInfo()==null));

        onView(withId(R.id.btn_findwifi)).perform(click());
        //onView(withId(R.id.txt_info)).check(ViewAssertions.matches(textMatchers(context.getString(R.string.info_net_disconnected))));
    }

//    @Test
//    public void someEmptyTest() {
//        when(person.getName()).thenReturn("Hello");
//        onView(withId(R.id.btn_findwifi)).perform(click());
//
//    }

    private Matcher<View> textMatchers(final String text) {
        return new BoundedMatcher<View, TextView>(TextView.class) {
            @Override
            public void describeTo(Description description) {

            }

            @Override
            protected boolean matchesSafely(TextView item) {
                return item.getText().equals(text);
            }
        };
    }
}
