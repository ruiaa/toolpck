package com.ruiaa.toolpck.tool.measure.environment;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.tool.ToolInfo;

/**
 * Created by ruiaa on 2016/12/11.
 */

public class EnvironmentToolInfo extends ToolInfo {

    @Override
    public Class<?> getActivityClass() {
        return EnvironmentActivity.class;
    }

    @Override
    public int getIconResId() {
        return R.drawable.environment;
    }

    @Override
    public int getLabelResId() {
        return R.string.environment;
    }
}
