package com.eincs.android.kupid.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.eincs.android.kupid.R;
import com.eincs.android.kupid.widget.AbsArrayAdapter;
import com.eincs.android.kupid.widget.CategoryItemView;
import com.eincs.android.kupid.widget.NotificationItemView;
import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;

public class CategoryActivity extends SherlockListActivity implements OnItemClickListener {

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
		FadingActionBarHelper helper = new FadingActionBarHelper()
				.actionBarBackground(R.drawable.ab_background)
				.headerLayout(R.layout.header)
				.contentLayout(R.layout.activity_category);
		setContentView(helper.createView(this));
		helper.initActionBar(this);
		mListView = getListView();
		mListView.setAdapter(new CategoryAdapter(this));
		mListView.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent(this, NotificationActivity.class);
		startActivity(intent);
	}

	private class CategoryAdapter extends AbsArrayAdapter<String> {

		public CategoryAdapter(Context context) {
			super(context, R.layout.item_category);
			addAll(VALUES);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			CategoryItemView itemView = (CategoryItemView) getOrCreateView(convertView, parent);
			return itemView;
		}

	}
}
