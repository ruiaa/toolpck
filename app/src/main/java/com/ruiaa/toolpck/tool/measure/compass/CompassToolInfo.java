package com.ruiaa.toolpck.tool.measure.compass;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.tool.ToolInfo;

/**
 * Created by ruiaa on 2016/12/11.
 */

public class CompassToolInfo extends ToolInfo {

    @Override
    public Class<?> getActivityClass() {
        return CompassActivity.class;
    }

    @Override
    public int getIconResId() {
        return R.drawable.compass;
    }

    @Override
    public int getLabelResId() {
        return R.string.compass;
    }
}
