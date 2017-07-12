package com.ruiaa.toolpck.tool.measure.alarm;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.tool.ToolInfo;

/**
 * Created by ruiaa on 2016/12/15.
 */

public class AlarmToolInfo extends ToolInfo {

    @Override
    public Class<?> getActivityClass() {
        return AlarmActivity.class;
    }

    @Override
    public int getIconResId() {
        return R.drawable.alarm;
    }

    @Override
    public int getLabelResId() {
        return R.string.alarm;
    }
}
