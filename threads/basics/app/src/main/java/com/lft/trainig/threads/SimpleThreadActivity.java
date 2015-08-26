package com.lft.trainig.threads;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

/**
 * A simple class which
 * <ol>
 *     <li>has custom thread</li>
 *     <li>has custom handler</li>
 *     <li>has a local handler</li>
 *     <li>has a custom runnable</li>
 * </ol>
 */
public class SimpleThreadActivity extends BaseActivity implements Handler.Callback {

    private FirstHandler firstHandler;
    public static final int COUNT_COMPLETE = 1;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * Associating the handlers with  our MainUI thread*/
        firstHandler = new FirstHandler();
        handler = new Handler(Looper.getMainLooper(), this);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_simplethread;
    }

    public void startThread(View view) {
//        FirstThreads firstThreads = new FirstThreads();
//        firstThreads.start();

        FirstRunnable firstRunnable = new FirstRunnable();
        FirstThreads firstThreads = new FirstThreads(firstRunnable);
        firstThreads.start();

    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.what == COUNT_COMPLETE)
            System.out.println("Count is complete @Activity callback");
        return false;
    }


    /**
     * Custom thread that does count down to 9 </br>
     * either you can do the counting action on Threads run() method </br>
     * or you can provide runnable with custom action
     */
    public class FirstThreads extends Thread {
        public static final String TAG = "FirstThreads";

        public FirstThreads() {

        }

        public FirstThreads(Runnable runnable) {
            super(runnable);
        }

        @Override
        public void run() {
            System.out.println(TAG + " @run()");
            super.run();
//            int count = 0;
//            while (count < 10) {
//                count++;
//                System.out.println(count);
//            }
//            firstHandler.sendEmptyMessage(COUNT_COMPLETE);
        }
    }

    /**
     * Counting action i.e. count up to 9 </br>
     * this is set of action that can be passed on to thread for execution
     */
    public class FirstRunnable implements Runnable {

        @Override
        public void run() {
            int count = 0;
            while (count < 10) {
                count++;
                System.out.println(count);
            }
            /**
             * Whenever the above action is complete, following handler action
             * is called. This is similar to saying that Thread has completed
             * the counting action. And for handlers to receive this message
             * on UI thread, it must be declared on UI thread as done in onCreate() method
             */
            firstHandler.sendEmptyMessage(COUNT_COMPLETE);
            handler.sendEmptyMessage(COUNT_COMPLETE);
        }
    }

    /**
     * Our Handler which handles the message
     */
    public class FirstHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == COUNT_COMPLETE)
                System.out.println("Count is complete @FirstHandler callback");
        }
    }
}
