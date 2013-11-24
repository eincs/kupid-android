package com.eincs.android.kupid;

import com.google.common.base.Preconditions;

import dagger.ObjectGraph;
import android.app.Application;

/**
 * Application implementation of the application. 
 */
public class KApplication extends Application {

	private static KApplication INSTANCE;
	private static ObjectGraph OBJECT_GRAPH;
	
	public static KApplication getInstance() {
		Preconditions.checkState(INSTANCE!=null, "KApplication is not created yet.");
		return INSTANCE;
	}
	
	public static ObjectGraph getObjectGraph() {
		Preconditions.checkState(OBJECT_GRAPH!=null, "ObjectGraph is not created yet.");
		return OBJECT_GRAPH;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		INSTANCE = this;
	}
	
}
