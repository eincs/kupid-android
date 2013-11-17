package com.eincs.android.kupid.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import butterknife.Views;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.eincs.android.kupid.R;
import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;

public class CategoryActivity extends SherlockActivity implements OnItemClickListener {

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
		mListView = Views.findById(this, android.R.id.list);
		mListView.setAdapter(new CategoryAdapter(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

	}

	private class CategoryAdapter extends ArrayAdapter<String> {

		public CategoryAdapter(Context context) {
			super(context, android.R.layout.simple_list_item_1);
			addAll(VALUES);
		}

	}
}
