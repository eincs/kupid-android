package com.eincs.android.kupid.controller;

import com.eincs.android.kupid.model.KCredentialModel;
import com.google.common.util.concurrent.ListenableFuture;

public interface Controller {

	public ListenableFuture<KCredentialModel> startLogin(String id, String password);

	public ListenableFuture<Void> startLogout();

}
