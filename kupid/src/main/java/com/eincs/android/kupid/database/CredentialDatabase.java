package com.eincs.android.kupid.database;

import com.eincs.android.kupid.model.KCredentialModel;
import com.google.common.base.Strings;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class CredentialDatabase {
	private static final String PREFERENCE_NAME = "credentials";
	private static final String PREFERENCE_KEY_ACCESS_TOKEN = "access_token";
	
	private final SharedPreferences mSharedPreference;
	
	public CredentialDatabase(Context context) {
		mSharedPreference = context.getSharedPreferences(PREFERENCE_NAME, 0);
	}
	
	public void saveCredential(KCredentialModel credentialModel) {
		Editor editor = mSharedPreference.edit();
		editor.putString(PREFERENCE_KEY_ACCESS_TOKEN, credentialModel.getAccessToken());
		editor.commit();
	}
	
	public KCredentialModel loadCredential() {
		if (!mSharedPreference.contains(PREFERENCE_KEY_ACCESS_TOKEN)) {
			return null;
		}
		String accessToken = mSharedPreference.getString("access_token", null);
		if(Strings.isNullOrEmpty(accessToken)) {
			return null;
		}
		KCredentialModel result = new KCredentialModel();
		result.setAccessToken(accessToken);
		return result;
	}
}
