package com.lft.espressointro.intent;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.provider.MediaStore;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import com.lft.espressointro.R;
import com.lft.espressointro.mockito.GeneralUtils;
import com.lft.espressointro.mockito.basics.Person;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.core.AllOf;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 * Created by laaptu on 3/15/16.
 */
@RunWith(AndroidJUnit4.class)
public class LoadDataActivityTest {
    @Rule
    public IntentsTestRule<LoadDataActivity> activityTestRule = new IntentsTestRule<>(LoadDataActivity.class);

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

    /**
     * Here we are just mocking a
     */
    @Ignore
    @Test
    public void mockitoNEspressoTest() {
        System.out.println("ConnectivityManager null " + (connectivityManager == null));
        activityTestRule.getActivity().setGeneralUtils(generalUtils);
        when(generalUtils.getConnectivityManager(isA(Context.class))).thenReturn(connectivityManager);
        when(connectivityManager.getActiveNetworkInfo()).thenReturn(null);


        onView(withId(R.id.btn_findwifi)).perform(click());
        onView(withId(R.id.txt_info)).check(ViewAssertions.matches(textMatchers(context.getString(R.string.info_net_disconnected))));
    }

    @Ignore
    @Test
    public void startActivityMock() {
        LoadDataActivity loadDataActivity = Mockito.spy(activityTestRule.getActivity());
        //Here one thing to understand is that
        //loadDataActivity != activityTestRule.getActivity()
        //whenever we spy activityTestRule.getActivity() as new Spy object is created
        // which is not equal to the spied object
        activityTestRule.getActivity().tag = "Original";
        System.out.println(loadDataActivity.tag);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                System.out.println("Mocking with Answer");
//                LoadDataActivity someActivity = (LoadDataActivity) invocation.getMock();
//                Intent intent = new Intent();
//                intent.putExtra(LoadDataActivity.RETURN_STRING, context.getString(R.string.result_value));
//                someActivity.onActivityResult(LoadDataActivity.RETURN_VALUE, LoadDataActivity.RETURN_VALUE, intent);
                return true;
            }
        }).when(loadDataActivity).startSecondActivity();

        loadDataActivity.startSecondActivity();
        //onView(withId(R.id.btn_second)).perform(click());
        //onView(withId(R.id.txt_info)).check(ViewAssertions.matches(textMatchers(context.getString(R.string.result_value))));
    }

    @Ignore
    @Test
    public void someIntentTest() {
        Intent intent = new Intent();
        intent.putExtra(LoadDataActivity.RETURN_STRING, "Hello1234");
        /**
         * What interesting about intending is that , it is like Scripting of mockito
         * so in mockito when(some action called). do this
         * This is also the same,
         * here we intercept all the intents , so in commented section,
         * we find if anyone is calling Intent with following classname,
         * don't start the activity, instead call the activity result of the calling
         * intent*/

//        Intents.intending(IntentMatchers.hasComponent(ComponentNameMatchers.hasClassName("com.lft.espressointro.twoactivities.SecondActivity"))).respondWith(
//                new Instrumentation.ActivityResult(Activity.RESULT_OK, intent)
//        );
        /**
         * Similarly we can work with matching each and everything of intent and work with
         * various options provided by Instrumentation like call create, call destroy and so on*/
        Intents.intending(IntentMatchers.hasAction(LoadDataActivity.RETURN_STRING)).respondWith(new Instrumentation.ActivityResult(1, intent));
        onView(withId(R.id.btn_second)).perform(click());
        onView(withId(R.id.txt_info)).check(ViewAssertions.matches(textMatchers("Hello1234")));
    }

    @Test
    public void cameraClickTest() {
        Intent intent = new Intent();
        intent.putExtra(LoadDataActivity.FILE_PATH, "/sdcard/pic/hello.jpg");
        Intents.intending(IntentMatchers.hasAction(MediaStore.ACTION_IMAGE_CAPTURE)).respondWith(new Instrumentation.ActivityResult(1, intent));
        onView(withId(R.id.btn_camera)).perform(click());
//        Intents.intended(Matchers.allOf(IntentMatchers.hasAction(MediaStore.ACTION_IMAGE_CAPTURE),
//                IntentMatchers.hasExtraWithKey(LoadDataActivity.RETURN_STRING)));
        Intents.intending(AllOf.allOf(IntentMatchers.hasAction(Matchers.equalTo(MediaStore.ACTION_IMAGE_CAPTURE)),IntentMatchers.hasExtraWithKey(Matchers.equalTo(LoadDataActivity.RETURN_STRING))));
        ;



    }

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


//    @Before
//    public void stubAllExternalIntents() {
//        // By default Espresso Intents does not stub any Intents. Stubbing needs to be setup before
//        // every test run. In this case all external Intents will be blocked.
//        Intents.intending(CoreMatchers.not(IntentMatchers.isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
//    }
}
