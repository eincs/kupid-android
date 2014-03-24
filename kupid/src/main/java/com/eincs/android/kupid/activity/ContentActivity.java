package com.eincs.android.kupid.activity;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract.Events;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.Views;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.eincs.android.kupid.KApplication;
import com.eincs.android.kupid.R;
import com.eincs.android.kupid.controller.Controller;
import com.eincs.android.kupid.database.Repository;
import com.eincs.android.kupid.model.KNotificationContentModel;
import com.eincs.android.kupid.utils.Extras;
import com.eincs.android.kupid.utils.FakeDelay;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

public class ContentActivity extends SherlockActivity implements
        FutureCallback<KNotificationContentModel>, OnClickListener,
        OnRefreshListener<ScrollView> {
    public static final String EXTRA_TITLE = "ContentActivity.EXTRA_TITLE";
    public static final String EXTRA_NOTIFICATION_ID = "ContentActivity.EXTRA_NOTIFICATION_ID";

    private String mNotificationId;

    private Controller mController;
    private Repository mRepository;

    private PullToRefreshScrollView mScroll;
    private TextView mTitle;
    private TextView mTime;
    private TextView mTag1;
    private TextView mTag2;
    private TextView mTag3;
    private TextView mContent;

    private Button mAction1;
    private Button mAction2;
    private Button mAction3;

    private KNotificationContentModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        String activityTitle = Extras.getString(this, EXTRA_TITLE);
        getSupportActionBar().setTitle(activityTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mNotificationId = Extras.getString(this, EXTRA_NOTIFICATION_ID);
        mController = KApplication.getController();
        mRepository = KApplication.getRepositoy();
        mScroll = Views.findById(this, R.id.content_scroll);
        mScroll.setOnRefreshListener(this);
        mTitle = Views.findById(this, R.id.content_title);
        mTime = Views.findById(this, R.id.content_time);
        mTag1 = Views.findById(this, R.id.content_tag1);
        mTag1 = Views.findById(this, R.id.content_tag2);
        mTag3 = Views.findById(this, R.id.content_tag3);
        mContent = Views.findById(this, R.id.content_text);
        mAction1 = Views.findById(this, R.id.content_btn1);
        mAction1.setOnClickListener(this);
        mAction2 = Views.findById(this, R.id.content_btn2);
        mAction2.setOnClickListener(this);
        mAction3 = Views.findById(this, R.id.content_btn3);
        mAction3.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.content, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRepository.readNotification(mNotificationId);
        Futures.addCallback(mRepository.getNotificationContent(null), this);
    }

    @Override
    public void onRefresh(PullToRefreshBase<ScrollView> arg0) {
        FakeDelay.executeWithDelay(new Runnable() {
            @Override
            public void run() {
                mScroll.onRefreshComplete();
            }
        }, 1000);
    }

    @Override
    public void onSuccess(KNotificationContentModel result) {
        model = result;
    }

    @Override
    public void onFailure(Throwable t) {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.action_export_calendar:
            exportToCalendar();
            break;
        case R.id.action_export_text:
            exportAsText();
            break;
        case R.id.action_view_original:
            viewOriginal();
            break;
        case android.R.id.home:
            onBackPressed();
            break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.content_btn1:
            exportToCalendar();
            break;
        case R.id.content_btn2:
            exportAsText();
            break;
        case R.id.content_btn3:
            viewOriginal();
            break;
        }
    }

    private void exportToCalendar() {
        String title = "Grading Policy";
        String description = getResources().getString(R.string.notification_content);

        Calendar cal = Calendar.getInstance();
        cal.set(2013, GregorianCalendar.NOVEMBER, 23, 23, 59);
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", cal.getTimeInMillis() + 60 * 60 * 1000);
        intent.putExtra("endTime", cal.getTimeInMillis() + 60 * 60 * 1000);
        intent.putExtra(Events.TITLE, title);
        intent.putExtra(Events.DESCRIPTION, description);
        startActivity(intent);
    }

    private void exportAsText() {
        String shareBody = getResources().getString(R.string.notification_content);
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.action_export_text)));
    }

    private void viewOriginal() {
        Uri uri = Uri.parse("http://m.korea.ac.kr/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
