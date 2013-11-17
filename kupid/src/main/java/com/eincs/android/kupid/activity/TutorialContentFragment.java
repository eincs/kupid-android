package com.eincs.android.kupid.activity;

import butterknife.Views;

import com.eincs.android.kupid.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TutorialContentFragment extends Fragment {
	public static final String ARG_CONTENT = "TutorialContentFragment.ARG_CONTENT"; 
	
	private String mContent;
	private TextView mTextView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContent = getArguments().getString(ARG_CONTENT);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_tutorial_content, null);
		mTextView = Views.findById(view, R.id.tutorial_content);
		mTextView.setText(mContent);
		return view;
	}
}
