package com.eincs.android.kupid.database;

import java.util.List;

import android.content.Context;
import android.content.res.Resources;

import com.eincs.android.kupid.KApplication;
import com.eincs.android.kupid.R;
import com.eincs.android.kupid.model.KCategoryModel;
import com.eincs.android.kupid.model.KNotificationModel;
import com.eincs.android.kupid.model.KTutorialModel;
import com.google.common.collect.Lists;

public final class DummyModels {
	private DummyModels() {}
	private static final Context CONTEXT = KApplication.getInstance();
	private static final Resources RESOURCE = CONTEXT.getResources();
	
	public static final List<KTutorialModel> TUTORIALS;
	static {
		TUTORIALS = Lists.newArrayList();
		TUTORIALS.add(new KTutorialModel(R.string.tutorial_description1, R.drawable.bg_tutorial_1));
		TUTORIALS.add(new KTutorialModel(R.string.tutorial_description2, R.drawable.bg_tutorial_2));
		TUTORIALS.add(new KTutorialModel(R.string.tutorial_description3, R.drawable.bg_tutorial_3));
		TUTORIALS.add(new KTutorialModel(R.string.tutorial_description4, R.drawable.bg_tutorial_4));
		TUTORIALS.add(new KTutorialModel(R.string.tutorial_description5, R.drawable.bg_tutorial_5));
	}
	
	public static final List<KCategoryModel> CATEGORIES;
	static {
		CATEGORIES = Lists.newArrayList();
		CATEGORIES.add(createCategory(R.string.setting_notice, R.string.setting_notice));
		CATEGORIES.add(createCategory(R.string.setting_schedule, R.string.setting_schedule));
		CATEGORIES.add(createCategory(R.string.subject_face_action, R.string.subject_face_action));
		CATEGORIES.add(createCategory(R.string.subject_probability, R.string.subject_probability));
		CATEGORIES.add(createCategory(R.string.subject_history, R.string.subject_history));
		CATEGORIES.add(createCategory(R.string.subject_algorithm, R.string.subject_algorithm));
		CATEGORIES.add(createCategory(R.string.subject_ip, R.string.subject_ip));
	}
	
	public static KCategoryModel createCategory(int titleResId, int descriptionResId) {
		String title = RESOURCE.getString(titleResId);
		String description = RESOURCE.getString(descriptionResId);
		KCategoryModel result = new KCategoryModel();
		result.setTitle(title);
		result.setDescription(description);
		return result;
	}
	
	public static final List<KNotificationModel> NOTIFICATIONS;
	static {
		NOTIFICATIONS = Lists.newArrayList();
		NOTIFICATIONS.add(createNotification());
	}
	
	public static KNotificationModel createNotification() {
		KNotificationModel result = new KNotificationModel();
		return result;
	}
}
