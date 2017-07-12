package com.ruiaa.toolpck.tool.measure.ruler;

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
 * Created by ruiaa on 2016/12/13.
 */

public class RulerView extends View {

    public RulerView(Context context) {
        super(context);
        initColorAndPaint();
    }

    public RulerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initColorAndPaint();
    }

    public RulerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initColorAndPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        viewHeight=MeasureSpec.getSize(heightMeasureSpec);
        viewWidth=MeasureSpec.getSize(widthMeasureSpec);
        initSize();
        setPaint();
        setMeasuredDimension(viewWidth,viewHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLine(canvas);
    }

    private int viewHeight;
    private int viewWidth;
    private int cmLineWidth;
    private int cmLineHeight;
    private float mmPx;

    private int mmLineColor;
    private int cmLineColor;
    private Paint mmLinePaint;
    private Paint cmLinePaint;
    private Paint cmDimenPaint;

    private void initSize(){
        mmPx= ConvertUtil.mm2px(1);
        cmLineWidth=(int)(mmPx*0.2f);
        cmLineHeight=(int)(mmPx*8f);
    }

    private void initColorAndPaint(){
        mmLineColor= ResUtil.getColor(R.color.md_grey_500);
        cmLineColor = ResUtil.getThemeColor(getContext(), R.attr.colorPrimary);

        mmLinePaint=new Paint();

        cmLinePaint=new Paint();

        cmDimenPaint=new Paint();


    }

    private void setPaint(){

        mmLinePaint.setStyle(Paint.Style.STROKE);
        mmLinePaint.setStrokeWidth(cmLineWidth*0.8f);
        mmLinePaint.setColor(mmLineColor);
        mmLinePaint.setAntiAlias(true);

        cmLinePaint.setStyle(Paint.Style.STROKE);
        cmLinePaint.setStrokeWidth(cmLineWidth);
        cmLinePaint.setColor(cmLineColor);
        cmLinePaint.setAntiAlias(true);

        cmDimenPaint.setStyle(Paint.Style.STROKE);
        cmDimenPaint.setTextSize(ResUtil.getDimenInPx(R.dimen.sp_16));
        if (ThemeUtil.getNightModeSwitch()){
            cmDimenPaint.setColor(Color.WHITE);
        }
    }

    private void drawLine(Canvas canvas){
        canvas.save();
        canvas.rotate(90,viewWidth/2,viewHeight/2);
        canvas.translate(viewWidth/2-viewHeight/2,viewHeight/2-viewWidth/2);
        for (int dimen=0;dimen*mmPx<viewHeight;){
            if (dimen%10==0){
                canvas.drawLine(dimen*mmPx,0,dimen*mmPx,cmLineHeight,cmLinePaint);
                canvas.drawText(dimen==0 ? "0 cm":String.valueOf(dimen/10),dimen*mmPx+mmPx/2,cmLineHeight,cmDimenPaint);
            }else if (dimen%5==0){
                canvas.drawLine(dimen*mmPx,0,dimen*mmPx,cmLineHeight*0.6f,mmLinePaint);
            }else {
                canvas.drawLine(dimen*mmPx,0,dimen*mmPx,cmLineHeight*0.3f,mmLinePaint);
            }
            dimen++;
        }
        canvas.restore();
        canvas.save();
        canvas.rotate(-90,viewWidth/2,viewHeight/2);
        canvas.translate(viewWidth/2-viewHeight/2,viewHeight/2-viewWidth/2);
        for (int dimen=0;dimen*mmPx<viewHeight;){
            if (dimen%10==0){
                canvas.drawLine(dimen*mmPx,0,dimen*mmPx,cmLineHeight,cmLinePaint);
                canvas.drawText(dimen==0 ? "0 cm":String.valueOf(dimen/10),dimen*mmPx+mmPx/2,cmLineHeight,cmDimenPaint);
            }else if (dimen%5==0){
                canvas.drawLine(dimen*mmPx,0,dimen*mmPx,cmLineHeight*0.6f,mmLinePaint);
            }else {
                canvas.drawLine(dimen*mmPx,0,dimen*mmPx,cmLineHeight*0.3f,mmLinePaint);
            }
            dimen++;
        }
        canvas.restore();

    }


}
