package com.eincs.android.kupid.database;

import java.util.List;

import com.eincs.android.kupid.model.KCategoryModel;
import com.eincs.android.kupid.model.KCredentialModel;
import com.eincs.android.kupid.model.KNotificationContentModel;
import com.eincs.android.kupid.model.KNotificationModel;
import com.eincs.android.kupid.model.KTutorialModel;

public class DummyRepository implements Repository{

	@Override
	public KCredentialModel getCredential() {
		return null;
	}

	@Override
	public void setCredential(KCredentialModel credentialModel) {
		
	}

	@Override
	public List<KTutorialModel> getTutorials() {
		return null;
	}

	@Override
	public List<KCategoryModel> getCategories() {
		return null;
	}

	@Override
	public List<KNotificationModel> getNotifications(String categoryId) {
		return null;
	}

	@Override
	public KNotificationContentModel getNotificationContent(String notificationId) {
		return null;
	}

}
