package com.ruiaa.toolpck.tool.measure.timecountdown;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableLong;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Vibrator;

import com.ruiaa.toolpck.tool.measure.timecount.TimeCountUtil;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;

/**
 * Created by ruiaa on 2016/12/16.
 */

public class TimeCountDownUtil {

    private static TimeCountDownUtil instance=null;

    public ObservableLong leftCountTime;
    private Ringtone ringtone;
    private Vibrator vibrator;
    private ArrayList<Long> waitToRemindList;
    public ArrayList<TimeLabel> timeLabelList;
    private long remindStartTime =0;
    private Subscription subscription;
    public ObservableBoolean pauseing;
    private int remindTimeLength=5000;

    public static TimeCountDownUtil getInstance(Context context){
        if (instance!=null) {
            return instance;
        }else {
            return new TimeCountDownUtil(context);
        }
    }

    private TimeCountDownUtil(Context context) {
        leftCountTime=new ObservableLong();
        leftCountTime.set(0);
        pauseing=new ObservableBoolean();
        pauseing.set(true);

        ringtone= RingtoneManager.getRingtone(context,RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));
        vibrator=(Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        waitToRemindList =new ArrayList<>();
        timeLabelList=new ArrayList<>();

        countTime();
    }

    public void start(long time){
        waitToRemindList.clear();
        waitToRemindList.add(0L);

        leftCountTime.set(time);
        setPauseing(false);

    }

    public void reset(){
        leftCountTime.set(0);
        setPauseing(true);

        waitToRemindList.clear();
    }

    public boolean isPauseing() {
        return pauseing.get();
    }

    public void setPauseing(boolean pauseing) {
        this.pauseing.set(pauseing);
    }

    public void addTimeToRemind(long time){
        for (long l:waitToRemindList){
            if (l<time){
                waitToRemindList.add(waitToRemindList.indexOf(l),time);
            }
        }
    }


    private void countTime(){
        subscription= Observable.interval(10, TimeUnit.MILLISECONDS)
                .subscribe(
                        aLong -> {

                            if (remindStartTime!=0&&System.currentTimeMillis()-remindStartTime>remindTimeLength){
                                stopRemind();
                            }

                            if (isPauseing()) return;


                            long l=leftCountTime.get();
                            if (l>0){
                                l=l-10;
                                l=l<0 ? 0:l;
                                leftCountTime.set(l);

                                if (!waitToRemindList.isEmpty()){
                                    long w= waitToRemindList.get(0);
                                    if (w>=l){
                                        startRemind();
                                        waitToRemindList.remove(w);
                                    }
                                }
                            }
                        }
                );
    }

    private void startRemind(){
        remindStartTime=System.currentTimeMillis();
        if (!ringtone.isPlaying()){
            ringtone.play();
            vibrator.vibrate(new long[]{50, 500, 50, 500}, 0);
        }
    }

    private void stopRemind(){
        remindStartTime=0;
        ringtone.stop();
        vibrator.cancel();
    }

    public void release(){
        if (leftCountTime.get()>0) return;

        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        instance=null;
    }


    public static class TimeLabel{
        private String label;
        private long time;

        public TimeLabel(String label, long time) {
            this.label = label;
            this.time = time;
        }

        public String getLabel() {
            return label;
        }

        public String getTime() {
            return TimeCountUtil.getTimeMinSec(time);
        }

        public boolean getHadRemind(long countTime){
            return countTime<=time;
        }
    }
}
