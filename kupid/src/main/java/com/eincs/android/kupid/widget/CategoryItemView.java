package com.eincs.android.kupid.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eincs.android.kupid.R;
import com.eincs.android.kupid.model.KCategoryModel;

public class CategoryItemView extends RelativeLayout{
	private TextView mText1;
	private TextView mText2;
	
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
		mText1 = (TextView) findViewById(R.id.item_category_text1);
		mText2 = (TextView) findViewById(R.id.item_category_text2);
	}

	/**
	 * 주어진 {@link KCategoryModel}의 데이터를 바탕으로 뷰의 내용을 채워넣는다.
	 * @param categoryModel 뷰의 데이터를 가지고 있는 {@link KCategoryModel} 인스턴스
	 */
	public void setContent(KCategoryModel categoryModel) {
		mText1.setText(categoryModel.getTitle());
		mText2.setText(categoryModel.getDescription());
	}
	
	public TextView getmText1() {
		return mText1;
	}

	public TextView getmText2() {
		return mText2;
	}
	
}
