package com.ruiaa.toolpck.tool.measure;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by ruiaa on 2016/12/10.
 */

public class SensorUtil {

    public static final int ORIENTATION = 1;

    private SensorManager sensorManager;
    private SensorEventListener listener = null;
    private OnRefreshListener refreshListener = null;

    public SensorUtil(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    }

    public void setRefreshListener(OnRefreshListener refreshListener) {
        this.refreshListener = refreshListener;
    }

    public boolean register(int type) {
        switch (type) {
            case ORIENTATION: {
                return initOrientationList();
            }
        }
        return false;
    }

    public void unRegister() {
        if (listener != null) {
            sensorManager.unregisterListener(listener);
            listener = null;
            refreshListener = null;
        }
    }

    private boolean initOrientationList() {
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor magnetic = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (accelerometer == null || magnetic == null) return false;

        listener = new SensorEventListener() {
            float[] mGravity;
            float[] mGeomagnetic;

            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
                    mGravity = event.values.clone();
                if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
                    mGeomagnetic = event.values.clone();
                if (mGravity != null && mGeomagnetic != null) {
                    float[] R = new float[9];
                    float[] I = new float[9];
                    boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
                    if (success) {
                        float[] orientation = new float[3];
                        SensorManager.getOrientation(R, orientation);
                        if (refreshListener != null) {
                            refreshListener.onRefresh(orientation);

                            /*
                             *
                             *  orientation[0] 绕z轴，指向北为0，北~东~南：0~1.5~3.14........北~西~南：0~-1.5~-3.14
                             *  orientation[1] 绕屏幕顶部x轴，底部抬起为正，底部下降为负，-3.14/2~~~~~3.14/2
                             *  orientation[2] 绕屏幕左边y轴，右边抬起为负，右边下降为正，-3.14~~~~~3.14
                             *
                             *
                             */
                        }
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        sensorManager.registerListener(listener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(listener, magnetic, SensorManager.SENSOR_DELAY_NORMAL);
        return true;
    }


    public interface OnRefreshListener {
        void onRefresh(float[] floats);
    }

    /*

Sensor.TYPE_LIGHT 光
Sensor.TYPE_PRESSURE 气压 hpa

















     */

}
