package com.lft.espressointro.mockito;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lft.espressointro.R;

/**
 * Created by laaptu on 3/14/16.
 */
public class LoadDataActivity extends AppCompatActivity {

    TextView textView;

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

    private void checkNetService() {
        if (generalUtils == null)
            generalUtils = new GeneralUtils();
        ConnectivityManager connectivityManager = generalUtils.getConnectivityManager(this);
        System.out.println("ConnManager 1 @checkNetService ="+(connectivityManager==null));
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        int stringId = networkInfo != null && networkInfo.isConnected() ?
                R.string.info_net_connected : R.string.info_net_disconnected;
        textView.setText(getString(stringId));
    }
}
