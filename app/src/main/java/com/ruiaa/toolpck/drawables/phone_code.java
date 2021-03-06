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
public class phone_code extends SVGRenderer {

    public phone_code(Context context) {
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
        
        mPath.moveTo(684.063f, 66.868f);
        mPath.lineTo(704.716f, 66.868f);
        mPath.lineTo(684.063f, 66.868f);
        mPath.close();
        mPath.moveTo(684.063f, 66.868f);
        
        mRenderPath.addPath(mPath, mFinalPathMatrix);
        if (mFillPaint == null) {
            mFillPaint = new Paint();
            mFillPaint.setStyle(Paint.Style.FILL);
            mFillPaint.setAntiAlias(true);
        }
        mFillPaint.setColor(applyAlpha(-13882324, 1.0f));
        mFillPaint.setColorFilter(filter);
        canvas.drawPath(mRenderPath, mFillPaint);
        mPath.reset();
        mRenderPath.reset();
        
        mFinalPathMatrix.setValues(new float[]{1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f});
        mFinalPathMatrix.postScale(scaleX, scaleY);
        
        mPath.moveTo(684.063f, 66.868f);
        mPath.lineTo(339.937f, 66.868f);
        mPath.rCubicTo(-30.393f, 0.0f, -55.048f, 29.425f, -55.048f, 65.717f);
        mPath.rLineTo(0.0f, 758.83f);
        mPath.rCubicTo(0.0f, 36.293f, 24.655f, 65.718f, 55.048f, 65.718f);
        mPath.rLineTo(344.126f, 0.0f);
        mPath.rCubicTo(30.402f, 0.0f, 55.048f, -29.425f, 55.048f, -65.718f);
        mPath.rLineTo(0.0f, -758.83f);
        mPath.cubicTo(739.111f, 96.292f, 714.466f, 66.868f, 684.063f, 66.868f);
        mPath.close();
        mPath.moveTo(684.063f, 66.868f);
        mPath.moveTo(512.061f, 913.377f);
        mPath.rCubicTo(-19.482f, 0.0f, -35.284f, -18.847f, -35.284f, -42.12f);
        mPath.rCubicTo(0.0f, -23.254f, 15.802f, -42.121f, 35.284f, -42.121f);
        mPath.rCubicTo(19.482f, 0.0f, 35.283f, 18.867f, 35.283f, 42.121f);
        mPath.cubicTo(547.344f, 894.53f, 531.543f, 913.377f, 512.061f, 913.377f);
        mPath.close();
        mPath.moveTo(512.061f, 913.377f);
        mPath.moveTo(697.809f, 784.544f);
        mPath.lineTo(326.192f, 784.544f);
        mPath.lineTo(326.192f, 239.455f);
        mPath.rLineTo(371.616f, 0.0f);
        mPath.lineTo(697.808f, 784.544f);
        mPath.close();
        mPath.moveTo(697.809f, 784.544f);
        
        mRenderPath.addPath(mPath, mFinalPathMatrix);
        mFillPaint.setColor(applyAlpha(-13882324, 1.0f));
        mFillPaint.setColorFilter(filter);
        canvas.drawPath(mRenderPath, mFillPaint);

    }

}