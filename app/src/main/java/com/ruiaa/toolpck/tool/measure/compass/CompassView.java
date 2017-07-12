package com.ruiaa.toolpck.tool.measure.compass;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.util.ColorUtil;
import com.ruiaa.toolpck.util.ResUtil;

/**
 * Created by ruiaa on 2016/12/11.
 */

public class CompassView extends View {

    public CompassView(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttrs(context,attrs);
    }

    public CompassView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttrs(context, attrs);
    }

    private int size;
    private int padding;
    private float circleStrokeWidth;
    private RectF circleRectF;
    private Path northPointerPath;
    private Path southPointerPath;
    private float pointerWidth;
    private float pointerHeight;

    private Paint circlePaint;
    private Paint pointerPaint;
    private Paint textPaint;


    private int northColor;
    private int southColor;
    private int eastWestColor;

    private void parseAttrs(Context context, AttributeSet attrs){
        TypedArray a=context.obtainStyledAttributes(attrs, R.styleable.CompassView);
        northColor=a.getColor(R.styleable.CompassView_compass_north_color, ResUtil.getColor(R.color.colorAccent));
        southColor=a.getColor(R.styleable.CompassView_compass_south_color,ResUtil.getColor(R.color.colorPrimary));

        a.recycle();
    }

    private void initSizeAndColor(int size){
        this.size=size;
        padding=ResUtil.getDimenInPx(R.dimen.dp_40);
        circleStrokeWidth=ResUtil.getDimenInPx(R.dimen.dp_10);
        circleRectF=new RectF(padding,padding,size-padding,size-padding);


        pointerWidth=(size/2-padding-circleStrokeWidth)*0.3f;
        pointerHeight=(size/2-padding-circleStrokeWidth)*0.8f;
        northPointerPath=new Path();
        northPointerPath.moveTo(size/2-pointerWidth,size/2);
        northPointerPath.lineTo(size/2+pointerWidth,size/2);
        northPointerPath.lineTo(size/2,size/2-pointerHeight);
        northPointerPath.close();

        southPointerPath=new Path();
        southPointerPath.moveTo(size/2-pointerWidth,size/2);
        southPointerPath.lineTo(size/2+pointerWidth,size/2);
        southPointerPath.lineTo(size/2,size/2+pointerHeight);
        southPointerPath.close();

        eastWestColor= ColorUtil.blendColor(0.6f,southColor,northColor);
    }

    private void initPaint(){
        circlePaint=new Paint();
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setAntiAlias(true);
        circlePaint.setStrokeWidth(circleStrokeWidth);

        pointerPaint=new Paint();
        pointerPaint.setStyle(Paint.Style.FILL);
        circlePaint.setAntiAlias(true);

        textPaint=new Paint();
        textPaint.setTextSize(padding*0.5f);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setAntiAlias(true);
    }

    private void drawCompass(Canvas canvas){
        circlePaint.setColor(southColor);
        canvas.drawArc(circleRectF,0,180,false,circlePaint);
        circlePaint.setColor(northColor);
        canvas.drawArc(circleRectF,180,180,false,circlePaint);

        pointerPaint.setColor(southColor);
        canvas.drawPath(southPointerPath,pointerPaint);
        pointerPaint.setColor(northColor);
        canvas.drawPath(northPointerPath,pointerPaint);

        pointerPaint.setColor(Color.WHITE);
        canvas.drawCircle(size/2,size/2,size*0.015f,pointerPaint);

        textPaint.setColor(northColor);
        canvas.drawText(ResUtil.getString(R.string.north),size/2-padding*0.25f,padding*0.6f,textPaint);
        canvas.rotate(90,size/2,size/2);
        textPaint.setColor(eastWestColor);
        canvas.drawText(ResUtil.getString(R.string.east),size/2-padding*0.25f,padding*0.6f,textPaint);
        canvas.rotate(90,size/2,size/2);
        textPaint.setColor(southColor);
        canvas.drawText(ResUtil.getString(R.string.south),size/2-padding*0.25f,padding*0.6f,textPaint);
        canvas.rotate(90,size/2,size/2);
        textPaint.setColor(eastWestColor);
        canvas.drawText(ResUtil.getString(R.string.west),size/2-padding*0.25f,padding*0.6f,textPaint);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        initSizeAndColor(Math.min(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec)));
        initPaint();
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (lastTime==0) return;

        canvas.save();
        canvas.rotate(position,size/2,size/2);
        drawCompass(canvas);
        canvas.restore();
    }

    private float position=90;//360
    private long lastTime=0;
    public void setPosition(float position) {
        if (System.currentTimeMillis()-lastTime<150) return;

        lastTime=System.currentTimeMillis();
        this.position = -position*360;
        invalidate();
    }
}
