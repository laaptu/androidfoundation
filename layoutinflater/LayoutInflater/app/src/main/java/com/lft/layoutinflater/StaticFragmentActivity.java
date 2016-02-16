package com.lft.layoutinflater;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import timber.log.Timber;

public class StaticFragmentActivity extends AppCompatActivity {
    //http://stackoverflow.com/questions/16883831/android-fragment-is-given-a-null-container-in-oncreateview

    LinearLayout parentlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_fragment);
        parentlayout = (LinearLayout) findViewById(R.id.root_parent);
        Fragment fragment = getFragmentManager().findFragmentById(R.id.static_fragment);
        Timber.d("Fragment is of type StaticFragment=%b", fragment instanceof StaticFragment);
        Timber.d("Main Parent Id =%s",MainActivity.getLayoutId(parentlayout.getId()));

        parentlayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                parentlayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                Timber.d("Total child count of parent =%d", parentlayout.getChildCount());
            }
        });
    }


    public static class StaticFragment extends Fragment {
        public StaticFragment() {

        }

        /**
         * In case of static layout, the viewgroup is null on onCreateView(),
         * so here we only get the view i.e. we won't get the layout params of the view
         * i.e. view is not added to the container at all
         * Internally there must be some place on the Activity or FragmentManager
         * where it takes the view from ViewContainer and then looks upon the params
         * provided on <fragment> and applies the same params as there i.e.
         * completely ignoring the params of view defined on R.layout.static_fragment
         * The reason container is null is that the , the view is not yet defined or created
         */
        private View view;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.static_fragment, container, true);
            Timber.d("onCreateView()");
            Timber.d("Container is null =%b", container == null);
            logView();
            return view;
        }

        private void logView() {
            Timber.d("*********************************");
            Timber.d("View is null =%b", view == null);
            Timber.d("View id =%s",MainActivity.getLayoutId(view.getId()));
            Timber.d("View instance of LinearLayout =%b", view instanceof LinearLayout);
            Timber.d("View has layoutParams =%b", view.getLayoutParams() != null);
            Timber.d("View has parent =%b", view.getParent() != null);
            if (view.getParent() != null) {
                int id = ((ViewGroup) view.getParent()).getId();
                Timber.d("Parent of View =%s", MainActivity.getLayoutId(id));
            }
            Timber.d("---------------------------------------");
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            Timber.d("onActivityCreated()");
            logView();
        }
    }
}
