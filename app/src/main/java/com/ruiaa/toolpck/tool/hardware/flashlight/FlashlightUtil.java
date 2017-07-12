package com.ruiaa.toolpck.tool.hardware.flashlight;

import android.hardware.Camera;

import com.ruiaa.toolpck.util.LogUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;

/**
 * Created by ruiaa on 2016/12/9.
 */

public class FlashlightUtil {

    private static final int TIME_LENGTH_MAX = 100;
    private static final int TIME_INTERVAL=50;      //最大周期 100*2*50=10000mills=10s
    private static int Brightness_Max = -1;

    private static FlashlightUtil instance = null;

    private Camera camera;
    private Camera.Parameters parameter;
    private boolean isCreateCameraing = false;

    private boolean isSOS=false;
    private int sosTime=0;
    private boolean isTurnOn = false;
    private boolean isTwinkle = false;
    private long betweenTime = 0;
    private long brightTimeLength = 1;
    private long darkTimeLength = 1;
    private Subscription subscription = null;

    private int brightness = 1;


    public static FlashlightUtil getInstance() {
        if (instance == null) {
            instance = new FlashlightUtil();
        }
        return instance;
    }

    private FlashlightUtil() {
        new Thread(() -> {
            isCreateCameraing = true;
            camera = Camera.open();
            parameter = camera.getParameters();
            isCreateCameraing = false;

            //getBrightnessMax();
        }).start();
    }

    /*
     * 开关
     */
    public boolean isTurnOn() {
        return isTurnOn;
    }

    public boolean setTurnOn(boolean turnOn) {
        if (turnOn) {
            isTurnOn = open();
        } else {
            isTurnOn = false;
            close();
        }
        return isTurnOn;
    }

    /*
     * 亮度
     */
    public void setBrightness(int levelPercent) {
        if (Brightness_Max<0) return;
        levelPercent = levelPercent == 0 ? 1 : levelPercent;
        brightness = Brightness_Max*levelPercent/100;
        resetLightStrength();
    }

    public int getBrightness() {
        return brightness;
    }

    /*
     * 闪烁
     */
    public boolean isTwinkle() {
        return isTwinkle;
    }

    public boolean setTwinkle(boolean twinkle) {
        isTwinkle = twinkle;
        if (twinkle) {
            openTwinkle();
        }
        return isTwinkle;
    }

    public void setBrightTimeLength(int brightTimeLength) {
        this.brightTimeLength = toTimeLength(brightTimeLength);
    }

    public long getBrightTimeLength() {
        return brightTimeLength;
    }

    public void setDarkTimeLength(int darkTimeLength) {
        this.darkTimeLength = toTimeLength(darkTimeLength);
    }

    public long getDarkTimeLength() {
        return darkTimeLength;
    }

    private long toTimeLength(int length){
        length=Math.abs(100-length);
        double p=1-(Math.sqrt(length)/10);
        long t=(long) (TIME_LENGTH_MAX*p);
        return t==0 ? 1:t;
    }

    /*
     * SOS  10101...000...11101110111...000...10101...0000000  间隔时间：滴，1t；答,3t；滴答间,1t；字母间,3t；字间,7t。
     *
     */
    //SOS  10101...000...11101110111...000...10101...0000000  间隔时间：滴，1t；答,3t；滴答间,1t；字母间,3t；字间,7t。
    //     0,2,4.........8-10,12-14,16-18,..22,24,26,......(33)
    private static final List<Integer> SOS_ON= Arrays.asList(0,2,4,  8,9,10,  12,13,14,  16,17,18,  22,24,26);
    public boolean isSOS() {
        return isSOS;
    }

    public void setSOS(boolean SOS) {
        isSOS = SOS;
        if (SOS){
            openTwinkle();
        }
        setTurnOn(SOS);
    }



    private boolean open() {
        if (isCreateCameraing) {
            return false;
        }
        if (camera == null) {
            camera = Camera.open();
            parameter = camera.getParameters();
        }
        parameter.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(parameter);
        camera.startPreview();
        return true;
    }

    private boolean close() {
        if (camera != null) {
            parameter.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(parameter);
            camera.stopPreview();
        }
        return true;
    }

    private void openTwinkle() {
        betweenTime = 0;
        sosTime=0;
        if (subscription == null) {
            subscription = Observable
                    .interval(TIME_INTERVAL, TimeUnit.MILLISECONDS)
                    .subscribe(
                            aLong -> {
                                if (isSOS){

                                    if (SOS_ON.contains(sosTime)){
                                        open();
                                    }else {
                                        close();
                                    }

                                    if (aLong%3==0) sosTime=sosTime+1;
                                    if (sosTime>=34) sosTime=0;

                                    return;
                                }


                                if (betweenTime == 0&&isTurnOn && isTwinkle) {
                                    open();
                                } else if (betweenTime == brightTimeLength&&isTurnOn && isTwinkle) {
                                    close();
                                }

                                betweenTime = betweenTime + 1;
                                if (betweenTime>=brightTimeLength+darkTimeLength){
                                    betweenTime=0;
                                }

                            },
                            throwable -> {
                                LogUtil.e("turnOnTwinkle--", throwable);
                            }
                    );

        }
    }

    private void getBrightnessMax(){
        File file = new File("/sys/class/leds/torch_led/max_brightness");
        if ( file == null || !file.exists() ) {
            LogUtil.i("getBrightnessMax--no");
            return;
        }

        try {
            BufferedReader input=new BufferedReader(new FileReader(file));
            try {
                Brightness_Max=Integer.valueOf(input.readLine());
                LogUtil.i("getBrightnessMax--"+Brightness_Max);
            } finally {
                input.close();
            }
        } catch ( IOException e ) {
            LogUtil.e("getBrightnessMax--",e);
        }
    }

    private void resetLightStrength() {
        File file = new File("/sys/class/leds/torch_led/brightness");
        if ( file == null || !file.exists() ) {
            LogUtil.i("resetLightStrength--no");
            return;
        }

        try {
            BufferedReader input=new BufferedReader(new FileReader(file));
            int b=Integer.valueOf(input.readLine());
            LogUtil.i("resetLightStrength--"+b);

            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            try {
                output.write(String.valueOf(brightness));
                LogUtil.i("resetLightStrength--"+brightness);
            } finally {
                input.close();
                output.close();
            }
        } catch ( IOException e ) {
            LogUtil.e("resetLightStrength--",e);
        }
    }


    public void release() {
        if (isTurnOn) return;

        if (camera != null) {
            camera.release();
            camera = null;
        }
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        instance = null;
    }
}
