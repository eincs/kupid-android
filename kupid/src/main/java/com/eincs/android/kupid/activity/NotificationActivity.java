package com.eincs.android.kupid.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import butterknife.Views;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.SearchView;
import com.actionbarsherlock.widget.SearchView.OnQueryTextListener;
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

import eu.inmite.android.lib.dialogs.ISimpleDialogListener;
import eu.inmite.android.lib.dialogs.SimpleDialogFragment.SimpleDialogBuilder;

public class NotificationActivity extends SherlockFragmentActivity implements OnRefreshListener<ListView>, ISimpleDialogListener {
    public static final String EXTRA_TITLE = "NotificationActivity.EXTRA_TITLE";
    public static final String EXTRA_CATEGORY_ID = "NotificationActivity.EXTRA_CATEGORY_ID";

    public static final int RC_REAL_ALL = 0;

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
        mListView = (PullToRefreshListView) Views.findById(this, android.R.id.list);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(mAdapter);
        mListView.setOnRefreshListener(this);
        mListView.getRefreshableView().addHeaderView(inflateHeaderView());
    }

    private View inflateHeaderView() {
        View headerView = LayoutInflater.from(this).inflate(R.layout.item_notification_header, null);
        return headerView;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.notification, menu);

        // 알림 검색 관련 메뉴 세팅
        String queryHint = getString(R.string.action_search_hint);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint(queryHint);
        searchView.setOnQueryTextListener(mQueryTextListener);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (null != searchManager) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        searchView.setIconifiedByDefault(true);
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
            break;
        }
        case R.id.action_lecture_info: {
            showLectureInfo();
            break;
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
        SimpleDialogBuilder builder = Dialogs.createPrompt(this, RC_REAL_ALL);
        builder.setMessage(R.string.dialog_messagae_read_all_message);
        builder.setTitle(R.string.dialog_messagae_read_all_title);
        builder.show();
    }

    @Override
    public void onPositiveButtonClicked(int requestCode) {
        switch (requestCode) {
        case RC_REAL_ALL:
            mRepository.readAllNotification(mCategoryId);
            String message = getString(R.string.readall_message, mTitle);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            finish();
            break;
        default:
            break;
        }
    }

    @Override
    public void onNegativeButtonClicked(int requestCode) {

    }

    private void showLectureInfo() {
        Uri uri = Uri.parse("http://m.korea.ac.kr/eku/subject/schedule_st01.jsp?year=2013&term=2R&term_nm=2%ED%95%99%EA%B8%B0&lec_no=2013_2R_CNCE406_00_4728&lec_nm=%EC%9D%B8%EA%B0%84%EC%BB%B4%ED%93%A8%ED%84%B0%EC%83%81%ED%98%B8%EC%9E%91%EC%9A%A9%EC%9E%85%EB%AC%B8%28%EC%98%81%EA%B0%95%29&dept_cd=4728&code=&yearTerm=#");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private OnQueryTextListener mQueryTextListener = new OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

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
            position = position - 1;
            if (position == 0) {
                // 헤더뷰를 클릭한 경우는 무시한다.
                return;
            }
            KNotificationModel notificationModel = getItem(position - 1);
            Intent intent = new Intent(NotificationActivity.this, ContentActivity.class);
            intent.putExtra(ContentActivity.EXTRA_TITLE, mTitle);
            intent.putExtra(ContentActivity.EXTRA_NOTIFICATION_ID, notificationModel.getId());
            startActivity(intent);
        }
    }
}
