package com.eincs.android.kupid.model;

import com.google.common.base.Predicate;

public final class ModelPredicates {
	private ModelPredicates() {}
	
	public static Predicate<KCategoryModel> categoryById(final String categoryId) {
		return new Predicate<KCategoryModel>() {
			@Override
			public boolean apply(KCategoryModel input) {
				return input.getId().equals(categoryId);
			}
		};
	}
	
	public static Predicate<KNotificationModel> notificationById(final String notificationId) {
		return new Predicate<KNotificationModel>() {
			@Override
			public boolean apply(KNotificationModel input) {
				return input.getId().equals(notificationId);
			}
		};
	}
	
	public static Predicate<KNotificationModel> unread(final String categoryId) {
		return new Predicate<KNotificationModel>() {
			@Override
			public boolean apply(KNotificationModel input) {
				return input.getCategoryId().equals(categoryId) && !input.isRead();
			}
		};
	}
}
