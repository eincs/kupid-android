package com.eincs.android.kupid.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import butterknife.Views;

import com.eincs.android.kupid.R;
import com.eincs.android.kupid.utils.FakeDelay;

public class LoginActivity extends Activity implements OnClickListener {

	private Button mLogin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mLogin = Views.findById(this, R.id.login_btn_login);
		mLogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		ProgressDialog dialog = ProgressDialog.show(this, null, "Please wait...");
		FakeDelay.executeWithDelay(dialog, new Runnable() {
			@Override
			public void run() {
				setResult(Activity.RESULT_OK);
				finish();				
			}
		}, 1500);
	}

}
