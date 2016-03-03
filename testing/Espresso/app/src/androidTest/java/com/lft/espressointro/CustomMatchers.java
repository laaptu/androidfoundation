package com.lft.espressointro;

import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

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
}
