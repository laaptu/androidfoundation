package com.lft.espressointro.castor;

import android.content.res.Resources;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.lft.espressointro.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import timber.log.Timber;

/**
 * Created by laaptu on 3/3/16.
 */
public class CustomMatchers {

    /**
     * Taken directly from ViewMathers.withSomeId
     * This is how customView Matcher work in conjunction with Espresso.onView()
     * Espresso.onView() may be able to give all the view one by one i.e
     * iterate all the views of the layout or activity.
     * Then it gives or passes the view to matchesSafely one by one,
     * so in matchesSafely() we just check for the condition and
     * return true or false.
     * When we print the value we will get all the views displayed here
     */
    public static Matcher<View> withSomeId(final int id) {
        return new TypeSafeMatcher<View>() {
            Resources resources = null;

            @Override
            public void describeTo(Description description) {
                String idDescription = Integer.toString(id);
                if (resources != null) {
                    try {
                        idDescription = resources.getResourceName(id);
                    } catch (Resources.NotFoundException e) {
                        // No big deal, will just use the int value.
                        idDescription = String.format("%s (resource name not found)", id);
                    }
                }
                description.appendText("with id: " + idDescription);
            }

            @Override
            public boolean matchesSafely(View view) {
                resources = view.getResources();
                printId(view);
                return id == view.getId();
            }

            private void printId(View view) {
                //checking this right now for R.layout.fragment_new_task
                switch (view.getId()) {
                    case R.id.new_task_task_name_input_layout:
                        System.out.println("R.id.new_task_task_name_input_layout");
                        break;
                    case R.id.new_task_task_name:
                        System.out.println("R.id.new_task_task_name");
                        break;
                    case R.id.new_task_task_desc_input_layout:
                        System.out.println("R.id.new_task_task_desc_input_layout");
                        break;
                    case R.id.new_task_task_desc:
                        System.out.println("R.id.new_task_task_desc");
                        break;
                    case R.id.new_task_add:
                        System.out.println("R.id.new_task_add");
                        break;
                    default:
                        System.out.println("No View for id =" + view.getId());
                }
            }
        };
    }

    //TypeSafeMatcher comes from hamcrest

    /**
     * Here we are going to apply custom matcher for R.layout.task_item
     * which contains FrameLayout --> TextView (id =task_item_task_name)
     */
    public static Matcher<View> withTaskViewName(final String expected) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                if (item != null && item.findViewById(R.id.task_item_task_name) != null) {
                    TextView taskName = (TextView) item.findViewById(R.id.task_item_task_name);
                    return taskName.getText().equals(expected);
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
                //for debugging and reporting purpose
                description.appendText("Looking for " + expected + " value @ R.layout.task_item.xml");

            }
        };
    }

    /**
     * This matcher is not accurate at all
     * Just wanted to know if any item has been added to RealmRecyclerView or not
     * Checking with findFirstVisibleItemPosition !=0, rather it should be
     * recycler view , getAdapter --> count ==0, seems like , there needs to
     * be some thing done on RealmRecyclerView to getAdapter(), will look on it later
     */
    public static Matcher<View> doesRecyclerViewHasFirstItem(final int position) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                if (item != null && item instanceof RealmRecyclerView) {
                    RealmRecyclerView recyclerView = (RealmRecyclerView) item;
                    return recyclerView.findFirstVisibleItemPosition() != 0;
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }


    public static Matcher<View> getEmptyMatcher() {
        return new Matcher<View>() {
            @Override
            public boolean matches(Object item) {
                return true;
            }

            @Override
            public void describeMismatch(Object item, Description mismatchDescription) {

            }

            @Override
            public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {

            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }

    /**
     * Trying to find out difference or to say importance between
     * BoundedMatcher and Simple TypeSafe Matcher
     * The simple diff is that in BoundMatcher we can easily
     * get the Toolbar on matchesSafely i.e. we don't have to typecast
     * or do anything.
     */
    //Chiuki BoundedMatcherImplementation on Toolbar title test
    public static Matcher<Object> withToolbarTitle(final Matcher<CharSequence> textMatcher) {
        return new BoundedMatcher<Object, Toolbar>(Toolbar.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("with toolbar title:");
                textMatcher.describeTo(description);

            }

            @Override
            protected boolean matchesSafely(Toolbar item) {
                return textMatcher.matches(item.getTitle());
            }
        };
    }

    public static Matcher<View> withToolbarTitleNoBound(final Matcher<CharSequence> textMatcher) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                return textMatcher.matches(((Toolbar) item).getTitle());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with toolbar title:");
                textMatcher.describeTo(description);

            }
        };
    }


    public static Matcher<String> isValidTest() {
        return new TypeSafeMatcher<String>() {
            @Override
            protected boolean matchesSafely(String item) {
                Timber.d("Matches Safely item =%s",item);
                return item != null && item.length()>3 && item.contains("Task");
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("someMinorDescription");
            }
        };
    }

//    public static Matcher<View> withText(String text) {
//        return withText(is(text));
//    }
//
//    /**
//     * Returns a matcher that matches {@link TextView}s based on text property value. Note: View's
//     * text property is never null. If you setText(null) it will still be "". Do not use null matcher.
//     *
//     * @param stringMatcher
//     *     <a href="http://hamcrest.org/JavaHamcrest/javadoc/1.3/org/hamcrest/Matcher.html">
//     *     <code>Matcher</code></a> of {@link String} with text to match
//     */
//    public static Matcher<View> withText(final Matcher<String> stringMatcher) {
//        checkNotNull(stringMatcher);
//        return new BoundedMatcher<View, TextView>(TextView.class) {
//            @Override
//            public void describeTo(Description description) {
//                description.appendText("with text: ");
//                stringMatcher.describeTo(description);
//            }
//
//            @Override
//            public boolean matchesSafely(TextView textView) {
//                return stringMatcher.matches(textView.getText().toString());
//            }
//        };
//    }


}
