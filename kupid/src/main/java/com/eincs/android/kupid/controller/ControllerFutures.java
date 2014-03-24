package com.eincs.android.kupid.controller;

import java.util.concurrent.Executor;

import android.app.Dialog;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.DialogFragment;

import com.eincs.android.kupid.utils.HandlerExecutor;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

public final class ControllerFutures {
    private static final Executor mCallbackExecutor = new HandlerExecutor(new Handler(Looper.getMainLooper()));

    private ControllerFutures() {
    }

    public static <T> void addCallback(ListenableFuture<T> future, FutureCallback<T> callback) {
        Futures.addCallback(future, callback, mCallbackExecutor);
    }

    public static <T> void dismissDialogOnComplete(ListenableFuture<T> future, Dialog dialog) {
        Futures.addCallback(future, new DialogCallback<T>(dialog), mCallbackExecutor);
    }

    public static <T> void dismissDialogOnComplete(ListenableFuture<T> future, DialogFragment dialog) {
        Futures.addCallback(future, new DialogCallback<T>(dialog), mCallbackExecutor);
    }
}
