package com.lft.trainig.threads;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

/**
 * A class which has
 * <ol>
 * <li>Single thread with custom handler</li>
 * <li>Multiple runnables running on that single thread</li>
 * </ol>
 */
public class SingleThreadMultipleRunnable extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_threadhandlers;
    }

    private FirstRunnable firstRunnable;
    private SecondRunnable secondRunnable;
    private ThirdRunnable thirdRunnable;
    private FourthRunnable fourthRunnable;
    private CustomThread customThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firstRunnable = new FirstRunnable();
        secondRunnable = new SecondRunnable();
        thirdRunnable = new ThirdRunnable();
        fourthRunnable = new FourthRunnable();
        customThread = new CustomThread("OurThread");
        customThread.start();
    }

    /**
     * Button click listeners to pass different runnable</br>
     * to the custom threads </br>
     */

    public void firstRunnable(View view) {
        customThread.execute(firstRunnable);

    }

    public void secondRunnable(View view) {
        customThread.execute(secondRunnable);
    }

    public void thirdRunnable(View view) {
        customThread.execute(thirdRunnable);
    }

    public void fourthRunnable(View view) {
        customThread.execute(fourthRunnable);
    }


    /**
     * Custom thread with custom handler</br>
     * The custom handler is responsible for delegating runnable tasks to the thread</br>
     */
    public class CustomThread extends Thread {
        public Handler handler;

        public CustomThread(String name) {
            super(name);
        }

        public void execute(Runnable runnable) {
            handler.post(runnable);
        }

        public CustomThread(Runnable runnable) {
            super(runnable);
        }


        @Override
        public void run() {
            try {
                // preparing a looper on current thread
                // the current thread is being detected implicitly
                Looper.prepare();

                //Log.i(TAG, "DownloadThread entering the loop");

                // now, the handler will automatically bind to the
                // Looper that is attached to the current thread
                // You don't need to specify the Looper explicitly
                handler = new Handler();

                // After the following line the thread will start
                // running the message loop and will not normally
                // exit the loop unless a problem happens or you
                // quit() the looper (see below)
                Looper.loop();

            } catch (Throwable t) {
            }
        }
    }

    /**
     * Method to stop thread, if thread running logic is on run()</br>
     * then this method needs to be called from that method for successful</br>
     * stoppage
     */
    private void interruptThread() {
        /**Works only when called from run() of runnable or thread's run()*/
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
     * Set of different runnables which displays </br>
     * multiplication tables of 2,3,4 and five respectively
     */
    public class FirstRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("2x" + i + "=" + 2 * i);
                if (i == 10) {
//                    try {
//                        Thread.currentThread().join();
//                        Thread.currentThread().interrupt();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    /**
                     * Another important point to notice</br>
                     * Thread join() and interrupt works </br>
                     * only when called from run()*/
                    interruptThread();
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public class SecondRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 30; i++) {
                System.out.println("3x" + i + "=" + 3 * i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class ThirdRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 30; i++) {
                System.out.println("4x" + i + "=" + 4 * i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public class FourthRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 30; i++) {
                System.out.println("5x" + i + "=" + 5 * i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
