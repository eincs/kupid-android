package com.eincs.android.kupid.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import butterknife.Views;

import com.eincs.android.kupid.R;
import com.viewpagerindicator.CirclePageIndicator;

public class TutorialActivity extends FragmentActivity implements OnClickListener {

	private ViewPager mPager;
	private CirclePageIndicator mIndicator;
	private Button mLogin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tutorial);
		mPager = Views.findById(this, R.id.tutorial_pager);
		mPager.setAdapter(new TutorialAdapter());
		mIndicator = Views.findById(this, R.id.tutorial_indicator);
		mIndicator.setViewPager(mPager);
		mLogin = Views.findById(this, R.id.tutorial_btn_login);
		mLogin.setOnClickListener(this);
		
	}
	
	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivityForResult(intent, 0);	
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// 그냥 뒤로가기로 온 경우에는 그냥 무시하고 현재화면에 머문다.
		if (resultCode == Activity.RESULT_CANCELED) {
			return;
		}
		// 만약 로그인에 성공한 경우, 중료시켜 LaunchActivity쪽에 화면 전환을 위잏만다.
		setResult(Activity.RESULT_OK);
		finish();
	}
	
	private class TutorialAdapter extends FragmentPagerAdapter {

		public TutorialAdapter() {
			super(getSupportFragmentManager());
		}

		@Override
		public Fragment getItem(int position) {
			Bundle arguments = new Bundle();
			arguments.putString(TutorialContentFragment.ARG_CONTENT, String.valueOf(position));
			Fragment fragment = new TutorialContentFragment();
			fragment.setArguments(arguments);
			return fragment;
		}

		@Override
		public int getCount() {
			return 5;
		}
		
	}
}
