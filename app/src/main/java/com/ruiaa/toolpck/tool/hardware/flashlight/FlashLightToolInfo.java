package com.ruiaa.toolpck.tool.hardware.flashlight;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.tool.ToolInfo;


/**
 * Created by ruiaa on 2016/12/8.
 */

public class FlashLightToolInfo extends ToolInfo {
    @Override
    public Class<?> getActivityClass() {
        return FlashlightActivity.class;
    }

    @Override
    public int getIconResId() {
        return R.drawable.flashlight;
    }

    @Override
    public int getLabelResId() {
        return R.string.flashlight;
    }
}
