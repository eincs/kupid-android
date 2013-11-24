package com.eincs.android.kupid.controller;

import android.app.Dialog;

import com.google.common.util.concurrent.FutureCallback;

public class DialogCallback<T> implements FutureCallback<T> {
	
	private final Dialog dialog;
	
	public DialogCallback(Dialog dialog) {
		this.dialog = dialog;
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
	}

}
