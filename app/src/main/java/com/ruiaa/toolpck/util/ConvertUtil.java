package com.ruiaa.toolpck.util;

import android.content.Context;

/**
 * Created by ruiaa on 2016/10/25.
 */

public class ConvertUtil  {

    private static Context context;

    public static void register(Context appContext) {
        context = appContext.getApplicationContext();
    }


    public static int dp2px(float dpValue) {
        if (dpValue==0) return 0;
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    public static int px2dp( float pxValue) {
        if (pxValue==0) return 0;
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public static int sp2px(float spValue) {
        if (spValue==0) return 0;
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


    public static int px2sp(float pxValue) {
        if (pxValue==0) return 0;
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static float mm2px(float mm){
        return mm * ((float) context.getResources().getDisplayMetrics().densityDpi) /25.4f;
    }
}
