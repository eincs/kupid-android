package com.eincs.android.kupid.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import butterknife.Views;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.eincs.android.kupid.KApplication;
import com.eincs.android.kupid.R;
import com.eincs.android.kupid.database.Repository;
import com.eincs.android.kupid.event.KEventBus;
import com.eincs.android.kupid.event.KModelChangedEvent;
import com.eincs.android.kupid.model.KNotificationModel;
import com.eincs.android.kupid.utils.Extras;
import com.eincs.android.kupid.utils.FakeDelay;
import com.eincs.android.kupid.widget.AbsArrayAdapter;
import com.eincs.android.kupid.widget.Dialogs;
import com.eincs.android.kupid.widget.NotificationItemView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class NotificationActivity extends SherlockActivity implements OnRefreshListener<ListView> {
	public static final String EXTRA_TITLE = "NotificationActivity.EXTRA_TITLE";
	public static final String EXTRA_CATEGORY_ID = "NotificationActivity.EXTRA_CATEGORY_ID";
	
	private String mTitle;
	private String mCategoryId;
	private Repository mRepository;
	private PullToRefreshListView mListView;
	private NotificationAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);
		mTitle = Extras.getString(this, EXTRA_TITLE);
		getSupportActionBar().setTitle(mTitle);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		mCategoryId = Extras.getString(this, EXTRA_CATEGORY_ID);
		mRepository = KApplication.getRepositoy();
		mAdapter = new NotificationAdapter(this);
		mAdapter.addAllAsync(mRepository.getNotifications(mCategoryId));
		mListView = (PullToRefreshListView) Views.findById(this,android.R.id.list);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(mAdapter);
		mListView.setOnRefreshListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.notification, menu);
		return true;
	}

	 @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			break;
		case R.id.action_real_all: {
			promptReadAll();
		}
		}
		return true;
	}
	 
	@Override
	protected void onResume() {
		super.onResume();
		KEventBus.getDefaultEventBus().registerSticky(this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		KEventBus.getDefaultEventBus().unregister(this);
	}
	
	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		FakeDelay.executeWithDelay(new Runnable() {
			@Override
			public void run() {
				mListView.onRefreshComplete();
			}
		}, 1000);
	}
	
	public void onEventMainThread(KModelChangedEvent event) {
		mAdapter.addAllAsync(mRepository.getNotifications(mCategoryId));
	}
	
	public void promptReadAll() {
		Dialogs.showAlertDialog(this, R.string.dialog_messagae_read_all, new Runnable() {
			@Override
			public void run() {
				mRepository.readAllNotification(mCategoryId);
				finish();
			}
		});
	}
	
	private class NotificationAdapter extends AbsArrayAdapter<KNotificationModel> implements OnItemClickListener {

		public NotificationAdapter(Context context) {
			super(context, R.layout.item_notification);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			KNotificationModel notificationModel = getItem(position);
			NotificationItemView itemView = (NotificationItemView) getOrCreateView(convertView, parent);
			itemView.setContent(notificationModel);
			return itemView;
		}
		
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			KNotificationModel notificationModel = getItem(position - 1);
			Intent intent = new Intent(NotificationActivity.this, ContentActivity.class);
			intent.putExtra(ContentActivity.EXTRA_TITLE, mTitle);
			intent.putExtra(ContentActivity.EXTRA_NOTIFICATION_ID, notificationModel.getId());
			startActivity(intent);
		}
	}
}
