package com.lft.layoutinflater;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        goToSimpleInflater();
//        goToStaticFragment();
        goToDynamicFragment();
    }

    public void onClick(View view) {
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
}
