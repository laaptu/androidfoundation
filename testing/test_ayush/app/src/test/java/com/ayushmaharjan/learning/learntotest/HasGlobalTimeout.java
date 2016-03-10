package com.ayushmaharjan.learning.learntotest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class HasGlobalTimeout {
    public static String log;
    private final CountDownLatch latch = new CountDownLatch(1);

//    @Rule
//    public Timeout globalTimeout = Timeout.seconds(5); // 10 seconds max per method tested

    @Rule
    public SomeTimeOut someTimeOut = new SomeTimeOut(5);


    @Test
    public void testSleepForTooLong() throws Exception {
        log += "ran1";
        Thread.sleep(4000); // sleep for 100 seconds
    }


    @Test
    public void testBlockForever() throws Exception {
        log += "ran2";
        //latch.await(); // will block
        Thread.sleep(6000);
    }

    public class SomeTimeOut extends Timeout {

        public SomeTimeOut(long seconds) {
            super(seconds, TimeUnit.SECONDS);
        }

        @Override
        public Statement apply(final Statement base, Description description) {
            if (description.getMethodName().equals("testBlockForever"))
                return base;
            return super.apply(base, description);
        }
    }


}