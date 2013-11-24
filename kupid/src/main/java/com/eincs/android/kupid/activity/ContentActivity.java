package com.eincs.android.kupid.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.eincs.android.kupid.R;

public class ContentActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.content, menu);
		return true;
	}
}
