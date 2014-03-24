package com.eincs.android.kupid.widget;

import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.eincs.android.kupid.utils.HandlerExecutor;
import com.google.common.base.Strings;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

public abstract class AbsArrayAdapter<T> extends ArrayAdapter<T> implements FutureCallback<List<T>> {
    private static final String TAG = "AbsArrayAdapter";

    private LayoutInflater mInflater;
    private int mResource;
    private HandlerExecutor mExecutor;

    public AbsArrayAdapter(Context context, int resource) {
        super(context, resource);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mResource = resource;
        mExecutor = new HandlerExecutor(new Handler(Looper.getMainLooper()));
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

    /**
     * 데이터를 비동기적으로 세팅한다. 데이터를 로딩하는 부분은 비동기적으로 일어나고, 세팅하는 부분만 UI-Thread에서 수행한다.
     */
    public void addAllAsync(ListenableFuture<List<T>> future) {
        Futures.addCallback(future, this, mExecutor);
    }

    @Override
    public void onSuccess(List<T> result) {
        setNotifyOnChange(false);
        clear();
        addAll(result);
        notifyDataSetChanged();
        setNotifyOnChange(true);
    }

    @Override
    public void onFailure(Throwable t) {
        clear();
        Log.d(TAG, Strings.nullToEmpty(t.getMessage()), t);
    }
}
