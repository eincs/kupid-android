package com.eincs.android.kupid.database;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import com.eincs.android.kupid.event.KEventBus;
import com.eincs.android.kupid.event.KModelChangedEvent;
import com.eincs.android.kupid.model.KCategoryModel;
import com.eincs.android.kupid.model.KCredentialModel;
import com.eincs.android.kupid.model.KNotificationContentModel;
import com.eincs.android.kupid.model.KNotificationModel;
import com.eincs.android.kupid.model.KTutorialModel;
import com.eincs.android.kupid.utils.DefaultThreadPoolExecutor;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class DummyRepository implements Repository {
	
	private final ListeningExecutorService mExecutor;
			
	public DummyRepository() {
		mExecutor = MoreExecutors.listeningDecorator(new DefaultThreadPoolExecutor());
	}

	@Override
	public ListenableFuture<KCredentialModel> getCredential() {
		return mExecutor.submit(new Callable<KCredentialModel>() {
			@Override
			public KCredentialModel call() throws Exception {
				return DummyModels.CREDENTIAL.loadCredential();
			}
		});
	}

	@Override
	public ListenableFuture<Void> setCredential(final KCredentialModel credentialModel) {
		return mExecutor.submit(new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				DummyModels.CREDENTIAL.saveCredential(credentialModel);
				return null;
			}
		});
	}

	@Override
	public ListenableFuture<List<KTutorialModel>> getTutorials() {
		return mExecutor.submit(new Callable<List<KTutorialModel>>() {
			@Override
			public List<KTutorialModel> call() throws Exception {
				return Collections.unmodifiableList(DummyModels.TUTORIALS);
			}
		});
	}

	@Override
	public ListenableFuture<List<KCategoryModel>> getCategories() {
		return mExecutor.submit(new Callable<List<KCategoryModel>>() {
			@Override
			public List<KCategoryModel> call() throws Exception {
				return Collections.unmodifiableList(DummyModels.CATEGORIES);
			}
		});
	}

	@Override
	public ListenableFuture<List<KNotificationModel>> getNotifications(final String categoryId) {
		return mExecutor.submit(new Callable<List<KNotificationModel>>() {
			@Override
			public List<KNotificationModel> call() throws Exception {
				return Lists.newArrayList(DummyModels.NOTIFICATIONS.get(categoryId));
			}
		});
	}

	@Override
	public ListenableFuture<Void> readAllNotification(final String categoryId) {
		return mExecutor.submit(new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				for (KCategoryModel category : DummyModels.CATEGORIES) {
					if (category.getId().equals(categoryId)) {
						category.setUnreadCount(0);
					}
				}
				notifyChanges(KCategoryModel.class);
				return null;
			}
		});
	}

	@Override
	public ListenableFuture<Void> readNotification(String notificationId) {
		return mExecutor.submit(new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				return null;
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
	
	private void notifyChanges(Class<?> type) {
		KEventBus.getDefaultEventBus().postSticky(new KModelChangedEvent(type));
	}
}
