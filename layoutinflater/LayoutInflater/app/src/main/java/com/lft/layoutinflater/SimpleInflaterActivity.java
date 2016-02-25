package com.lft.layoutinflater;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import timber.log.Timber;

public class SimpleInflaterActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private FrameLayout frameLayout;

    private LayoutInflater layoutInflater;

    /**
     * The main difference between attachtoRoot true and false
     * true: creates view from Layout, sets layout params and add it to the parent or viewgroup
     * false: creates view from layout,sets layout params
     * if viewgroup is null, simply creates a view from layout
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_inflater);
        linearLayout = (LinearLayout) findViewById(R.id.inflated_view_parent);
        frameLayout = (FrameLayout) findViewById(R.id.inflated_view_parent_parent);
        layoutInflater = LayoutInflater.from(this);
    }

    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_inflate:
//                simpleInflateTest();
                nullViewGroupTest();
//                attachFalseViewGroupTest();
                break;
            case R.id.btn_addchild:
                addSimpleChild();
                break;
        }
    }

    private void nullViewGroupTest() {
        /**
         * By doing this it simply creates a view and return it back
         * look at the source code where it says what to return*/
        View view = layoutInflater.inflate(R.layout.simple_inflate, null, true);

        //here is simply passes view or creates a new view like done in addSimpleChild
        // but without any layout params, if following is commented out we won't see any view
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
//        layoutParams.setMargins(10, 10, 10, 10);
//        view.setLayoutParams(layoutParams);
        logView(view);
        //linearLayout.addView(view);
        findChildCount();
    }

    /**
     * During this case
     * it creates a view from R.layout,simple_inflate and assigns the params of linearLayout
     * and returns the created view from R.layout.simple_inflate, but doesn't add the view
     * to the linearLayout
     */
    private void attachFalseViewGroupTest() {
        View view = layoutInflater.inflate(R.layout.simple_inflate, linearLayout, false);
        Timber.d("LinearLayout id =%s", MainActivity.getLayoutId(linearLayout.getId()));
        logView(view);
        //linearLayout.addView(view);
        findChildCount();
    }

    private void addSimpleChild() {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
        //if following is commented out no view is added
        // so making the layout params automatically match, layoutInflater plays a vital role
        //which simply adds view but without any params
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
//        layoutParams.setMargins(10, 10, 10, 10);
//        linearLayout.setLayoutParams(layoutParams);
        //style

        this.linearLayout.addView(linearLayout);
        Timber.d("LinearLayout id =%s", MainActivity.getLayoutId(this.linearLayout.getId()));
        logView(linearLayout);

        findChildCount();

    }

    /**
     * What LayoutInflater does
     * takes view from layout = R.layout.simple_inflate
     * assigns params to the layout based upon LinearLayout params,
     * if true, adds the layout(R.layout.simple_inflate) to the linearLayout
     * and returns the linearLayout back not the view of Layout =R.layout.simple_inflate
     */
    private void simpleInflateTest() {
        //show the diff between true and false
        //layoutInflater.inflate(R.layout.simple_inflate, linearLayout, false);
        View view = layoutInflater.inflate(R.layout.simple_inflate, linearLayout, true);
        Timber.d("LinearLayout id =%s", MainActivity.getLayoutId(linearLayout.getId()));
        logView(view);
        findChildCount();
    }

    private void findChildCount() {
        Timber.d("Total Child count =%d", linearLayout.getChildCount());
    }

    private void logView(View view) {
        Timber.d("***************************");
        Timber.d("View is null =%b", view == null);
        Timber.d("View id=%s", MainActivity.getLayoutId(view.getId()));
        Timber.d("View instance of LinearLayout =%b", view instanceof LinearLayout);
        Timber.d("View has layoutParams =%b", view.getLayoutParams() != null);
        if (view.getLayoutParams() != null) {
//            Timber.d("View params instance of FrameLayoutParams =%b", view.getLayoutParams() instanceof FrameLayout.LayoutParams);
            Timber.d("View params instance of LinearLayoutParams =%b", view.getLayoutParams() instanceof LinearLayout.LayoutParams);
        }
        Timber.d("View has parent =%b", view.getParent() != null);
        if (view.getParent() != null) {
            int id = ((ViewGroup) view.getParent()).getId();
            Timber.d("View parent id =%s", Integer.toHexString(id));
        }
        Timber.d("-----------------------------------");
    }
}
