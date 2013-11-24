package com.eincs.android.kupid.widget;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.eincs.android.kupid.R;

public final class Dialogs {
	private Dialogs() {
	}

	public static ProgressDialog showProgressDialog(Context context) {
		String message = context.getResources().getString(R.string.please_wait);
		return ProgressDialog.show(context, null, message);
	}

	public static void showAlertDialog(Context context, int mesResId, final Runnable yesTask) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(mesResId);
		builder.setCancelable(false);
		builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				yesTask.run();
				dialog.dismiss();
			}
		});
		builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
		AlertDialog alert = builder.create();
		alert.setTitle(R.string.app_name);
		alert.show();
	}
}
