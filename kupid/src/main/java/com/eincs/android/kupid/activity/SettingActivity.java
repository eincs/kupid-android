package com.eincs.android.kupid.activity;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;

import com.actionbarsherlock.app.SherlockPreferenceActivity;
import com.eincs.android.kupid.R;

public class SettingActivity extends SherlockPreferenceActivity {

    @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.Theme_Sherlock_Light_NoActionBar);
        super.onCreate(savedInstanceState);

		// 일반 Activity와는 달리 PreferenceActivity는 setContentView를 사용해서는 안됩니다.
		// setContentView(R.layout.settings_activity);

        // 아래와 같이 설정합니다.
        addPreferencesFromResource(R.xml.pref);

        ListPreference searchEngineSettings = (ListPreference)findPreference("searching_engine_setting");
        searchEngineSettings.setSummary(searchEngineSettings.getValue());
        searchEngineSettings.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
            
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                
                preference.setSummary((String)newValue);
                
                // return false; 로 리턴하면 변경을 취소합니다.
                return true;
            }
        });

    }

}