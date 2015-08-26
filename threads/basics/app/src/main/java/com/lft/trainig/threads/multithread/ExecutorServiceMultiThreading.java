package com.lft.trainig.threads.multithread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.lft.trainig.threads.BaseActivity;
import com.lft.trainig.threads.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.Bind;

/**
 * Class responsible for showing demo of multi threading using
 * Executor Service</br>
 * Executor service simply takes runnable as params </br>
 * and based upon on number of threads initialized, it will assign </br>
 * runnables to those threads</br>
 * Use ddms , process and threads to see how thread pools are made </br>
 * using executor service
 */
public class ExecutorServiceMultiThreading extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_threadpool;
    }

    private static final int TRIANGLE_LEFT = 1, TRIANGLE_RIGHT = 2, FIB = 3;

    @Bind(R.id.fib_txt)
    TextView fibText;
    @Bind(R.id.left_txt)
    TextView leftTxt;
    @Bind(R.id.right_txt)
    TextView rightTxt;

    private CustomHandler updateHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * Associating custom handler with our main UI(),so that </br>
         * it can make updates via main UI </br>*/
        updateHandler = new CustomHandler();

    }

    private ExecutorService executorService;

    public void threadPool(View view) {
        initThreadPool();
    }

    private void initThreadPool() {
        /**
         * CPU processors ,mainly multicore processors each core count as a single processor
         * Here we are initializing thread pool size </br>
         * and then assigning runnables to it</br>
         * as per thread pool size , it will then run the runnables in those threads</br>
         */
        int numProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("NumProcessor = " + numProcessors);
        executorService = Executors.newFixedThreadPool(numProcessors);
        executorService.submit(new FibonacciRunnable());
        executorService.submit(new TriangleLeft());
        executorService.submit(new TriangleRight());

    }

    /**
     * Called from our thread </br>
     * see necessary runnables call </br>
     * it in turn notifies our custom handler</br>
     * to update the UI</br>
     */
    private void submitMessage(int what, String msg) {
        Message message = updateHandler.obtainMessage(what, msg);
        //message.what = what;
        //message.obj = msg;
        //updateHandler.sendMessage(message);
        message.sendToTarget();
    }

    /**
     * Custom handler used to update the UI</br>
     * Remember again, this handler is not updating the UI</br>
     * Instead it is calling main thread to do all the updating task </br>
     */
    public class CustomHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FIB:
                    String fibVal = (String) msg.obj;
                    fibText.append("\n" + fibVal);
                    break;
                case TRIANGLE_LEFT:
                    String leftVal = (String) msg.obj;
                    leftTxt.append("\n" + leftVal);
                    break;
                case TRIANGLE_RIGHT:
                    String rightVal = (String) msg.obj;
                    rightTxt.append("\n" + rightVal);
                    break;
            }
        }
    }


    /**
     * Simple runnable to print left * triangle
     */
    public class TriangleLeft implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                String val = " * ";
                for (int j = 0; j < i; j++)
                    val += " * ";
                System.out.println(val);
                submitMessage(TRIANGLE_LEFT, val);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Simple runnable to print * right triangle
     */
    public class TriangleRight implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                String val = "";
                for (int j = 10; j > 0; j--) {
                    if (j > i)
                        val += "  ";
                    else
                        val += " * ";
                }
                System.out.println(val);
                submitMessage(TRIANGLE_RIGHT, val);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /**
     * Simple runnable to print Fibonacci numbers
     */
    public class FibonacciRunnable implements Runnable {
        int a = 0, b = 1, sum = 0;

        @Override
        public void run() {
            System.out.println(a);
            System.out.println(b);
            for (int i = 0; i < 10; i++) {
                sum = a + b;
                a = b;
                b = sum;
                System.out.println(sum);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                submitMessage(FIB, String.valueOf(sum));

            }

        }
    }


}
