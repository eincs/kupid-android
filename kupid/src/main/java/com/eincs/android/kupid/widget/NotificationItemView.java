package com.eincs.android.kupid.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eincs.android.kupid.KApplication;
import com.eincs.android.kupid.R;
import com.eincs.android.kupid.model.KNotificationModel;

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

	/**
	 * {@link KNotificationModel}의 데이터를 바탕으로 본 뷰의 내용을 채워넣는다.
	 * @param notificationModel 뷰의 데이터를 가지고 있는 {@link KNotificationModel} 인스턴스
	 */
	public void setContent(KNotificationModel notificationModel) {
		boolean read = notificationModel.isRead();
		Resources resources = KApplication.getInstance().getResources();
		setBackgroundColor(!read ? resources.getColor(android.R.color.white) : resources.getColor(android.R.color.transparent));
		mTitle.setTypeface(!read ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
		mContent1.setTypeface(!read ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
		mContent2.setTypeface(!read ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
	}
	
	public TextView getTitle() {
		return mTitle;
	}

	public TextView getTime() {
		return mTime;
	}

	public TextView getContent1() {
		return mContent1;
	}

	public TextView getContent2() {
		return mContent2;
	}

	public ImageView getStar() {
		return mStar;
	}

	public TextView getTag1() {
		return mTag1;
	}

	public TextView getTag2() {
		return mTag2;
	}

	public TextView getTag3() {
		return mTag3;
	}
}
