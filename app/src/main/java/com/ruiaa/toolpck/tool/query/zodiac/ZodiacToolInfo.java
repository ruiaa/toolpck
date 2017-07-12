package com.ruiaa.toolpck.tool.query.zodiac;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.tool.ToolInfo;

/**
 * Created by ruiaa on 2016/12/24.
 */

public class ZodiacToolInfo extends ToolInfo {

    @Override
    public Class<?> getActivityClass() {
        return ZodiacActivity.class;
    }

    @Override
    public int getIconResId() {
        return R.drawable.zodiac;
    }

    @Override
    public int getLabelResId() {
        return R.string.zodiac;
    }
}
