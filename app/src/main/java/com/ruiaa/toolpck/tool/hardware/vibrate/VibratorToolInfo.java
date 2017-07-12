package com.ruiaa.toolpck.tool.hardware.vibrate;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.tool.ToolInfo;


/**
 * Created by ruiaa on 2016/12/10.
 */

public class VibratorToolInfo extends ToolInfo {

    @Override
    public Class<?> getActivityClass() {
        return VibratorActivity.class;
    }

    @Override
    public int getIconResId() {
        return R.drawable.vibrate;
    }

    @Override
    public int getLabelResId() {
        return R.string.vibrate;
    }
}
