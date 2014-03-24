package com.eincs.android.kupid.controller;

import android.content.Context;
import android.widget.Toast;

import com.eincs.android.kupid.KApplication;
import com.google.common.util.concurrent.FutureCallback;

public abstract class ControllerCallback<T> implements FutureCallback<T> {

    @Override
    public abstract void onSuccess(T result);

    @Override
    public void onFailure(Throwable t) {
        if (!(t instanceof ControllerException)) {
            return;
        }
        Context context = KApplication.getInstance();
        String message = ((ControllerException) t).getMessage();
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
