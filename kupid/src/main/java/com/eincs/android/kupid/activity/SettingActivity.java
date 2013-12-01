package com.eincs.android.kupid.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;

import com.actionbarsherlock.app.SherlockPreferenceActivity;
import com.actionbarsherlock.view.MenuItem;
import com.eincs.android.kupid.KApplication;
import com.eincs.android.kupid.R;
import com.eincs.android.kupid.controller.Controller;
import com.eincs.android.kupid.controller.ControllerCallback;
import com.eincs.android.kupid.controller.ControllerFutures;
import com.eincs.android.kupid.widget.Dialogs;
import com.google.common.util.concurrent.ListenableFuture;

public class SettingActivity extends SherlockPreferenceActivity implements OnPreferenceClickListener {

	private Controller mController;
	
    @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mController = KApplication.getController();
        
        // 아래와 같이 설정합니다.
        addPreferencesFromResource(R.xml.preference);
        Preference logOutSettings = (Preference)findPreference("logoutSetting");
        logOutSettings.setOnPreferenceClickListener(this);
    }

    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			break;
		}
		return true;
	}
    
	@Override
	public boolean onPreferenceClick(Preference preference) {
		Builder dialog = new AlertDialog.Builder(SettingActivity.this);
		dialog.setTitle(R.string.setting_logout);
		dialog.setMessage(R.string.setting_logout_message);
		dialog.setPositiveButton(android.R.string.yes, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				logout();
			}
		});
		dialog.setNegativeButton(android.R.string.no, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		dialog.show();
		return true;
	}
	
	private void logout() {
		ProgressDialog dialog = Dialogs.showProgressDialog(this);
		ListenableFuture<Void> future = mController.startLogout();
		ControllerFutures.dismissDialogOnComplete(future, dialog);
		ControllerFutures.addCallback(future, new ControllerCallback<Void>() {
			@Override
			public void onSuccess(Void result) {
				Intent intent = new Intent(SettingActivity.this, CategoryActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtra(CategoryActivity.EXTRA_REDIRECT, true);
				startActivity(intent);
			}
		});
	}
}