package com.ruiaa.toolpck.tool.measure.timecount;

import android.content.Context;
import android.databinding.ObservableLong;
import android.os.Vibrator;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;


/**
 * Created by ruiaa on 2016/12/15.
 */

public class TimeCountUtil {

    public ObservableLong countTimeMillis;
    private Subscription subscription;
    private Vibrator vibrator;
    private boolean pauseing=true;

    public TimeCountUtil(Context context, boolean isMillis) {
        vibrator=(Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (isMillis){
            initMillisCounter();
        }else {
            initSecondCounter();
        }
    }

    private void initMillisCounter(){
        countTimeMillis=new ObservableLong();

        subscription= Observable.interval(10, TimeUnit.MILLISECONDS)
                .subscribe(aLong -> {
                    if (!pauseing) {
                        countTimeMillis.set(countTimeMillis.get() + 10);
                        //LogUtil.i("initCounter--"+countTimeMillis.get());
                    }
                });
    }

    private void initSecondCounter(){
        countTimeMillis=new ObservableLong();

        subscription= Observable.interval(1000, TimeUnit.MILLISECONDS)
                .subscribe(aLong -> {
                    if (!pauseing) {
                        countTimeMillis.set(countTimeMillis.get() + 1000);
                        //LogUtil.i("initCounter--"+countTimeMillis.get());
                    }
                });
    }

    public void setPauseing(boolean pauseing) {
        this.pauseing = pauseing;
    }

    public void reset(){
        countTimeMillis.set(0);
    }

    public void vibrator(){
        if (vibrator.hasVibrator()) {
            vibrator.vibrate(new long[]{0, 1000, 0, 1000, 0, 1000, 0, 1000}, -1);
        }
    }

    private void stopVibrator(){
        vibrator.cancel();
    }

    public void release() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    public static String getTimeMinute(long timeMillis){
        int m=(int)timeMillis/1000%3600/60;
        if (m<10) {
            return "0"+m;
        }else {
            return String.valueOf(m);
        }
    }

    public static String getTimeSecond(long timeMillis){
        int s=(int)timeMillis/1000%60;
        if (s<10){
            return "0"+s;
        }else {
            return String.valueOf(s);
        }
    }

    public static String getTimeMillisOne(long timeMillis){
        return String.valueOf( timeMillis%1000/100);
    }

    public static String getTimeMillisTwo(long timeMillis){
        int t=(int)timeMillis%1000/10;
        if (t<10){
            return "0"+t;
        }else {
            return String.valueOf(t);
        }
    }

    public static String getTimeMinSecMil(long timeMillis){
        return getTimeMinute(timeMillis)+":"+getTimeSecond(timeMillis)+":"+getTimeMillisTwo(timeMillis);
    }

    public static String getTimeMinSec(long timeMillis){
        return getTimeMinute(timeMillis)+":"+getTimeSecond(timeMillis);
    }
}
