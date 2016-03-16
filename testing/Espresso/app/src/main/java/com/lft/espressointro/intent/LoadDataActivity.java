package com.lft.espressointro.intent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lft.espressointro.R;
import com.lft.espressointro.mockito.GeneralUtils;
import com.lft.espressointro.twoactivities.SecondActivity;

/**
 * Created by laaptu on 3/14/16.
 */
public class LoadDataActivity extends AppCompatActivity {

    TextView textView;

    public String tag = "Hello";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loaddata);
        textView = (TextView) findViewById(R.id.txt_info);
        checkPermissionGranted();
    }

    private void checkPermissionGranted() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, 10);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    public void btnClick(View view) {
        checkNetService();
    }

    private GeneralUtils generalUtils;

    public void setGeneralUtils(GeneralUtils generalUtils) {
        this.generalUtils = generalUtils;

    }

    public static int RETURN_VALUE = 10;
    public static int RETURN_FROM_CAMERA = 11;
    public static String RETURN_STRING = "returnString", FILE_PATH = "filePath";

    private void checkNetService() {
        ConnectivityManager connectivityManager = generalUtils.getConnectivityManager(this);
        System.out.println("ConnManager 1 @checkNetService =" + (connectivityManager == null));
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        int stringId = networkInfo != null && networkInfo.isConnected() ?
                R.string.info_net_connected : R.string.info_net_disconnected;
        textView.setText(getString(stringId));
    }

    public void goToSecondActivity(View view) {
        startSecondActivity();
    }

    public void startSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.setAction(RETURN_STRING);
        startActivityForResult(intent, 10);
//        return true;
    }

    public void clickPic(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(RETURN_STRING,"SomeString");
        startActivityForResult(intent, RETURN_FROM_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1 && requestCode == RETURN_VALUE && data != null && data.hasExtra(RETURN_STRING)) {
            textView.setText(data.getStringExtra(RETURN_STRING));
            return;
        }

        if (resultCode == 1 && requestCode == RETURN_FROM_CAMERA && data != null) {
            String filePath = data.getStringExtra(FILE_PATH);
            System.out.println(filePath);

        }
        textView.setText("Error getting intent values");

    }
}
