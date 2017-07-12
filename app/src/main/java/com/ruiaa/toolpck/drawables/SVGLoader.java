package com.ruiaa.toolpck.drawables;


import com.ruiaa.toolpck.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.LongSparseArray;

import com.github.megatronking.svg.support.SVGDrawable;
import com.github.megatronking.svg.support.SVGHelper;

/**
 * AUTO-GENERATED FILE.  DO NOT MODIFY.
 * 
 * This class was automatically generated by the
 * SVG-Generator. It should not be modified by hand.<br><br>
 *
 * Call the follow in your Application: 
 *
 * <pre class="prettyprint">
 * public class MyApplication extends Application {
 *
 *    public void onCreate() {
 *        SVGLoader.load(this)
 *    }
 *
 * }
 * </pre>
 */
public class SVGLoader  {

    private static LongSparseArray<Drawable.ConstantState> sPreloadedDrawables;

    public static void load(Context context) {
        sPreloadedDrawables = SVGHelper.hackPreloadDrawables(context.getResources());
        if (sPreloadedDrawables == null) {
            return;
        }
        add(context, R.drawable.alarm, SVGDrawable.SVGDrawableConstantState.create(new alarm(context)));
        add(context, R.drawable.barcode, SVGDrawable.SVGDrawableConstantState.create(new barcode(context)));
        add(context, R.drawable.calculator, SVGDrawable.SVGDrawableConstantState.create(new calculator(context)));
        add(context, R.drawable.common_phone, SVGDrawable.SVGDrawableConstantState.create(new common_phone(context)));
        add(context, R.drawable.compass, SVGDrawable.SVGDrawableConstantState.create(new compass(context)));
        add(context, R.drawable.currencyrate, SVGDrawable.SVGDrawableConstantState.create(new currencyrate(context)));
        add(context, R.drawable.environment, SVGDrawable.SVGDrawableConstantState.create(new environment(context)));
        add(context, R.drawable.express, SVGDrawable.SVGDrawableConstantState.create(new express(context)));
        add(context, R.drawable.flashlight, SVGDrawable.SVGDrawableConstantState.create(new flashlight(context)));
        add(context, R.drawable.gradienter, SVGDrawable.SVGDrawableConstantState.create(new gradienter(context)));
        add(context, R.drawable.idcard, SVGDrawable.SVGDrawableConstantState.create(new idcard(context)));
        add(context, R.drawable.personaltax, SVGDrawable.SVGDrawableConstantState.create(new personaltax(context)));
        add(context, R.drawable.phone_code, SVGDrawable.SVGDrawableConstantState.create(new phone_code(context)));
        add(context, R.drawable.pm25, SVGDrawable.SVGDrawableConstantState.create(new pm25(context)));
        add(context, R.drawable.protractor, SVGDrawable.SVGDrawableConstantState.create(new protractor(context)));
        add(context, R.drawable.qrcode, SVGDrawable.SVGDrawableConstantState.create(new qrcode(context)));
        add(context, R.drawable.ruler, SVGDrawable.SVGDrawableConstantState.create(new ruler(context)));
        add(context, R.drawable.soundrecord, SVGDrawable.SVGDrawableConstantState.create(new soundrecord(context)));
        add(context, R.drawable.time_count, SVGDrawable.SVGDrawableConstantState.create(new time_count(context)));
        add(context, R.drawable.time_countdown, SVGDrawable.SVGDrawableConstantState.create(new time_countdown(context)));
        add(context, R.drawable.unitconver, SVGDrawable.SVGDrawableConstantState.create(new unitconver(context)));
        add(context, R.drawable.vibrate, SVGDrawable.SVGDrawableConstantState.create(new vibrate(context)));
        add(context, R.drawable.weather, SVGDrawable.SVGDrawableConstantState.create(new weather(context)));
        add(context, R.drawable.zodiac, SVGDrawable.SVGDrawableConstantState.create(new zodiac(context)));
    }

    private static void add(Context context, int resId, SVGDrawable.SVGDrawableConstantState state) {
        sPreloadedDrawables.put(SVGHelper.resKey(context, resId), state);
    }

}