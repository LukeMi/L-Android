package com.lukemi.android.tutorial.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolManager {

    private static ThreadPoolManager tpm = new ThreadPoolManager();
    private ThreadPoolExecutor threadPoolExecutor;
    private LinkedBlockingDeque service = new LinkedBlockingDeque();

    private ThreadPoolManager() {
        threadPoolExecutor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5), handler);
        threadPoolExecutor.execute(runnable);

    }

    public static ThreadPoolManager getInstance() {
        if (tpm == null) {
            tpm = new ThreadPoolManager();
        }
        return tpm;
    }


    private RejectedExecutionHandler handler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                service.push(new FutureTask<Object>(r, null));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                FutureTask futureTask = null;
                try {
                    futureTask = (FutureTask) service.take();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (futureTask != null) {
                    threadPoolExecutor.execute(futureTask);
                }
            }
        }
    };

    public <T> void excute(FutureTask<T> futureTask) {
        if (futureTask != null) {
            try {
                service.put(futureTask);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
