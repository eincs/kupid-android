package com.eincs.android.kupid.controller;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;

import com.google.common.util.concurrent.FutureCallback;

public class DialogCallback<T> implements FutureCallback<T> {

    private final Dialog dialog;
    private final DialogFragment dialogFragment;

    public DialogCallback(Dialog dialog) {
        this.dialog = dialog;
        this.dialogFragment = null;
    }

    public DialogCallback(DialogFragment dialogFragment) {
        this.dialog = null;
        this.dialogFragment = dialogFragment;
    }

    @Override
    public void onSuccess(T result) {
        dismiss();
    }

    @Override
    public void onFailure(Throwable t) {
        dismiss();
    }

    private void dismiss() {
        try {
            dialog.dismiss();
        } catch (Exception e) {

        }
        try {
            dialogFragment.dismiss();
        } catch (Exception e) {

        }
    }
}
