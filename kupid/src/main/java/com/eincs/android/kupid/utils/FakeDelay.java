package com.eincs.android.kupid.utils;

import java.util.concurrent.TimeUnit;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.google.common.base.Strings;
import com.google.common.util.concurrent.Uninterruptibles;

/**
 * 실제 어플리케이션을 구동하는 것처럼 하기 위해 네트워크나 디비접근등에 일부러 딜레이를 주는 유틸리티 클래스.
 */
public final class FakeDelay {
    public static String TAG = "FakeDelay";
    public static long DEFAULT_DELAY_DURATION = 500;

    public FakeDelay() {
    }

    public static void delay() {
        delay(DEFAULT_DELAY_DURATION);
    }

    public static void delayNetwork() {
        delay(DEFAULT_DELAY_DURATION);
    }

    public static void delayDatabase() {
        delay(DEFAULT_DELAY_DURATION);
    }

    public static void delay(long durationMillis) {
        delay(durationMillis, TimeUnit.MILLISECONDS);
    }

    public static void delay(long duration, TimeUnit timeunit) {
        Uninterruptibles.sleepUninterruptibly(duration, timeunit);
    }

    /**
     * 실제 어플리케이션을 작성했을때 딜레이를 생각하여, 딜레이를 주고 {@link Runnable}을 실행시켜준다.
     */
    public static void executeWithDelay(final Runnable runnable) {
        executeWithDelay(null, runnable);
    }

    public static void executeWithDelay(final Runnable runnable, final long durationMillis) {
        executeWithDelay(null, runnable, durationMillis);
    }

    public static void executeWithDelay(final ProgressDialog dialog, final Runnable runnable) {
        executeWithDelay(null, runnable, DEFAULT_DELAY_DURATION);
    }

    public static void executeWithDelay(final ProgressDialog dialog, final Runnable runnable, final long durationMillis) {
        if (dialog != null) {
            dialog.show();
        }
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                delay(durationMillis);
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                try {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    runnable.run();
                } catch (Exception e) {
                    Log.e(TAG, Strings.nullToEmpty(e.getMessage()), e);
                }
            }
        }.execute();
    }
}
