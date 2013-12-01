package com.eincs.android.kupid.widget;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;

import com.eincs.android.kupid.R;

import eu.inmite.android.lib.dialogs.SimpleDialogFragment;
import eu.inmite.android.lib.dialogs.SimpleDialogFragment.SimpleDialogBuilder;

public final class Dialogs {
	private Dialogs() {
	}

	public static ProgressDialog showProgressDialog(Context context) {
		String message = context.getResources().getString(R.string.please_wait);
		return ProgressDialog.show(context, null, message);
	}
	
	public static SimpleDialogBuilder createPrompt(FragmentActivity activity, int requestCode) {
		SimpleDialogBuilder builder = SimpleDialogFragment.createBuilder(activity, activity.getSupportFragmentManager());
		// default title of the dialog is app_name
		builder.setTitle(R.string.app_name);
		builder.setRequestCode(requestCode);
		builder.setPositiveButtonText(android.R.string.yes);
		builder.setNegativeButtonText(android.R.string.no);
		return builder;
	}
	
	@Deprecated
	public static void showSystemAlertDialog(Context context, int mesResId, final Runnable yesTask) {
		
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
