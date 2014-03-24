package com.eincs.android.kupid.utils;

import java.util.concurrent.ExecutionException;

import com.google.common.util.concurrent.ListenableFuture;

public final class MoreFutures {
    private MoreFutures() {
    }

    public static <T> T get(ListenableFuture<T> future) {
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
