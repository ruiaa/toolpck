package com.ruiaa.toolpck.common.widget;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.EditText;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.util.ConvertUtil;
import com.ruiaa.toolpck.util.ResUtil;

/**
 * Created by ruiaa on 2016/12/21.
 */

public class AutoSizeTextView extends EditText {
    public AutoSizeTextView(Context context) {
        super(context);
        init();
    }

    public AutoSizeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AutoSizeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private Paint testPaint;
    private float minTextSize ;
    private float maxTextSize ;


    private void init() {
        testPaint = new Paint();
        testPaint.set(this.getPaint());
        minTextSize= ResUtil.getDimenInPx(R.dimen.sp_14);
        maxTextSize= ResUtil.getDimenInPx(R.dimen.sp_36);
        setSingleLine(true);
        setCursorVisible ( false ) ;
        setTextSize(ConvertUtil.px2sp(maxTextSize));
    }

    private void refitText(String text, int textWidth) {
        if (textWidth > 0) {
            int availableWidth = textWidth - this.getPaddingLeft()
                    - this.getPaddingRight();
            float trySize = maxTextSize;
            testPaint.setTextSize(trySize);
            while ((trySize > minTextSize) && (testPaint.measureText(text) > availableWidth)) {
                trySize -= 1;
                if (trySize <= minTextSize) {
                    trySize = minTextSize;
                    break;
                }
                testPaint.setTextSize(trySize);
            }
            setTextSize(TypedValue.COMPLEX_UNIT_PX,trySize);
        }
    };

    @Override
    protected void onTextChanged(CharSequence text, int start, int before,
                                 int after) {
        super.onTextChanged(text, start, before, after);
        refitText(text.toString(), this.getWidth());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w != oldw) {
            refitText(this.getText().toString(), w);
        }
    }

}
