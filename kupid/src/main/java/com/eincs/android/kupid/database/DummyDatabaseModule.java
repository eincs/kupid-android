package com.eincs.android.kupid.database;

import javax.inject.Singleton;

import com.eincs.android.kupid.KApplication;

import dagger.Module;
import dagger.Provides;

@Module(injects = KApplication.class)
public class DummyDatabaseModule {
	@Provides
	@Singleton
	Repository getRepository() {
		return new DummyRepository();
	}
}