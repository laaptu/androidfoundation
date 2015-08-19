package com.lft.trainig.threads;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;

/**
 * Created by laaptu on 8/10/15.
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
        customHandler = new CustomHandler();
        doSomething();
    }

    public void startThread(View view) {
        startRunnableThread();
        new Runnable() {
            @Override
            public void run() {
                System.out.println("This is runnable");
            }
        };


    }

    public void doSomething() {
        System.out.println("Hi I am leanring");
        ;
        System.out.println("Do someting");
        int a = 10;

    }

    public class SomeRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("Hi I am leanring");
            ;
            System.out.println("Do someting");
            int a = 10;
        }
    }

    private void startNormalThread() {
        customThread = new CustomThread();
        customThread.start();
    }

    private void startRunnableThread() {
        customRunnable = new CustomRunnable();
        customThread = new CustomThread(customRunnable);
        customRunnable.setTag(customThread.tag);
        customThread.start();

    }

    public void stopThread(View view) {
        //stopThreadByBoolean();
        //stopThreadByInterrup();
        stopThreadByRunnable();


    }

    private void stopThreadByRunnable() {
        //stopThreadByBoolean();
        if (customRunnable != null) {
            customRunnable.killRunnable();
        }
    }

    private void stopThreadByInterrup() {
        if (customThread != null) {
            customThread.interrupt();
            try {
                customThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void stopThreadByBoolean() {
        if (customThread != null) {
            customThread.stopThread();

        }
    }


    public class CustomThread extends Thread {

        public String tag = "";

        private boolean run = true;
        private int count = 1;

        public CustomThread() {
            initTag();
        }

        private void initTag() {
            tag = String.valueOf((int) (Math.random() * 1000));
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
            while (run) {
                super.run();
            }
            while (run && count > 0) {
                count++;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(tag + ":: count =" + count);
            }
        }
    }

    public class DemoRunnable implements Runnable {
        public DemoRunnable() {

        }

        @Override
        public void run() {
            while (true) {
                System.out.println("DemoRunnable");
            }
        }
    }

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


    public class SomeThread extends Thread {
        private int count = 1;
        private String tag;

        public SomeThread() {
            tag = String.valueOf((int) Math.random() * 100);
        }


        @Override
        public void run() {
            super.run();
            while (count > 1) {
                count++;
                System.out.println(" Tag " + tag + " Counting " + count);
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }
}
