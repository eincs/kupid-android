package com.eincs.android.kupid.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Views;

import com.eincs.android.kupid.KApplication;
import com.eincs.android.kupid.R;
import com.eincs.android.kupid.controller.Controller;
import com.eincs.android.kupid.controller.ControllerCallback;
import com.eincs.android.kupid.controller.ControllerFutures;
import com.eincs.android.kupid.model.KCredentialModel;
import com.eincs.android.kupid.widget.Dialogs;
import com.google.common.util.concurrent.ListenableFuture;

public class LoginActivity extends FragmentActivity implements OnClickListener {

	private Controller mController;
	private EditText mEditId;
	private EditText mEditPassword;
	private Button mLogin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mController = KApplication.getController();
		mEditId = Views.findById(this, R.id.login_edit_id);
		mEditPassword = Views.findById(this, R.id.login_edit_password);
		mLogin = Views.findById(this, R.id.login_btn_login);
		mLogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		String id = mEditId.getText().toString();
		String password = mEditPassword.getText().toString();
		ProgressDialog dialog = Dialogs.showProgressDialog(this);
		ListenableFuture<KCredentialModel> future = mController.startLogin(id, password);
		ControllerFutures.dismissDialogOnComplete(future, dialog);
		ControllerFutures.addCallback(future, new ControllerCallback<KCredentialModel>() {
			@Override
			public void onSuccess(KCredentialModel result) {
				setResult(Activity.RESULT_OK);
				finish();
			}
		});
	}

}
