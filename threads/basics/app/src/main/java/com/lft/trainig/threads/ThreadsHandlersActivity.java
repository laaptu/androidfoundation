package com.lft.trainig.threads;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

/**
 * Created by laaptu on 8/12/15.
 */
public class ThreadsHandlersActivity extends BaseActivity {
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

    public void startThread(View view) {
        customThread.execute(firstRunnable);

    }

    public void secondRunnable(View view) {
        //customThread.execute(secondRunnable);
        //interruptThread();
        minorRunnableTest();
    }

    private void minorRunnableTest() {
        SomeOtherThread someOtherThread = new SomeOtherThread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable run");
            }
        });
        someOtherThread.start();
    }

    public void thirdRunnable(View view) {
        customThread.execute(thirdRunnable);
    }

    public void fourthRunnable(View view) {
        customThread.execute(fourthRunnable);
    }


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

    public class FirstRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("2x" + i + "=" + 2 * i);
                if (i == 40) {
//                    try {
//                        Thread.currentThread().join();
//                        Thread.currentThread().interrupt();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    //interruptThread();
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void interruptThread() {
        /**Works only when called from run() */
        if (customThread != null) {
            try {
                customThread.join();
                customThread.interrupt();
            } catch (InterruptedException e) {
                e.printStackTrace();
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


    public class SomeOtherThread extends Thread {
        public SomeOtherThread(Runnable runnable) {
            super(runnable);
        }

        @Override
        public void run() {
            System.out.println("Thread run");
            //super.run();
            for (int i = 0; i < 10; i++)
                System.out.println(i);
            /**First and foremost this will run always
             * even though you provide runnable, but runnable to run
             * , you must call super.run(), which will now execute the runnable,
             * but if no super.run(), it will only call this run()*/
            super.run();


//            System.out.println("Thread run");
//            Looper.prepare();
//            Looper.loop();
        }
    }
}
