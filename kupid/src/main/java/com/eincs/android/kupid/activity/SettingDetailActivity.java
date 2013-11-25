package com.eincs.android.kupid.activity;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockPreferenceActivity;
import com.eincs.android.kupid.R;

public class SettingDetailActivity extends SherlockPreferenceActivity {

    @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 아래와 같이 설정합니다.
        addPreferencesFromResource(R.xml.pref_detail);

    }

}