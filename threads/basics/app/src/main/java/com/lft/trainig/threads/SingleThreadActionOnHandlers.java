package com.lft.trainig.threads;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.LogPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Class which has
 * <ol>
 * <li>Single thread with custom handler</li>
 * <li>Task to be performed on thread is now passed through handlers rather than through runnables</li>
 * </ol>.
 */
public class SingleThreadActionOnHandlers extends BaseActivity {

    private static final String TAG = "ThreadHandlers2";
    private static final String TAG_LOOPER = "LooperTag";
    private static final int MULTI_2 = 2, MULTI_3 = 3, MULTI_4 = 4, MULTI_5 = 5, UPDATE_TEXT = 8, STOP_THREAD = 9;


    @Bind(R.id.info_txt)
    TextView infoText;

    @Bind(R.id.btn_stop)
    Button btnStopThread;

    @Override
    public int getLayoutId() {
        return R.layout.activity_threadhandlers;
    }

    private CustomThread customThread;
    private CustomHandler customHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customThread = new CustomThread();
        customThread.start();

        /**
         * This handler is associated with mainUI()
         */
        customHandler = new CustomHandler();

        btnStopThread.setVisibility(View.VISIBLE);
    }

    /**
     * For thread to perform certain action, we now use
     * custom thread handler message passing</br>
     * Here we are passing an empty message to say that we
     * want multiplication of two to be performed by our thread</br>
     */
    public void firstRunnable(View view) {
        customThread.handler.sendEmptyMessage(MULTI_2);
        printLogonThread();

    }

    public void secondRunnable(View view) {
        customThread.handler.sendEmptyMessage(MULTI_3);
        printLogonThread();
    }

    public void thirdRunnable(View view) {
        customThread.handler.sendEmptyMessage(MULTI_4);
        printLogonThread();

    }

    public void fourthRunnable(View view) {
        customThread.handler.sendEmptyMessage(MULTI_5);
        printLogonThread();

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

    /**
     * Method to stop thread </br>
     * Thread.join() and interrupt is effective on this case </br>
     * i.e. logic is outside run() method </br>
     * all action is carried out through normal function </br>
     * But internally thread is run ,the handler to main UI won't be called</br>
     * This is also not an effective way. See the log on thread</br>
     * It will run</br>
     * But UI update won't happen </br>
     * Since Thread.join() is called from main thread </br>
     * and main thread needs to acknowledge that Thread.join() happened</br>
     * so main thread waits for it</br>
     * and as a result your app hangs </br>
     * So the best logic would be implement some boolean as done on runnable </br>
     * http://stackoverflow.com/questions/15962646/android-thread-join-causes-application-to-hang
     * http://stackoverflow.com/questions/5382247/android-gameloop-thread-join-hangs-application
     *
     */
    @OnClick({R.id.btn_stop})
    public void stopThread() {
        if (customThread != null) {
            try {
                customThread.join();
                customThread.interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * This method simply prints the multiplication table</br>
     * with passed integer and at same time tries to update the textview</br>
     * using updateString() method</br>
     */
    private void doMultiplication(int val) {
        for (int i = 0; i < 20; i++) {
            String someStr = val + "X" + i + "=" + val * i;
            System.out.println(someStr);
            updateString(someStr);
            /**
             * Stop thread through join() works effectively</br>
             * only when called from thread or else there is chance of ANR ,
             * uncomment the following lines and you will know for yourself</br>*/
//            if (i == 10)
//                stopThread();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method to update the UI from main thread</br>
     * Remember only UI thread is capable of making changes to UI</br>
     * so handler associated with UI thread is only capable of calling the main UI</br>
     * to make UI changes
     */
    private void updateString(final String someString) {

        /**
         * When you uncomment this line, and run the app</br>
         * you will encounter error </br>
         * as per our functionality, this handler will now be associated with </br>
         * custom thread as it is called from there </br>
         * and only main UI can update the UI, not any background threads
         */
//        new Handler().post(new Runnable() {
//            @Override
//            public void run() {
//                String infoTxtStr = infoText.getText().toString();
//                infoText.setText(someString + "\n" + infoTxtStr);
//            }
//        });


        /**
         * Following statements can only update the UI</br>
         * as customHandler is associated with the main thread</br>
         * as it is defined on main thread</br>
         * Further, for efficiency reason in order to construct a message</br>
         * better to use obtainMessage() rather than creating a new one
         */
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


    /**
     * CustomThread which now uses Handler message passing</br>
     * as a form of task to be performed</br>
     * Look upon {@link SingleThreadMultipleRunnable} if you want </br>
     * same action to be performed using runnables</br>
     * Remember any handler you call from this thread will be associated with this thread</br>
     * For example for function doMultiplication() to updateString()</br>
     * look upon the commented new Handler() statement</br>
     * when you are doing this or calling anything from this thread </br>,
     * any handler will be associated this this thread</br>
     * If you uncomment the line of updateString() and try to udpate the text</br>
     * you will see exception
     * </br>
     * You can add breakpoint at different level to see </br>
     * which thread is associated with the method call using android debug mode</br>
     */
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


        /**
         * This callback will be used by Looper when it receives the message</br>
         * from the handler</br>
         * Remember doMultiplication() be defined anywhere, it will be done by our </br>
         * custom thread
         */
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
            printLogonThread();
            return true;
        }
    }

    /**
     * Method to append string
     */
    private String getNewText(String someString) {
        String infoTxtStr = infoText.getText().toString();
        return someString + "\n" + infoTxtStr;
    }

    /**
     * Method to show message queue on thread and its looper
     */
    private void printLogonThread() {
        customThread.handler.dump(new LogPrinter(Log.DEBUG, TAG), "");
        customThread.looper.setMessageLogging(new LogPrinter(Log.DEBUG, TAG_LOOPER));
    }

}
