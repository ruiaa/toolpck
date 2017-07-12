package com.ruiaa.toolpck.util;

import android.graphics.Color;

/**
 * Created by ruiaa on 2016/12/11.
 */

public class ColorUtil {
    public static int blendColor(int fg, int bg) {
       return blendColor(Color.alpha(fg)/255,fg,bg);
    }

    public static int blendColor(float fgRatio,int fg,int bg){
        int scr = Color.red(fg);
        int scg = Color.green(fg);
        int scb = Color.blue(fg);
        int sa = Color.alpha(fg);
        if (fgRatio>=0&&fgRatio<=1){
            sa=(int)(255*fgRatio);
        }
        int dcr = Color.red(bg);
        int dcg = Color.green(bg);
        int dcb = Color.blue(bg);
        int color_r = dcr * (0xff - sa) / 0xff + scr * sa / 0xff;
        int color_g = dcg * (0xff - sa) / 0xff + scg * sa / 0xff;
        int color_b = dcb * (0xff - sa) / 0xff + scb * sa / 0xff;
        return ((color_r << 16) + (color_g << 8) + color_b) | (0xff000000);
    }
}
