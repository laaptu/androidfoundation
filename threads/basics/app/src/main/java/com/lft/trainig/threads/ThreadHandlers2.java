package com.lft.trainig.threads;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;

/**
 * Created by laaptu on 8/20/15.
 */
public class ThreadHandlers2 extends BaseActivity {

    @Bind(R.id.info_txt)
    TextView infoText;

    @Override
    public int getLayoutId() {
        return R.layout.activity_threadhandlers;
    }

    private CustomThread customThread;
    private Handler updateHandler;
    private CustomHandler customHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customThread = new CustomThread();
        customThread.start();
        updateHandler = new Handler();
        customHandler = new CustomHandler();
    }

    public void startThread(View view) {
        customThread.handler.sendEmptyMessage(MULTI_2);

    }

    public void secondRunnable(View view) {
        customThread.handler.sendEmptyMessage(MULTI_3);


    }

    public void thirdRunnable(View view) {
        customThread.handler.sendEmptyMessage(MULTI_4);

    }

    public void fourthRunnable(View view) {
        //customThread.handler.sendEmptyMessage(MULTI_5);
        stopThread();

    }

    public class CustomHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == UPDATE_TEXT) {
                String updatedText = (String) msg.obj;
                infoText.setText(updatedText);
            }
        }
    }

    private void updateString(final String someString) {
//        new Handler().post(new Runnable() {
//            @Override
//            public void run() {
//                String infoTxtStr = infoText.getText().toString();
//                infoText.setText(someString + "\n" + infoTxtStr);
//            }
//        });
        Message message = customHandler.obtainMessage();
        message.obj = getNewText(someString);
        message.what = UPDATE_TEXT;
        customHandler.sendMessage(message);

//        updateHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                String infoTxtStr = infoText.getText().toString();
//                infoText.setText(someString + "\n" + infoTxtStr);
//            }
//        });

    }

    private void stopThread() {
        if (customThread != null) {
            try {
                customThread.join();
                customThread.interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String getNewText(String someString) {
        String infoTxtStr = infoText.getText().toString();
        return someString + "\n" + infoTxtStr;
    }


    private static final int MULTI_2 = 2, MULTI_3 = 3, MULTI_4 = 4, MULTI_5 = 5, UPDATE_TEXT = 8;

    private void doMultiplication(int val) {
        for (int i = 0; i < 10; i++) {
            String someStr = val + "X" + i + "=" + val * i;
            System.out.println(someStr);
            if (i == 9)
                updateString(someStr);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class CustomThread extends Thread implements Handler.Callback {
        private Looper looper;
        private Handler handler;

        @Override
        public void run() {
            Looper.prepare();
            looper = Looper.myLooper();
            handler = new Handler(looper, this);
            Looper.loop();
        }

        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case MULTI_2:
                    doMultiplication(MULTI_2);
                    break;
                case MULTI_3:
                    doMultiplication(MULTI_3);
                    break;
                case MULTI_4:
                    doMultiplication(MULTI_4);
                    break;
                case MULTI_5:
                    doMultiplication(MULTI_5);
                    break;
            }
            return true;
        }
    }

}
