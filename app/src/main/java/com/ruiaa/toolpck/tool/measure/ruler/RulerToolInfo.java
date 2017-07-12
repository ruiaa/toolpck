package com.ruiaa.toolpck.tool.measure.ruler;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.tool.ToolInfo;

/**
 * Created by ruiaa on 2016/12/13.
 */

public class RulerToolInfo extends ToolInfo {

    @Override
    public Class<?> getActivityClass() {
        return RulerActivity.class;
    }

    @Override
    public int getIconResId() {
        return R.drawable.ruler;
    }

    @Override
    public int getLabelResId() {
        return R.string.ruler;
    }
}
