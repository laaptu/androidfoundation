package com.training.lft.myapplication.sample1;

import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Bundle;
import android.util.Size;
import android.view.TextureView;
import android.view.View;

import com.training.lft.myapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * Created by laaptu on 12/3/15.
 */
public class SimpleCameraActivity extends Activity {

    /**
     * Some callback related to textview
     * https://github.com/stereoboy/android_samples/blob/master/SimpleCamera/src/com/example/simplecamera/MainActivity.java
     */

    /**
     * Logic wise , first
     * 1. Surface texture initialize
     * 2. Once surface texture is availabe, initialize Camera via CameraManager
     * 3. Initialization is handled by cameraDeviceCallback*/
    private Size previewSize;
    private TextureView.SurfaceTextureListener surfaceTextureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
            try {
                String cameraId = cameraManager.getCameraIdList()[0];
                CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(cameraId);
                StreamConfigurationMap streamConfigurationMap = cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                previewSize = streamConfigurationMap.getOutputSizes(SurfaceTexture.class)[0];

                cameraManager.openCamera(cameraId, cameraDeviceCallback, null);
            } catch (CameraAccessException e) {
                Timber.e("Unable to open camera %s ", e.getMessage());
            }

        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {

        }
    };

    private CameraDevice.StateCallback cameraDeviceCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(CameraDevice camera) {

        }

        @Override
        public void onDisconnected(CameraDevice camera) {

        }

        @Override
        public void onError(CameraDevice camera, int error) {

        }
    };

    private CameraCaptureSession.StateCallback cameraCaptureSessionCallback = new CameraCaptureSession.StateCallback() {
        @Override
        public void onConfigured(CameraCaptureSession session) {

        }

        @Override
        public void onConfigureFailed(CameraCaptureSession session) {

        }
    };

    @Bind(R.id.textureview)
    TextureView textureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simplecamera);
        ButterKnife.bind(this);
        textureView.setSurfaceTextureListener(surfaceTextureListener);
    }

    @OnClick({R.id.btn_click})
    public void onClick(View view) {

    }
}
