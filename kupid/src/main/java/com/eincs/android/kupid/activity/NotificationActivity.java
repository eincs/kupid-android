package com.eincs.android.kupid.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.eincs.android.kupid.R;

public class NotificationActivity extends SherlockListActivity implements OnItemClickListener {

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
	
	private ListView mListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);
		mListView = getListView();
		mListView.setAdapter(new NotificationAdapter(this));
		mListView.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
	}
	
	private class NotificationAdapter extends ArrayAdapter<String> {

		public NotificationAdapter(Context context) {
			super(context, android.R.layout.simple_list_item_1);
			addAll(VALUES);
		}

	}
}
