package com.eincs.android.kupid.controller;

import java.util.Random;
import java.util.concurrent.Callable;

import android.util.Base64;

import com.eincs.android.kupid.KApplication;
import com.eincs.android.kupid.R;
import com.eincs.android.kupid.database.Repository;
import com.eincs.android.kupid.model.KCredentialModel;
import com.eincs.android.kupid.utils.DefaultThreadPoolExecutor;
import com.eincs.android.kupid.utils.FakeDelay;
import com.google.common.base.Strings;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class DummyController implements Controller {

	private final ListeningExecutorService mExecutor;
	private final Repository mRepository;
	
	public DummyController() {
		mExecutor = MoreExecutors.listeningDecorator(new DefaultThreadPoolExecutor());
		mRepository = KApplication.getRepositoy();
	}
	
	@Override
	public ListenableFuture<KCredentialModel> startLogin(final String id, final String password) {
		return mExecutor.submit(new Callable<KCredentialModel>() {
			@Override
			public KCredentialModel call() throws Exception {
				FakeDelay.delayNetwork();
				if (Strings.isNullOrEmpty(id)) {
					throw new ControllerException(R.string.exception_msg_no_such_user);
				}
				if (Strings.isNullOrEmpty(password)) {
					throw new ControllerException(R.string.exception_msg_invalid_password);
				}
				KCredentialModel credentialModel = new KCredentialModel();
				byte[] accessTokenBytes = new byte[2048];
				new Random().nextBytes(accessTokenBytes);
				credentialModel.setAccessToken(Base64.encodeToString(accessTokenBytes, Base64.URL_SAFE));
				mRepository.setCredential(credentialModel);
				return mRepository.getCredential().get();
			}
		});
	}
	
	@Override
	public ListenableFuture<Void> startLogout() {
		return mExecutor.submit(new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				FakeDelay.delayNetwork();
				mRepository.setCredential(null);
				return null;
			}
		});
	}
}
