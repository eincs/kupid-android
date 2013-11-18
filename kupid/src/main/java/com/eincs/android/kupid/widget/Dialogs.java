package com.eincs.android.kupid.widget;

import android.app.ProgressDialog;
import android.content.Context;

import com.eincs.android.kupid.R;

public final class Dialogs {
	private Dialogs() {}
	
	public static ProgressDialog showProgressDialog(Context context) {
		String message = context.getResources().getString(R.string.please_wait);
		return ProgressDialog.show(context, null, message);
	}
}
