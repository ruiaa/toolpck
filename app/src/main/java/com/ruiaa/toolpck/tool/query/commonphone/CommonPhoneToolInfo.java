package com.ruiaa.toolpck.tool.query.commonphone;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.tool.ToolInfo;

/**
 * Created by ruiaa on 2016/12/22.
 */

public class CommonPhoneToolInfo extends ToolInfo {
    @Override
    public Class<?> getActivityClass() {
        return CommonPhoneActivity.class;
    }

    @Override
    public int getIconResId() {
        return R.drawable.common_phone;
    }

    @Override
    public int getLabelResId() {
        return R.string.common_phone;
    }
}
