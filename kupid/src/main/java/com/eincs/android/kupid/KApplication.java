package com.eincs.android.kupid;

import android.app.Application;

import com.eincs.android.kupid.database.DummyRepository;
import com.eincs.android.kupid.database.Repository;
import com.google.common.base.Preconditions;

/**
 * Application implementation of the application. 
 */
public class KApplication extends Application {

	private static KApplication INSTANCE;
	private static Repository REPOSITORY;
	
	public static KApplication getInstance() {
		Preconditions.checkState(INSTANCE!=null, "KApplication is not created yet.");
		return INSTANCE;
	}
	
	public static Repository getRepositoy() {
		return REPOSITORY;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		INSTANCE = this;
		REPOSITORY = new DummyRepository(); 
	}
	
}
