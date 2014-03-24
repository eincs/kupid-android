package com.eincs.android.kupid.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DefaultThreadPoolExecutor extends ThreadPoolExecutor {

    private static BlockingQueue<Runnable> createWorkQueue() {
        return new LinkedBlockingQueue<Runnable>();
    }

    public DefaultThreadPoolExecutor() {
        super(1, 1, 1, TimeUnit.HOURS, createWorkQueue());
    }
}
