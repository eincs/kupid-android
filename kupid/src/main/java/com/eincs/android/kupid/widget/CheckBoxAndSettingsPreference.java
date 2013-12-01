package com.eincs.android.kupid.widget;

import com.eincs.android.kupid.R;
import com.eincs.android.kupid.activity.SettingDetailActivity;
import com.eincs.android.kupid.activity.SettingsPreferenceFragment;

import android.content.Context;
import android.content.Intent;
import android.preference.CheckBoxPreference;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class CheckBoxAndSettingsPreference extends CheckBoxPreference {
    private static final float DISABLED_ALPHA = 0.4f;

    private SettingsPreferenceFragment mFragment;
    private TextView mTitleText;
    private TextView mSummaryText;
    private ImageView mSettingsButton;
    private Intent mSettingsIntent;

    public CheckBoxAndSettingsPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayoutResource(R.layout.preference_inputmethod);
        setWidgetLayoutResource(R.layout.preference_inputmethod_widget);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        View textLayout = view.findViewById(R.id.inputmethod_pref);
        textLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onCheckBoxClicked();
            }
        });
        mSettingsButton = (ImageView) view.findViewById(R.id.inputmethod_settings);
        mTitleText = (TextView)view.findViewById(android.R.id.title);
        mSummaryText = (TextView)view.findViewById(android.R.id.summary);
        mSettingsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onSettingsButtonClicked();
                Intent intent = new Intent(getContext(), SettingDetailActivity.class);
                getContext().startActivity(intent);
            }
        });
        mSettingsButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        enableSettingsButton();
    }

    public void setFragmentIntent(SettingsPreferenceFragment fragment, Intent intent) {
        mFragment = fragment;
        mSettingsIntent = intent;
    }

    protected void onCheckBoxClicked() {
        if (isChecked()) {
            setChecked(false);
        } else {
            setChecked(true);
        }
    }

    protected void onSettingsButtonClicked() {
        if (mFragment != null && mSettingsIntent != null) {
            mFragment.startActivity(mSettingsIntent);
        }
    }

    private void enableSettingsButton() {
        if (mSettingsButton != null) {
            if (mSettingsIntent == null) {
                mSettingsButton.setVisibility(View.GONE);
            } else {
                final boolean checked = isChecked();
                mSettingsButton.setEnabled(checked);
                mSettingsButton.setClickable(checked);
                mSettingsButton.setFocusable(checked);
                if (!checked) {
                    mSettingsButton.setAlpha(DISABLED_ALPHA);
                }
            }
        }
        if (mTitleText != null) {
            mTitleText.setEnabled(true);
        }
        if (mSummaryText != null) {
            mSummaryText.setEnabled(true);
        }
    }
}