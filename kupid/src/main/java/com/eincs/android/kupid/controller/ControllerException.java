package com.eincs.android.kupid.controller;

import com.eincs.android.kupid.KApplication;

import android.content.res.Resources;

public class ControllerException extends Exception {
	private static final long serialVersionUID = -70114761316592707L;
	
	public ControllerException(int msgResId) {
		super(getStringResource(msgResId));
	}
	
	private static String getStringResource(int msgResId) {
		Resources resources = KApplication.getInstance().getResources();
		return resources.getString(msgResId);
	}
}
