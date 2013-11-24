package com.eincs.android.kupid.activity;

import butterknife.Views;

import com.eincs.android.kupid.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TutorialContentFragment extends Fragment {
	public static final String ARG_CONTENT = "TutorialContentFragment.ARG_CONTENT"; 
	public static final String ARG_BACKGROUND = "TutorialContentFragment.ARG_BACKGROUND";
	
	private int mArgContent;
	private int mArgBackground;
	
	private RelativeLayout mBackground;
	private TextView mTextView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mArgContent = getArguments().getInt(ARG_CONTENT);
		mArgBackground = getArguments().getInt(ARG_BACKGROUND);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_tutorial_content, null);
		mBackground = Views.findById(view, R.id.tutorial_background);
		mBackground.setBackgroundResource(mArgBackground);
		mTextView = Views.findById(view, R.id.tutorial_content);
		mTextView.setText(mArgContent);
		return view;
	}
}
