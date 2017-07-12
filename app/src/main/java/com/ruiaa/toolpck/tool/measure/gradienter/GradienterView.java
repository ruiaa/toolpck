package com.ruiaa.toolpck.tool.measure.gradienter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.util.ResUtil;

/**
 * Created by ruiaa on 2016/12/11.
 */

public class GradienterView extends View {

    public GradienterView(Context context, AttributeSet attrs) {
        super(context, attrs);

        parseAttrs(context, attrs);
    }

    public GradienterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        parseAttrs(context, attrs);
    }

    private int size;
    private Paint baseCirclePaint;
    private Paint bubblePaint;
    private int circleColor;
    private int bubbleColor;


    private void parseAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GradienterView);
        circleColor=a.getColor(R.styleable.GradienterView_gradienter_circle_color, ResUtil.getColor(R.color.colorPrimary));
        bubbleColor=a.getColor(R.styleable.GradienterView_gradienter_bubble_color,ResUtil.getColor(R.color.colorAccent));

        a.recycle();
    }

    private int padding = 20;
    private float bubbleRadius = 20.0f;
    private float bubbleCenterX = 0.5f;
    private float bubbleCenterY = 0.5f;
    private float circleStrokeWidth;
    private float bubbleCircleRadius=20f;

    private void initPaint() {
        baseCirclePaint = new Paint();
        baseCirclePaint.setColor(circleColor);
        baseCirclePaint.setStyle(Paint.Style.STROKE);
        baseCirclePaint.setStrokeWidth(circleStrokeWidth);
        baseCirclePaint.setAntiAlias(true);

        bubblePaint = new Paint();
        bubblePaint.setColor(bubbleColor);
        bubblePaint.setStyle(Paint.Style.FILL);
    }

    private void initSize(int size){
        this.size=size;
        bubbleRadius=size*0.03f;
        circleStrokeWidth=ResUtil.getDimenInPx(R.dimen.dp_2);
        bubbleCircleRadius=bubbleRadius*1.3f;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        initSize(Math.min(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec)));
        initPaint();
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBaseCircle(canvas);
        drawBubble(canvas);
    }

    private void drawBaseCircle(Canvas canvas) {
        canvas.drawCircle(size / 2, size / 2, (size - padding * 2) / 2, baseCirclePaint);
        canvas.drawCircle(size / 2, size / 2, (size - padding * 2) / 4, baseCirclePaint);
        canvas.drawCircle(size / 2, size / 2, bubbleCircleRadius, baseCirclePaint);
        canvas.drawLine(size/2,padding,size/2,size/2-bubbleCircleRadius,baseCirclePaint);
        canvas.drawLine(size/2,size-padding,size/2,size/2+bubbleCircleRadius,baseCirclePaint);
        canvas.drawLine(padding,size/2,size/2-bubbleCircleRadius,size/2,baseCirclePaint);
        canvas.drawLine(size-padding,size/2,size/2+bubbleCircleRadius,size/2,baseCirclePaint);
    }

    private void drawBubble(Canvas canvas) {
        canvas.drawCircle((size - padding * 2) * bubbleCenterX + padding, (size - padding * 2) * bubbleCenterY + padding, bubbleRadius, bubblePaint);
    }


    public void setPosition(float x, float y) {
            float rx = (0.5f - x);
            float ry = (0.5f - y);

            float r = (float) Math.sqrt(rx * rx + ry * ry);
            if (r > 0.5f) {
                rx = rx / r * 0.5f;
                ry = ry / r * 0.5f;
                x = 0.5f - rx;
                y = 0.5f - ry;
            }
            this.bubbleCenterX = x;
            this.bubbleCenterY = y;
            invalidate();
    }
}
