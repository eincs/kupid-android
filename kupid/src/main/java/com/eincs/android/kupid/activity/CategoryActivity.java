package com.eincs.android.kupid.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.eincs.android.kupid.KApplication;
import com.eincs.android.kupid.R;
import com.eincs.android.kupid.database.Repository;
import com.eincs.android.kupid.model.KCategoryModel;
import com.eincs.android.kupid.widget.AbsArrayAdapter;
import com.eincs.android.kupid.widget.CategoryItemView;
import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;

public class CategoryActivity extends SherlockListActivity implements OnItemClickListener {

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
		mListView = getListView();
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		mAdapter.addAllAsync(mRepository.getCategories());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.category, menu);
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// 이곳에서 SettingActivity를 호출하세요.
		// Intent intent = new Intent(this, SettingActivity.class);
		// startActivity(intent);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent(this, NotificationActivity.class);
		startActivity(intent);
	}

	private class CategoryAdapter extends AbsArrayAdapter<KCategoryModel> {

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
	}
}
