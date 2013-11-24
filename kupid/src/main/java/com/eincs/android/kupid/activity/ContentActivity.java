package com.eincs.android.kupid.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.Views;

import com.eincs.android.kupid.KApplication;
import com.eincs.android.kupid.R;
import com.eincs.android.kupid.database.Repository;
import com.eincs.android.kupid.model.KNotificationContentModel;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;

public class ContentActivity extends Activity implements FutureCallback<KNotificationContentModel>, OnClickListener {

	private Repository mRepository;

	private TextView mTitle;
	private TextView mTime;
	private TextView mTag1;
	private TextView mTag2;
	private TextView mTag3;
	private TextView mContent;

	private ImageButton mAction1;
	private ImageButton mAction2;
	private ImageButton mAction3;

	private KNotificationContentModel model;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content);
		mRepository = KApplication.getRepositoy();
		mTitle = (TextView) Views.findById(this, R.id.content_title);
		mTime = (TextView) Views.findById(this, R.id.content_time);
		mTag1 = (TextView) Views.findById(this, R.id.content_tag1);
		mTag1 = (TextView) Views.findById(this, R.id.content_tag2);
		mTag3 = (TextView) Views.findById(this, R.id.content_tag3);
		mContent = (TextView) Views.findById(this, R.id.content_text);
		mAction1 = (ImageButton) Views.findById(this, R.id.content_btn1);
		mAction1.setOnClickListener(this);
		mAction2 = (ImageButton) Views.findById(this, R.id.content_btn2);
		mAction2.setOnClickListener(this);
		mAction3 = (ImageButton) Views.findById(this, R.id.content_btn3);
		mAction3.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.content, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		Futures.addCallback(mRepository.getNotificationContent(null), this);
	}

	@Override
	public void onSuccess(KNotificationContentModel result) {
		model = result;
	}

	@Override
	public void onFailure(Throwable t) {
		finish();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_export_calendar:
			exportToCalendar();
			break;
		case R.id.action_export_text:
			exportAsText();
			break;
		case R.id.action_view_original:
			viewOriginal();
			break;
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.content_btn1:
			exportToCalendar();
			break;
		case R.id.content_btn2:
			exportAsText();
			break;
		case R.id.content_btn3:
			viewOriginal();
			break;
		}
	}

	private void exportToCalendar() {
		Uri calendars = Uri.parse("content://com.android.calendar/time/");
		Intent intent = new Intent();
		intent.setData(calendars);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

	private void exportAsText() {
		String shareBody = "Here is the share content body";
		Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
		sharingIntent.setType("text/plain");
		sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
		startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.action_export_text)));
	}

	private void viewOriginal() {
		Uri uri = Uri.parse("http://m.korea.ac.kr/");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}
}
