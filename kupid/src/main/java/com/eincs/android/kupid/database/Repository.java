package com.eincs.android.kupid.database;

import java.util.List;

import com.eincs.android.kupid.model.KCategoryModel;
import com.eincs.android.kupid.model.KCredentialModel;
import com.eincs.android.kupid.model.KNotificationContentModel;
import com.eincs.android.kupid.model.KNotificationModel;
import com.eincs.android.kupid.model.KTutorialModel;
import com.google.common.util.concurrent.ListenableFuture;

/**
 * 데이터 베이스로의 접근을 정의하는 인터페이스.
 * 본 어플리케이션에서 필요한 여러가지 인터페이스를 정의한다. 
 */
public interface Repository {

	/**
	 * 유저의 로그인 정보를 데이터베이스에서 가져온다.
	 * 만약 로그인이 되어 있지 않다면, null을 리턴한다.
	 * @return 유저의 로그인 정보
	 */
	public ListenableFuture<KCredentialModel> getCredential();
	
	/**
	 * 유저가 로그인 한 경우, 로그인 정보를 데이터베이스에 저장한다.
	 * @param credentialModel 유저의 로그인 정보
	 */
	public ListenableFuture<Void> setCredential(KCredentialModel credentialModel);
	
	/**
	 * 튜토리얼 리스트를 데이터베이스에서 가져온다.
	 * @return 튜토리얼에 대한 리스트
	 */
	public ListenableFuture<List<KTutorialModel>> getTutorials();
	
	
	/**
	 * 유저의 카테고리 리스트를 데이터베이스에서 가져온다.
	 * @return 카테고리 리스트
	 */
	public ListenableFuture<List<KCategoryModel>> getCategories();
	
	/**
	 * 특정 카테고리의 알림 리스트를 데이터베이스에서 가져온다.
	 * @param categoryId 카테고리 아이디
	 * @return 알림에 대한 리스트
	 */
	public ListenableFuture<List<KNotificationModel>> getNotifications(String categoryId);

	public ListenableFuture<Void> readAllNotification(String categoryId);
	
	public ListenableFuture<Void> readNotification(String notificationId);
	
	/**
	 * 특정 알림에 대한 내용을 데이터베이스에서 가져온다.
	 * @param notificationId 알림에 대한 아이디
	 * @return 알림에 대한 내용
	 */
	public ListenableFuture<KNotificationContentModel> getNotificationContent(String notificationId);
}
