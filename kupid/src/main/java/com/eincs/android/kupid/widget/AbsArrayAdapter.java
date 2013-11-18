package com.eincs.android.kupid.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public abstract class AbsArrayAdapter<T> extends ArrayAdapter<T> {
	private LayoutInflater mInflater;
	private int mResource;
	
	public AbsArrayAdapter(Context context, int resource) {
		super(context, resource);
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mResource = resource;
	}

	@Override
	public abstract View getView(int position, View convertView, ViewGroup parent);
	
	protected View getOrCreateView(View convertView, ViewGroup parent) {
		View view = null;
		if (convertView == null) {
            view = mInflater.inflate(mResource, parent, false);
        } else {
            view = convertView;
        }
		return view;
	}
}
