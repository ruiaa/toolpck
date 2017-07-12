package com.ruiaa.toolpck.tool.calculation.calculator;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.tool.ToolInfo;

/**
 * Created by ruiaa on 2016/12/15.
 */

public class CalculatorToolInfo extends ToolInfo {

    public CalculatorToolInfo() {
    }

    @Override
    public Class<?> getActivityClass() {
        return CalculatorActivity.class;
    }

    @Override
    public int getIconResId() {
        return R.drawable.calculator;
    }


    @Override
    public int getLabelResId() {
        return R.string.calculator;
    }
}
