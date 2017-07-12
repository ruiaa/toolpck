package com.ruiaa.toolpck.tool.query.pm25;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.tool.ToolInfo;

/**
 * Created by ruiaa on 2016/12/24.
 */

public class Pm25ToolInfo extends ToolInfo {

    @Override
    public Class<?> getActivityClass() {
        return Pm25Activity.class;
    }

    @Override
    public int getIconResId() {
        return R.drawable.pm25;
    }

    @Override
    public int getLabelResId() {
        return R.string.pm25;
    }
}
