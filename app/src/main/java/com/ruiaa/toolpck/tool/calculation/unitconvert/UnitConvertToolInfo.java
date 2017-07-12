package com.ruiaa.toolpck.tool.calculation.unitconvert;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.tool.ToolInfo;

/**
 * Created by ruiaa on 2016/12/24.
 */

public class UnitConvertToolInfo extends ToolInfo {

    @Override
    public Class<?> getActivityClass() {
        return UnitConvertActivity.class;
    }

    @Override
    public int getIconResId() {
        return R.drawable.unitconver;
    }

    @Override
    public int getLabelResId() {
        return R.string.unit_convert;
    }
}
