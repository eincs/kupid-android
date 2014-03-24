package com.eincs.android.kupid.network;

import java.util.List;

import com.eincs.android.kupid.model.KCategoryModel;
import com.eincs.android.kupid.model.KCredentialModel;
import com.eincs.android.kupid.model.KNotificationModel;
import com.google.common.util.concurrent.ListenableFuture;

/**
 * 서버와 통신하는 클라이언트. 본 어플리케이션에서 필요한 서버와 통신 인터페이스를 정의한다.
 */
public interface NetworkClient {

    public ListenableFuture<KCredentialResponse> login(KCredentialModel credential);

    public ListenableFuture<List<KCategoryModel>> getCategories(String accessToken);

    public ListenableFuture<List<KNotificationModel>> getNotifications(String accessToken, String category);
}
