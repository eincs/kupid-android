package com.eincs.android.kupid.database;

import java.util.List;
import java.util.concurrent.Callable;

import com.eincs.android.kupid.model.KCategoryModel;
import com.eincs.android.kupid.model.KCredentialModel;
import com.eincs.android.kupid.model.KNotificationContentModel;
import com.eincs.android.kupid.model.KNotificationModel;
import com.eincs.android.kupid.model.KTutorialModel;
import com.eincs.android.kupid.utils.DefaultThreadPoolExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class DummyRepository implements Repository{
	
	private final ListeningExecutorService mExecutor;
			
	public DummyRepository() {
		mExecutor = MoreExecutors.listeningDecorator(new DefaultThreadPoolExecutor());
	}

	@Override
	public ListenableFuture<KCredentialModel> getCredential() {
		return mExecutor.submit(new Callable<KCredentialModel>() {
			@Override
			public KCredentialModel call() throws Exception {
				return null;
			}
		});
	}

	@Override
	public ListenableFuture<Void> setCredential(KCredentialModel credentialModel) {
		return mExecutor.submit(new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				return null;
			}
		});
	}

	@Override
	public ListenableFuture<List<KTutorialModel>> getTutorials() {
		return mExecutor.submit(new Callable<List<KTutorialModel>>() {
			@Override
			public List<KTutorialModel> call() throws Exception {
				return null;
			}
		});
	}

	@Override
	public ListenableFuture<List<KCategoryModel>> getCategories() {
		return mExecutor.submit(new Callable<List<KCategoryModel>>() {
			@Override
			public List<KCategoryModel> call() throws Exception {
				return DummyModels.CATEGORIES;
			}
		});
	}

	@Override
	public ListenableFuture<List<KNotificationModel>> getNotifications(String categoryId) {
		return mExecutor.submit(new Callable<List<KNotificationModel>>() {
			@Override
			public List<KNotificationModel> call() throws Exception {
				return DummyModels.NOTIFICATIONS;
			}
		});
	}

	@Override
	public ListenableFuture<KNotificationContentModel> getNotificationContent(String notificationId) {
		return mExecutor.submit(new Callable<KNotificationContentModel>() {
			@Override
			public KNotificationContentModel call() throws Exception {
				return null;
			}
		});
	}
}
