package com.lft.layoutinflater;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import timber.log.Timber;

public class DynamicFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_fragment);
    }

    private static final String TAG = "FragmentId";

    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.add_fragment:
                getFragmentManager().beginTransaction().replace(R.id.second_parent, new DynamicFragment(), TAG).commit();
                break;
        }
    }

    public static class DynamicFragment extends Fragment {
        public DynamicFragment() {

        }

        private View view;

        /**
         * Why the inflater shouldn't attach to the root
         * https://possiblemobile.com/2013/05/layout-inflation-as-intended/
         * https://trevore.com/post/android-fragment-gotchas
         * */

        /**
         * One way of finding out what happens when inflater.inflate attach to true is written
         * it will crash, because the framework, itself wants to add the child and if
         * it finds that it has added the child by attach to true, it will throw crash
         * as the framework only wants to add the child
         */

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//            inflater.inflate(R.layout.dynamic_fragment, container);
//            and
//            inflater.inflate(R.layout.dynamic_fragment, container,true);
//            are same thing and both of them will crash, look on the log error to find out
            //at what point the error comes
            view = inflater.inflate(R.layout.dynamic_fragment, container,false);
            Timber.d("onCreateView()");
            Timber.d("Container is null =%b", container == null);
            if (container != null) {
                Timber.d("Container id =%s", Integer.toHexString(container.getId()));
            }
            logView();
            return view;
        }

        private void logView() {
            Timber.d("*********************************");
            Timber.d("View is null =%b", view == null);
            Timber.d("View id =%s",Integer.toHexString(view.getId()));
            Timber.d("View instance of LinearLayout =%b", view instanceof LinearLayout);
            Timber.d("View has layoutParams =%b", view.getLayoutParams() != null);
            Timber.d("View has parent =%b", view.getParent() != null);
            if (view.getParent() != null) {
                int id = ((ViewGroup) view.getParent()).getId();
                Timber.d("Parent of View =%s", Integer.toHexString(id));
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
