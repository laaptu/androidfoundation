package com.lft.trainig.threads;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;

/**
 * A simple class
 * <ol>
 * <li>Which shows custom thread with normal run and runnable object</li>
 * <li>which show stopping by setting a certain boolean value to false</li>
 * <li>which shows stopping the thread by calling join() and interrupt is not best suited for task
 * which has actions on run() </li>
 * </ol>
 */
public class ThreadStartStopActivity extends BaseActivity {

    @Bind(R.id.info_txt)
    TextView infoText;

    private static final int COUNT_10 = 1, COUNT_15 = 2, COUNT_COMPLETE = 3;

    private CustomHandler customHandler;


    @Override
    public int getLayoutId() {
        return R.layout.activity_threadstartstop;
    }

    private CustomThread customThread;
    private CustomRunnable customRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**Whenever you define an handler in the mainUI(), it will be
         * </br>
         * associated with the mainUI(),so there the customHandler, will be</br>
         * associated with the main thread or UI thread*/
        customHandler = new CustomHandler();
    }

    /**
     * Method to start thread using custom runnable </br>
     * or its own run () method
     */
    public void startThread(View view) {
        //startNormalThread();
        startRunnableThread();
    }


    /**
     * Method to start thread without any runnable associated with it</br>
     * for this to work, do add some logic on run() method of thread</br>
     */
    private void startNormalThread() {
        customThread = new CustomThread();
        customThread.start();
    }

    /**
     * Starting the thread with custom runnable </br>
     */
    private void startRunnableThread() {
        customRunnable = new CustomRunnable();
        customThread = new CustomThread(customRunnable);
        customRunnable.setTag(customThread.tag);
        customThread.start();

    }

    /**
     * Method to stop thread</br>
     * depending upon your implementation perform necessary action</br>
     * if thread is started with runnable via startRunnableThread() call stopThreadByRunnable</br>
     * if thread is started without runnable via startNormalThread() call stopThreadByBoolean</br>
     * stopThreadByInterrupt() is to show that thread stop doesn't work via this method if logic</br>
     * is on run()
     */
    public void stopThread(View view) {
        //stopThreadByInterrupt();
        //stopThreadByBoolean();
        stopThreadByRunnable();


    }


    /**
     * When thread logic is in run() method ,
     * while trying to stop the thread by interrupt()</br>
     * is not an elegant method</br>
     * as you will see blockage or ANR using this interrupt method
     */
    private void stopThreadByInterrupt() {
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
     * It is always elegant to stop the thread() by setting some boolean value to false</br>
     * in case  the logic is in run() </br>
     * use this method of stoppage on either runnable or on thread run</br>
     * wherever it is called
     */
    private void stopThreadByBoolean() {
        if (customThread != null) {
            customThread.stopThread();
        }
    }

    /**
     * Simply setting some boolean value to false to stop the run process of thread
     */
    private void stopThreadByRunnable() {
        if (customRunnable != null) {
            customRunnable.killRunnable();
        }
    }


    /**
     * Custom Thread which can run its own run() code or run</br>
     * the runnable passed to it
     */
    public class CustomThread extends Thread {

        public String tag = "";

        private boolean run = true;
        private int count = 1;


        public static final String TAG = "CustomThread";

        public CustomThread() {
            initTag();
        }

        private void initTag() {
            tag = String.valueOf((int) (Math.random() * 1000));
            setName(TAG);
        }

        public CustomThread(Runnable runnable) {
            super(runnable);
            initTag();
        }

        public void stopThread() {
            run = false;
        }

        @Override
        public void run() {
            /**
             * If we call super.run(), it will run any runnable associated
             * with the thread else it will simply execute this run().
             * </br>
             * so if we initialize the thread by CustomThread(Runnable runnable)</br>
             * but don't call super.run() here, it won't execute the runnable </br>,
             * it will only simply run whatever defined here*/
            super.run();
            /**
             * Use following commented line of codes, if you don't want runnable</br>
             * and do comment super.run() as well , otherwise both will run*/
//            while (run && count > 0) {
//                count++;
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                System.out.println(tag + ":: count =" + count);
//            }
        }
    }


    /**
     * Simple custom runnable which does counting</br>
     * and at the same time updates the textView using customHandler</br>
     * Since custom handler is associated with main thread</br>
     * it is able to udpate the UI
     */
    public class CustomRunnable implements Runnable {

        int count = 1;
        public String tag = "";
        private boolean run = true;

        public CustomRunnable() {
        }

        public void setTag(String tag) {
            this.tag = "Runnable " + tag;
        }

        public void killRunnable() {
            run = false;
        }


        /**
         * To stop the run(), it is elegant to set some boolean value to false</br>
         * in this case run =false, will stop the task
         */
        @Override
        public void run() {
            while (count > 0) {
                count++;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (count == 10)
                    customHandler.sendEmptyMessage(COUNT_10);
                if (count == 15)
                    customHandler.sendEmptyMessage(COUNT_15);

                if (count > 30) {
                    customHandler.sendEmptyMessage(COUNT_COMPLETE);
                    break;
                }

                System.out.println(tag + ":: count =" + count);
                if (!run)
                    break;
            }
        }
    }

    /**
     * CustomHandler to update the UI</br>
     * Internally this handler is associated with main thread or UI</br>
     * as it is defined there</br>
     * so it simply updates the main thread to make change to info text</br>
     * i.e. it is not updating the UI</br>
     * but passing message to main thread to update the UI
     */

    public class CustomHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == COUNT_10)
                infoText.append("\n COUNT IS 10");
            else if (msg.what == COUNT_15)
                infoText.append("\n COUNT IS 15");
            else if (msg.what == COUNT_COMPLETE)
                infoText.append("\n COUNT COMPLETE");
        }
    }


}
