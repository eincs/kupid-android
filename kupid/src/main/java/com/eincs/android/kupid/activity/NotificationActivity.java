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
import com.eincs.android.kupid.R;
import com.eincs.android.kupid.utils.FakeDelay;
import com.eincs.android.kupid.widget.AbsArrayAdapter;
import com.eincs.android.kupid.widget.NotificationItemView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class NotificationActivity extends SherlockActivity implements
		OnItemClickListener, OnRefreshListener<ListView> {

	private final String[] VALUES = new String[] {
			"Android Example ListActivity", "Adapter implementation",
			"Simple List View With ListActivity", "ListActivity Android",
			"Android Example", "ListActivity Source Code",
			"ListView ListActivity Array Adapter",
			"Android Example ListActivity", "Adapter implementation",
			"Simple List View With ListActivity", "ListActivity Android",
			"Android Example", "ListActivity Source Code",
			"ListView ListActivity Array Adapter",
			"Android Example ListActivity", "Adapter implementation",
			"Simple List View With ListActivity", "ListActivity Android",
			"Android Example", "ListActivity Source Code",
			"ListView ListActivity Array Adapter",
			"Android Example ListActivity" };

	private PullToRefreshListView mListView;
	private NotificationAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);
		mAdapter = new NotificationAdapter(this);
		mListView = (PullToRefreshListView) Views.findById(this,
				android.R.id.list);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);
		mListView.setOnRefreshListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(this, ContentActivity.class);
		startActivity(intent);
	}

	private class NotificationAdapter extends AbsArrayAdapter<String> {

		public NotificationAdapter(Context context) {
			super(context, R.layout.item_notification);
			addAll(VALUES);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			NotificationItemView itemView = (NotificationItemView) getOrCreateView(convertView, parent);
			return itemView;
		}
	}
}
