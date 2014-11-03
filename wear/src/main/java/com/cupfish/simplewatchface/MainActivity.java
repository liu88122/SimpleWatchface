package com.cupfish.simplewatchface;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.view.WatchViewStub;

import com.github.adnansm.timelytextview.TimelyView;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends Activity {

    private static final int ANIMATE_DURATION = 500;

    private TimelyView mHourTenDigitView;
    private TimelyView mHourUnitDigitView;
    private TimelyView mMinuteTenDigitView;
    private TimelyView mMinuteUnitDigitView;
    private TimelyView mSecondTenDigitView;
    private TimelyView mSecondUnitDigitView;

    private ObjectAnimator mHourTenDigitAnimator;
    private ObjectAnimator mHourUnitDigitAnimator;
    private ObjectAnimator mMinuteTenDigitAnimator;
    private ObjectAnimator mMinuteUnitDigitAnimator;
    private ObjectAnimator mSecondTenDigitAnimator;
    private ObjectAnimator mSecondUnitDigitAnimator;

    private int mLastHourTenDigit = -1;
    private int mLastHourUnitDigit = -1;
    private int mLastMinuteTenDigit = -1;
    private int mLastMinuteUnitDigit = -1;
    private int mLastSecondTenDigit = -1;
    private int mLastSecondUnitDigit = -1;

    private Handler mHandler = new Handler();
    private RefreshTimeRunnable mRefreshTimeRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mHourTenDigitView = (TimelyView) stub.findViewById(R.id.hour_ten_digit);
                mHourUnitDigitView = (TimelyView) stub.findViewById(R.id.hour_unit_digit);
                mMinuteTenDigitView = (TimelyView) stub.findViewById(R.id.minute_ten_digit);
                mMinuteUnitDigitView = (TimelyView) stub.findViewById(R.id.minute_unit_digit);
                mSecondTenDigitView = (TimelyView) stub.findViewById(R.id.second_ten_digit);
                mSecondUnitDigitView = (TimelyView) stub.findViewById(R.id.second_unit_digit);

                if (mRefreshTimeRunnable != null) {
                    mRefreshTimeRunnable.stopRefresh();
                }
                mRefreshTimeRunnable = new RefreshTimeRunnable();
                runOnUiThread(mRefreshTimeRunnable);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mRefreshTimeRunnable != null) {
            mRefreshTimeRunnable.stopRefresh();
        }
        mRefreshTimeRunnable = new RefreshTimeRunnable();
        runOnUiThread(mRefreshTimeRunnable);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mRefreshTimeRunnable != null) {
            mRefreshTimeRunnable.stopRefresh();
            mRefreshTimeRunnable = null;
        }
    }

    private class RefreshTimeRunnable implements Runnable{

        private boolean stopRefresh;

        public void stopRefresh(){
            stopRefresh = true;
        }

        @Override
        public void run() {
            if (!stopRefresh) {
                refreshTime();
                mHandler.postDelayed(this, 1000);
            }
        }
    }

    private void refreshTime() {
        Calendar calendar = GregorianCalendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);
        int currentSecond = calendar.get(Calendar.SECOND);

        int currentHourTenDigit = currentHour / 10;
        int currentHourUnitDigit = currentHour % 10;
        if (mLastHourTenDigit != currentHourTenDigit) {
            if (mHourTenDigitView != null) {
                if (mLastHourTenDigit < 0) {
                    mHourTenDigitAnimator = mHourTenDigitView.animate(currentHourTenDigit);
                } else {
                    mHourTenDigitAnimator = mHourTenDigitView.animate(mLastHourTenDigit, currentHourTenDigit);
                }
                mHourTenDigitAnimator.setDuration(ANIMATE_DURATION);
                mHourTenDigitAnimator.start();
                mLastHourTenDigit = currentHourTenDigit;
            }
        }
        if (mLastHourUnitDigit != currentHourUnitDigit) {
            if (mHourUnitDigitView != null) {
                if (mLastHourUnitDigit < 0) {
                    mHourUnitDigitAnimator = mHourUnitDigitView.animate(currentHourUnitDigit);
                }else {
                    mHourUnitDigitAnimator = mHourUnitDigitView.animate(mLastHourUnitDigit, currentHourUnitDigit);
                }
                mHourUnitDigitAnimator.setDuration(ANIMATE_DURATION);
                mHourUnitDigitAnimator.start();
                mLastHourUnitDigit = currentHourUnitDigit;
            }
        }

        int currentMinuteTenDigit = currentMinute / 10;
        int currentMinuteUnitDigit = currentMinute % 10;
        if (mLastMinuteTenDigit != currentMinuteTenDigit) {
            if (mMinuteTenDigitView != null) {
                if (mLastMinuteTenDigit < 0) {
                    mMinuteTenDigitAnimator = mMinuteTenDigitView.animate(currentMinuteTenDigit);
                }else {
                    mMinuteTenDigitAnimator = mMinuteTenDigitView.animate(mLastMinuteTenDigit, currentMinuteTenDigit);
                }
                mMinuteTenDigitAnimator.setDuration(ANIMATE_DURATION);
                mMinuteTenDigitAnimator.start();
                mLastMinuteTenDigit = currentMinuteTenDigit;
            }
        }
        if (mLastMinuteUnitDigit != currentMinuteUnitDigit) {
            if (mMinuteUnitDigitView != null) {
                if (mLastMinuteUnitDigit < 0) {
                    mMinuteUnitDigitAnimator = mMinuteUnitDigitView.animate(currentMinuteUnitDigit);
                }else {
                    mMinuteUnitDigitAnimator = mMinuteUnitDigitView.animate(mLastMinuteUnitDigit, currentMinuteUnitDigit);
                }
                mMinuteUnitDigitAnimator.setDuration(ANIMATE_DURATION);
                mMinuteUnitDigitAnimator.start();
                mLastMinuteUnitDigit = currentMinuteUnitDigit;
            }
        }

        int currentSecondTenDigit = currentSecond / 10;
        int currentSecondUnitDigit = currentSecond % 10;
        if (mLastSecondTenDigit != currentSecondTenDigit) {
            if (mSecondTenDigitView != null) {
                if (mLastSecondTenDigit < 0) {
                    mSecondTenDigitAnimator = mSecondTenDigitView.animate(currentSecondTenDigit);
                }else{
                    mSecondTenDigitAnimator = mSecondTenDigitView.animate(mLastSecondTenDigit, currentSecondTenDigit);

                }
                mSecondTenDigitAnimator.setDuration(ANIMATE_DURATION);
                mSecondTenDigitAnimator.start();
                mLastSecondTenDigit = currentSecondTenDigit;
            }
        }
        if (mLastSecondUnitDigit != currentSecondUnitDigit) {
            if (mSecondUnitDigitView != null) {
                if (mLastSecondUnitDigit < 0) {
                    mSecondUnitDigitAnimator = mSecondUnitDigitView.animate(currentSecondUnitDigit);
                }else{
                    mSecondUnitDigitAnimator = mSecondUnitDigitView.animate(mLastSecondUnitDigit, currentSecondUnitDigit);

                }
                mSecondUnitDigitAnimator.setDuration(ANIMATE_DURATION);
                mSecondUnitDigitAnimator.start();
                mLastSecondUnitDigit = currentSecondUnitDigit;
            }
        }
    }


}
