package com.ruiaa.toolpck.tool.measure.timecount;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.tool.ToolInfo;

/**
 * Created by ruiaa on 2016/12/15.
 */

public class TimeCountToolInfo extends ToolInfo {
    @Override
    public Class<?> getActivityClass() {
        return TimeCountActivity.class;
    }

    @Override
    public int getIconResId() {
        return R.drawable.time_count;
    }

    @Override
    public int getLabelResId() {
        return R.string.time_count;
    }
}
