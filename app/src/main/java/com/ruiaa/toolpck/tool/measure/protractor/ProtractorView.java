package com.ruiaa.toolpck.tool.measure.protractor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.util.ConvertUtil;
import com.ruiaa.toolpck.util.ResUtil;
import com.ruiaa.toolpck.util.ThemeUtil;

/**
 * Created by ruiaa on 2016/12/14.
 */

public class ProtractorView extends View {
    public ProtractorView(Context context) {
        super(context);
        initPaintAndColor();
    }

    public ProtractorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaintAndColor();
    }

    public ProtractorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaintAndColor();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        viewHeight = MeasureSpec.getSize(heightMeasureSpec);
        viewWidth = MeasureSpec.getSize(widthMeasureSpec);
        initSize();
        setPaint();
        setMeasuredDimension(viewWidth, viewHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLine(canvas);
    }

    private int viewHeight;
    private int viewWidth;

    private Paint longLinePaint;
    private Paint shortLinePaint;
    private Paint dimenTextPaint;

    private int longLineColor;
    private int shortLineColor;
    private float longLineHeight;
    private float longLineWidth;
    private float dimenTextSize;

    private void initPaintAndColor() {
        longLinePaint = new Paint();
        shortLinePaint = new Paint();
        dimenTextPaint = new Paint();

        longLineColor = ResUtil.getThemeColor(getContext(), R.attr.colorPrimary);
        shortLineColor = ResUtil.getColor(R.color.md_grey_500);
    }

    private void initSize() {
        longLineHeight = ConvertUtil.mm2px(8);
        longLineWidth = longLineHeight / 8 * 0.2f;
        dimenTextSize = ResUtil.getDimenInPx(R.dimen.sp_16);
    }

    private void setPaint() {
        longLinePaint.setColor(longLineColor);
        longLinePaint.setStyle(Paint.Style.STROKE);
        longLinePaint.setStrokeWidth(longLineWidth);
        longLinePaint.setAntiAlias(true);

        shortLinePaint.setColor(shortLineColor);
        shortLinePaint.setStyle(Paint.Style.STROKE);
        shortLinePaint.setStrokeWidth(longLineWidth);
        shortLinePaint.setAntiAlias(true);

        dimenTextPaint.setStyle(Paint.Style.STROKE);
        dimenTextPaint.setTextSize(dimenTextSize);
        if (ThemeUtil.getNightModeSwitch()){
            dimenTextPaint.setColor(Color.WHITE);
        }
    }

    private void drawLine(Canvas canvas) {
        canvas.save();
        for (int i = 0; i <= 180; i++) {
            if (i % 30 == 0) {
                float textOffset = 0;
                if (i == 0) {
                    textOffset = dimenTextSize;
                } else if (i == 180) {
                    textOffset=-dimenTextSize*0.2f;
                } else if (i < 10) {
                    textOffset = dimenTextSize * 0.5f;
                } else if (i < 100) {
                    textOffset = dimenTextSize * 0.6f;
                } else {
                    textOffset = dimenTextSize * 0.9f;
                }
                canvas.drawText(String.valueOf(i), viewWidth - textOffset, longLineHeight + dimenTextSize * 1.4f, dimenTextPaint);

                canvas.drawLine(viewWidth, 0, viewWidth, longLineHeight * 1.1f, longLinePaint);
            } else if (i % 10 == 0) {
                canvas.drawLine(viewWidth, 0, viewWidth, longLineHeight * 0.7f, longLinePaint);
                //canvas.drawText(String.valueOf(i),viewWidth-dimenTextSize,longLineHeight*0.65f,dimenTextPaint);
            } else if (i % 5 == 0) {
                canvas.drawLine(viewWidth, 0, viewWidth, longLineHeight * 0.45f, shortLinePaint);
            } else {
                canvas.drawLine(viewWidth, 0, viewWidth, longLineHeight * 0.3f, shortLinePaint);
            }
            canvas.rotate(-1, viewWidth, viewHeight / 2);
        }
        canvas.restore();

        canvas.drawCircle(viewWidth,viewHeight/2,longLineHeight*0.1f,longLinePaint);
        canvas.drawCircle(viewWidth,viewHeight/2,longLineHeight*0.09f,longLinePaint);
    }

}
