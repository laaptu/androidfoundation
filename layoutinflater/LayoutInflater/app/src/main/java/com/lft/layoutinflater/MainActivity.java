package com.lft.layoutinflater;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    //rootParent =7f0c000a
//     inflated_view=0x7f0c0005;
//    inflated_view_parent=0x7f0c0006;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        goToSimpleInflater();
//        goToStaticFragment();
        goToDynamicFragment();
    }

    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_simple:
                goToSimpleInflater();
                break;
            case R.id.btn_static:
                goToStaticFragment();
                break;
            case R.id.btn_dynamic:
                goToDynamicFragment();
                break;
        }
    }

    private void goToSimpleInflater() {
        startActivity(new Intent(this, SimpleInflaterActivity.class));
    }

    private void goToStaticFragment() {
        startActivity(new Intent(this, StaticFragmentActivity.class));
    }

    private void goToDynamicFragment() {
        startActivity(new Intent(this, DynamicFragmentActivity.class));
    }

    public static String getLayoutId(int id) {
        Timber.d("Hex string of id =%s", Integer.toHexString(id));
        switch (id) {
            case R.id.inflated_view:
                return "R.id.inflated_view";
            case R.id.inflated_view_parent:
                return "R.id.inflated_view_parent";
            case R.id.root_parent:
                return "R.id.root_parent";
        }
        return "No parent with given id";
    }
}
