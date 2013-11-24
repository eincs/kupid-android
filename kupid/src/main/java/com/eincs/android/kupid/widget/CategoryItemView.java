package com.eincs.android.kupid.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eincs.android.kupid.R;
import com.eincs.android.kupid.model.KCategoryModel;
import com.google.common.base.Strings;

public class CategoryItemView extends RelativeLayout {
	private TextView mHeader;
	private TextView mText1;
	private TextView mText2;
	private TextView mBadge;
	
	public CategoryItemView(Context context) {
		this(context, null);
	}
	
	public CategoryItemView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public CategoryItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		mHeader = (TextView) findViewById(R.id.item_category_header);
		mText1 = (TextView) findViewById(R.id.item_category_text1);
		mText2 = (TextView) findViewById(R.id.item_category_text2);
		mBadge = (TextView) findViewById(R.id.item_category_badge);
	}

	/**
	 * 주어진 {@link KCategoryModel}의 데이터를 바탕으로 뷰의 내용을 채워넣는다.
	 * @param categoryModel 뷰의 데이터를 가지고 있는 {@link KCategoryModel} 인스턴스
	 */
	public void setContent(KCategoryModel categoryModel) {
		String header = categoryModel.getHeader();
		boolean hasHaeder = !Strings.isNullOrEmpty(header);
		mHeader.setVisibility(hasHaeder?View.VISIBLE:View.GONE);
		mHeader.setText(header);
		int unreadCount = categoryModel.getUnreadCount();
		boolean hasUnread = unreadCount > 0;
		mText1.setText(categoryModel.getTitle());
		mText1.setTypeface(hasUnread ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
		mText2.setText(categoryModel.getDescription());
		mText2.setTypeface(hasUnread ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
		mBadge.setVisibility(hasUnread ? View.VISIBLE : View.GONE);
		mBadge.setText(Integer.toString(unreadCount));
	}
	
	public TextView getHeader() {
		return mHeader;
	}

	public TextView getBadge() {
		return mBadge;
	}

	public TextView getText1() {
		return mText1;
	}

	public TextView getText2() {
		return mText2;
	}
}
