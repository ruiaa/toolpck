package com.ruiaa.toolpck.tool.query.phonecode;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.tool.ToolInfo;

/**
 * Created by ruiaa on 2016/12/21.
 */

public class PhoneCodeToolInfo extends ToolInfo {

    @Override
    public Class<?> getActivityClass() {
        return PhoneCodeActivity.class;
    }

    @Override
    public int getIconResId() {
        return R.drawable.phone_code;
    }

    @Override
    public int getLabelResId() {
        return R.string.phone_code_query;
    }
}
