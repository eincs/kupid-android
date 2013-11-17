package com.eincs.android.kupid.activity;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.eincs.android.kupid.R;

public class CategoryActivity extends ListActivity implements OnItemClickListener{

	private final String[] VALUES = new String[] {
			"Android Example ListActivity",
			"Adapter implementation",
			"Simple List View With ListActivity",
			"ListActivity Android",
			"Android Example",
			"ListActivity Source Code",
			"ListView ListActivity Array Adapter",
			"Android Example ListActivity",
			"Adapter implementation",
			"Simple List View With ListActivity",
			"ListActivity Android",
			"Android Example",
			"ListActivity Source Code",
			"ListView ListActivity Array Adapter",
			"Android Example ListActivity",
			"Adapter implementation",
			"Simple List View With ListActivity",
			"ListActivity Android",
			"Android Example",
			"ListActivity Source Code",
			"ListView ListActivity Array Adapter",
			"Android Example ListActivity"
		};
	
	private ListView mListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		mListView = getListView();
		mListView.setAdapter(new CategoryAdapter(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.category, menu);
		return true;
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
	}

	private class CategoryAdapter extends ArrayAdapter<String> {

		public CategoryAdapter(Context context) {
			super(context, android.R.layout.simple_list_item_1);
			addAll(VALUES);
		}
		
	}
}
