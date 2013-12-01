package com.eincs.android.kupid.activity;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockPreferenceActivity;
import com.actionbarsherlock.view.MenuItem;
import com.eincs.android.kupid.R;

public class SettingDetailActivity extends SherlockPreferenceActivity {

    @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 아래와 같이 설정합니다.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addPreferencesFromResource(R.xml.pref_detail);
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
}