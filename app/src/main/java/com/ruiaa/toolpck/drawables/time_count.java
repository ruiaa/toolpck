package com.ruiaa.toolpck.drawables;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;

import com.github.megatronking.svg.support.SVGRenderer;

/**
 * AUTO-GENERATED FILE.  DO NOT MODIFY.
 * 
 * This class was automatically generated by the
 * SVG-Generator. It should not be modified by hand.
 */
public class time_count extends SVGRenderer {

    public time_count(Context context) {
        super(context);
        mAlpha = 1.0f;
        mWidth = (int)128.0f;
        mHeight = (int)128.0f;
    }

    @Override
    public void render(Canvas canvas, int w, int h, ColorFilter filter) {
        
        final float scaleX = w / 1024.0f;
        final float scaleY = h / 1024.0f;
        
        mPath.reset();
        mRenderPath.reset();
        
        mFinalPathMatrix.setValues(new float[]{1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f});
        mFinalPathMatrix.postScale(scaleX, scaleY);
        
        mPath.moveTo(539.39136f, 486.9955f);
        mPath.lineTo(539.39136f, 332.01886f);
        mPath.rCubicTo(0.0f, -16.508999f, -13.278421f, -29.89282f, -29.65746f, -29.89282f);
        mPath.rCubicTo(-16.394388f, 0.0f, -29.673834f, 13.384845f, -29.673834f, 29.89282f);
        mPath.rLineTo(0.0f, 154.97563f);
        mPath.rCubicTo(-34.490536f, 12.350283f, -59.33027f, 45.194317f, -59.33027f, 84.18842f);
        mPath.rCubicTo(0.0f, 49.538254f, 39.83424f, 89.70609f, 89.00513f, 89.70609f);
        mPath.rCubicTo(49.15349f, 0.0f, 89.00615f, -40.16784f, 89.00615f, -89.70609f);
        mPath.cubicTo(598.74005f, 532.1898f, 573.88293f, 499.34476f, 539.39136f, 486.9955f);
        mPath.close();
        mPath.moveTo(539.39136f, 486.9955f);
        mPath.moveTo(539.09357f, 184.03445f);
        mPath.rCubicTo(0.034792f, -0.51677f, 0.297782f, -0.963954f, 0.297782f, -1.49812f);
        mPath.rLineTo(0.0f, -59.786667f);
        mPath.rLineTo(118.68101f, 0.0f);
        mPath.rCubicTo(16.378016f, 0.0f, 29.67281f, -13.393032f, 29.67281f, -29.90203f);
        mPath.rCubicTo(0.0f, -16.510021f, -13.294794f, -29.89282f, -29.67281f, -29.89282f);
        mPath.lineTo(361.3811f, 62.954823f);
        mPath.rCubicTo(-16.37904f, 0.0f, -29.674856f, 13.382798f, -29.674856f, 29.89282f);
        mPath.rCubicTo(0.0f, 16.508999f, 13.295817f, 29.90203f, 29.674856f, 29.90203f);
        mPath.rLineTo(118.67896f, 0.0f);
        mPath.rLineTo(0.0f, 59.786667f);
        mPath.rCubicTo(0.0f, 0.534166f, 0.26299f, 0.980327f, 0.297782f, 1.49812f);
        mPath.cubicTo(281.11603f, 199.20293f, 124.03853f, 366.53806f, 124.03853f, 571.18396f);
        mPath.rCubicTo(0.0f, 214.65689f, 172.66757f, 388.6558f, 385.69537f, 388.6558f);
        mPath.rCubicTo(212.99402f, 0.0f, 385.68002f, -173.99889f, 385.68002f, -388.6558f);
        mPath.cubicTo(895.4139f, 366.53806f, 738.3364f, 199.20293f, 539.09357f, 184.03445f);
        mPath.close();
        mPath.moveTo(539.09357f, 184.03445f);
        mPath.moveTo(509.7339f, 900.05304f);
        mPath.rCubicTo(-180.23492f, 0.0f, -326.36407f, -147.23227f, -326.36407f, -328.8691f);
        mPath.rCubicTo(0.0f, -181.61127f, 146.12915f, -328.85376f, 326.36407f, -328.85376f);
        mPath.rCubicTo(180.23697f, 0.0f, 326.34872f, 147.24251f, 326.34872f, 328.85376f);
        mPath.cubicTo(836.0826f, 752.8208f, 689.9709f, 900.05304f, 509.7339f, 900.05304f);
        mPath.close();
        mPath.moveTo(509.7339f, 900.05304f);
        
        mRenderPath.addPath(mPath, mFinalPathMatrix);
        if (mFillPaint == null) {
            mFillPaint = new Paint();
            mFillPaint.setStyle(Paint.Style.FILL);
            mFillPaint.setAntiAlias(true);
        }
        mFillPaint.setColor(applyAlpha(-13882324, 1.0f));
        mFillPaint.setColorFilter(filter);
        canvas.drawPath(mRenderPath, mFillPaint);

    }

}