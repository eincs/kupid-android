package com.eincs.android.kupid.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eincs.android.kupid.R;

public class NotificationItemView extends LinearLayout {
	private TextView mTitle;
	private TextView mTime;
	private TextView mContent1;
	private TextView mContent2;
	private ImageView mStar;
	private TextView mTag1;
	private TextView mTag2;
	private TextView mTag3;
	
	public NotificationItemView(Context context) {
		this(context, null);
	}
	
	public NotificationItemView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public NotificationItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		mTitle = (TextView) findViewById(R.id.item_noti_title);
		mTime = (TextView) findViewById(R.id.item_noti_time);
		mContent1 = (TextView) findViewById(R.id.item_noti_content1);
		mContent2 = (TextView) findViewById(R.id.item_noti_content2);
		mStar = (ImageView) findViewById(R.id.item_noti_star);
		mTag1 = (TextView) findViewById(R.id.item_noti_tag1);
		mTag2 = (TextView) findViewById(R.id.item_noti_tag2);
		mTag3 = (TextView) findViewById(R.id.item_noti_tag3);
	}
}
