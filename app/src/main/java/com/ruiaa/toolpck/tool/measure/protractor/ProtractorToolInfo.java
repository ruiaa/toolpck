package com.ruiaa.toolpck.tool.measure.protractor;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.tool.ToolInfo;

/**
 * Created by ruiaa on 2016/12/14.
 */

public class ProtractorToolInfo extends ToolInfo {

    @Override
    public Class<?> getActivityClass() {
        return ProtractorActivity.class;
    }

    @Override
    public int getIconResId() {
        return R.drawable.protractor;
    }

    @Override
    public int getLabelResId() {
        return R.string.protractor;
    }
}
