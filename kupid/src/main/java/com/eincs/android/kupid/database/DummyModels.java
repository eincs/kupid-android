package com.eincs.android.kupid.database;

import java.util.List;

import android.content.Context;
import android.content.res.Resources;

import com.eincs.android.kupid.KApplication;
import com.eincs.android.kupid.R;
import com.eincs.android.kupid.model.KCategoryModel;
import com.eincs.android.kupid.model.KNotificationModel;
import com.eincs.android.kupid.model.KTutorialModel;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

public final class DummyModels {
	private DummyModels() {}
	private static final Context CONTEXT = KApplication.getInstance();
	private static final Resources RESOURCE = CONTEXT.getResources();
	
	public static final CredentialDatabase CREDENTIAL;
	static {
		CREDENTIAL = new CredentialDatabase(CONTEXT);
	}
	
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
		String headerCommon = RESOURCE.getString(R.string.category_header_common);
		String headerSubject = RESOURCE.getString(R.string.category_header_subject);
		CATEGORIES = Lists.newArrayList();
		CATEGORIES.add(newCategory(0, R.string.setting_notice, R.string.setting_notice_desc, 2).setHeader(headerCommon));
		CATEGORIES.add(newCategory(1, R.string.setting_schedule, R.string.setting_schedule_desc, 0));
		CATEGORIES.add(newCategory(3, R.string.subject_hci, R.string.subject_hci_desc, 1).setHeader(headerSubject));
		CATEGORIES.add(newCategory(4, R.string.subject_face_action, R.string.subject_face_action_desc, 0));
		CATEGORIES.add(newCategory(5, R.string.subject_probability, R.string.subject_probability_desc, 0));
		CATEGORIES.add(newCategory(6, R.string.subject_history, R.string.subject_history_desc, 0));
		CATEGORIES.add(newCategory(7, R.string.subject_algorithm, R.string.subject_algorithm_desc, 0));
		CATEGORIES.add(newCategory(8, R.string.subject_ip, R.string.subject_ip_desc, 123));
	}
	
	public static KCategoryModel newCategory(int id, int titleResId, int descriptionResId, int unradCount) {
		String title = RESOURCE.getString(titleResId);
		String description = RESOURCE.getString(descriptionResId);
		KCategoryModel result = new KCategoryModel();
		result.setId(Integer.toString(id));
		result.setTitle(title);
		result.setDescription(description);
		result.setUnreadCount(unradCount);
		return result;
	}
	
	public static final Multimap<String, KNotificationModel> NOTIFICATIONS;
	static {
		NOTIFICATIONS = LinkedListMultimap.create();
		for (KCategoryModel categoryModel : CATEGORIES) {
			int unreadCount = categoryModel.getUnreadCount();
			String categoryId = categoryModel.getId();
			for (int i = 0; i < 200; i++) {
				boolean read = i < unreadCount ? true : false;
				NOTIFICATIONS.put(categoryModel.getId(), createNotification(i, categoryId, read));
			}
		}
	}
	
	public static KNotificationModel createNotification(int id, String categoryId, boolean read) {
		KNotificationModel result = new KNotificationModel();
		result.setCategoryId(categoryId);
		result.setNotificationId(String.format("%s_%s", categoryId, Integer.toString(id)));
		result.setRead(read);
		return result;
	}
}
