package com.eincs.android.kupid.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.eincs.android.kupid.KApplication;
import com.eincs.android.kupid.R;
import com.eincs.android.kupid.database.Repository;
import com.eincs.android.kupid.event.KEventBus;
import com.eincs.android.kupid.event.KModelChangedEvent;
import com.eincs.android.kupid.model.KCategoryModel;
import com.eincs.android.kupid.widget.AbsArrayAdapter;
import com.eincs.android.kupid.widget.CategoryItemView;
import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;

public class CategoryActivity extends SherlockListActivity {

	private Repository mRepository;
	private CategoryAdapter mAdapter;
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
		mRepository = KApplication.getRepositoy();
		mAdapter = new CategoryAdapter(this);
		mAdapter.addAllAsync(mRepository.getCategories());
		mListView = getListView();
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(mAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.category, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			break;
		}
		return true;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		mAdapter.addAllAsync(mRepository.getCategories());
		KEventBus.getDefaultEventBus().registerSticky(this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		KEventBus.getDefaultEventBus().unregister(this);
	}
	
	public void onEventMainThread(KModelChangedEvent event) {
		mAdapter.addAllAsync(mRepository.getCategories());
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		Intent intent = new Intent(this, SettingActivity.class);
		startActivity(intent);
		return true;
	}

	private class CategoryAdapter extends AbsArrayAdapter<KCategoryModel> implements OnItemClickListener {

		public CategoryAdapter(Context context) {
			super(context, R.layout.item_category);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			KCategoryModel categoryModel = getItem(position);
			CategoryItemView itemView = (CategoryItemView) getOrCreateView(convertView, parent);
			itemView.setContent(categoryModel);
			return itemView;
		}
		
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			KCategoryModel categoryModel = getItem(position - 1);
			Intent intent = new Intent(CategoryActivity.this, NotificationActivity.class);
			intent.putExtra(NotificationActivity.EXTRA_TITLE, categoryModel.getTitle());
			intent.putExtra(NotificationActivity.EXTRA_CATEGORY_ID, categoryModel.getId());
			startActivity(intent);
		}
	}
}
