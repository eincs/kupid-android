package com.eincs.android.kupid.database;

import java.util.List;

import android.content.Context;
import android.content.res.Resources;

import com.eincs.android.kupid.KApplication;
import com.eincs.android.kupid.model.KCategoryModel;
import com.eincs.android.kupid.model.KNotificationModel;
import com.google.common.collect.Lists;

public final class DummyModels {
	private DummyModels() {}
	private static final Context CONTEXT = KApplication.getInstance();
	private static final Resources RESOURCE = CONTEXT.getResources();
	
	public static final List<KCategoryModel> CATEGORIES;
	static {
		CATEGORIES = Lists.newArrayList();
		CATEGORIES.add(createCategory("공지사항", "공지사항"));
		CATEGORIES.add(createCategory("학사일정", "2"));
		CATEGORIES.add(createCategory("인간과 컴퓨터 상호작용 입문", "2"));
		CATEGORIES.add(createCategory("확률 및 통계 입문", "2"));
		CATEGORIES.add(createCategory("역사는 어떻게 서술 되는가?", "2"));
		CATEGORIES.add(createCategory("외모와 인간행동", "2"));
		CATEGORIES.add(createCategory("알고리즘", "2"));
		CATEGORIES.add(createCategory("인터넷 프로토콜", "2"));
	}
	
	public static KCategoryModel createCategory(String title, String description) {
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
