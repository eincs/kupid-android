package com.eincs.android.kupid.database;

import java.util.List;

import com.eincs.android.kupid.model.KCategoryModel;
import com.google.common.collect.Lists;

public final class DummyModels {
	private DummyModels() {}
	
	public static final List<KCategoryModel> CATEGORIES;
	static {
		CATEGORIES = Lists.newArrayList();
		CATEGORIES.add(create("1", "2"));
		CATEGORIES.add(create("1", "2"));
		CATEGORIES.add(create("1", "2"));
		CATEGORIES.add(create("1", "2"));
		CATEGORIES.add(create("1", "2"));
		CATEGORIES.add(create("1", "2"));
		CATEGORIES.add(create("1", "2"));
		CATEGORIES.add(create("1", "2"));
		CATEGORIES.add(create("1", "2"));
		CATEGORIES.add(create("1", "2"));
		CATEGORIES.add(create("1", "2"));
		CATEGORIES.add(create("1", "2"));
	}
	
	private static KCategoryModel create(String title, String description) {
		KCategoryModel result = new KCategoryModel();
		result.setTitle(title);
		result.setDescription(description);
		return result;
	}

}
