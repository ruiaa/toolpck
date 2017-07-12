package com.ruiaa.toolpck.tool.hardware.vibrate;

import android.content.Context;
import android.os.Vibrator;

import com.ruiaa.toolpck.util.LogUtil;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by ruiaa on 2016/12/10.
 */

public class VibratorUtil {

    private static VibratorUtil instance = null;
    private Vibrator vibrator;
    private boolean canVibrator;
    private boolean isTurnOn;
    private OnStopListener onStopListener = null;
    private Subscription subscription = null;
    private long startTime = 0;
    private long continueTime = 0;
    private long vibratorTimeInPeriod = 0;
    private long waitTimeInPeriod = 0;
    private boolean limitless = true;
    private boolean repeat = false;

    public static VibratorUtil getInstance(Context context) {
        if (instance == null) {
            instance = new VibratorUtil(context);
        }
        return instance;
    }

    private VibratorUtil(Context context) {
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        canVibrator = vibrator.hasVibrator();
    }

    public void setOnStopListener(OnStopListener onStopListener) {
        this.onStopListener = onStopListener;
    }

    public boolean isCanVibrator() {
        return canVibrator;
    }

    /*
     *  开关
     */
    public boolean isTurnOn() {
        return isTurnOn;
    }

    public boolean setTurnOn(boolean turn) {
        if (turn) {
            return isTurnOn = start();
        } else {
            cancel();
            return isTurnOn = false;
        }
    }

    /*
     *  持续
     */
    public long getContinueTime() {
        return continueTime/1000;
    }

    public void setContinueTime(long continueTime) {
        this.continueTime = continueTime*1000;
        startTime = System.currentTimeMillis();
    }

    public boolean isLimitless() {
        return limitless;
    }

    public void setLimitless(boolean limitless) {
        this.limitless = limitless;
        startTime = System.currentTimeMillis();
    }

    /*
     * 重复
     */
    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
        if (isTurnOn()) start();
    }

    public long getVibratorTimeInPeriod() {
        return vibratorTimeInPeriod;
    }

    public void setVibratorTimeInPeriod(long vibratorTimeInPeriod) {
        this.vibratorTimeInPeriod = vibratorTimeInPeriod;
    }

    public long getWaitTimeInPeriod() {
        return waitTimeInPeriod;
    }

    public void setWaitTimeInPeriod(long waitTimeInPeriod) {
        this.waitTimeInPeriod = waitTimeInPeriod;
    }

    /*
                 *
                 */
    private boolean start() {
        startTime = System.currentTimeMillis();

        if (repeat) {
            vibrator.vibrate(new long[]{waitTimeInPeriod, vibratorTimeInPeriod}, 0);
        } else {
            vibrator.vibrate(new long[]{0,1000}, 0);
        }
        if (subscription == null) {
            subscription = Observable
                    .interval(500, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aLong -> {
                                if (isTurnOn && !limitless && System.currentTimeMillis() > startTime + continueTime) {
                                    cancel();
                                    if (onStopListener != null) {
                                        onStopListener.onStop();
                                    }
                                }
                            },
                            throwable -> {
                                LogUtil.e("start--", throwable);
                            }
                    );
        }
        return true;
    }

    private void cancel() {
        isTurnOn = false;
        vibrator.cancel();
    }

    public void release() {
        onStopListener=null;
        if (isTurnOn) return;

        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        instance = null;
    }

    interface OnStopListener {
        void onStop();
    }
}
