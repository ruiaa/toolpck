package com.ruiaa.toolpck.tool.measure.timecountdown;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.tool.ToolInfo;

/**
 * Created by ruiaa on 2016/12/15.
 */

public class TimeCountDownToolInfo extends ToolInfo {

    @Override
    public Class<?> getActivityClass() {
        return TimeCountDownActivity.class;
    }

    @Override
    public int getIconResId() {
        return R.drawable.time_countdown;
    }

    @Override
    public int getLabelResId() {
        return R.string.time_count_down;
    }
}
