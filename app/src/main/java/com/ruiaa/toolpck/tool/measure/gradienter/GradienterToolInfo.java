package com.ruiaa.toolpck.tool.measure.gradienter;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.tool.ToolInfo;

/**
 * Created by ruiaa on 2016/12/10.
 */

public class GradienterToolInfo extends ToolInfo {
    @Override
    public Class<?> getActivityClass() {
        return GradienterActivity.class;
    }

    @Override
    public int getIconResId() {
        return R.drawable.gradienter;
    }

    @Override
    public int getLabelResId() {
        return R.string.gradienter;
    }
}
