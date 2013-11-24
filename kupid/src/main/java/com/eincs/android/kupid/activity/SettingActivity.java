package com.eincs.android.kupid.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;

import com.actionbarsherlock.app.SherlockPreferenceActivity;
import com.eincs.android.kupid.R;

public class SettingActivity extends SherlockPreferenceActivity {

    @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 아래와 같이 설정합니다.
        addPreferencesFromResource(R.xml.pref);

        Preference logOutSettings = (Preference)findPreference("logoutSetting");
        logOutSettings.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference preference) {
				Builder dialog = new AlertDialog.Builder(SettingActivity.this);
				dialog.setTitle(R.string.setting_logout);
				dialog.setMessage(R.string.setting_logout_message);
				dialog.setIcon(R.drawable.ic_launcher);
				dialog.setPositiveButton(android.R.string.yes, new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// 로그 아웃 시켜줌
						
					}
				});
				dialog.setNegativeButton(android.R.string.no, new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				});
				
				dialog.show();
				return true;
			}
		});

    }

}