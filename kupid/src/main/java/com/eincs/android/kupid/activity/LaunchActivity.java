package com.eincs.android.kupid.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.eincs.android.kupid.R;
import com.eincs.android.kupid.utils.FakeDelay;

public class LaunchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		moveNext(getIntent());
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		moveNext(getIntent());
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_CANCELED) {
			finish();
			return;
		}
		// 만약 로그인에 성공한 경우, 카테고리 화면으로 이동한다.
		moveCategory(null);
	}
	
	/**
	 * 현재의 앱 상태를 보고 다음 화면으로 이동한다.
	 * 만약 로그인이 안되어 있는 상태면, 무조건 로그인 화면으로 보낸다.
	 * 로그인이 되어 있다면, 넘어온 Intent를 가지고 적절하 다음 화면으로 이동시킨다.
	 * Intent는 보통 시스템 노티타고 들어오는 경우 달려서 들어온다.
	 * 적절한 카테고리 화면으로 보내던지 하면 된다.
	 */
	private void moveNext(Intent intent) {
		FakeDelay.delay();
		Intent nextIntent = new Intent(this, TutorialActivity.class);
		startActivityForResult(nextIntent, 0);
	}
	
	/**
	 * 카테고리 화면을 이이동한다.
	 * @param intent
	 */
	private void moveCategory(Intent intent) {
		Intent nextIntent = new Intent(this, CategoryActivity.class);
		startActivity(nextIntent);
		finish();
	}
}
