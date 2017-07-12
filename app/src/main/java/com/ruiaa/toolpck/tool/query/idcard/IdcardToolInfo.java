package com.ruiaa.toolpck.tool.query.idcard;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.tool.ToolInfo;

/**
 * Created by ruiaa on 2016/12/24.
 */

public class IdcardToolInfo extends ToolInfo {
    @Override
    public Class<?> getActivityClass() {
        return IdcardActivity.class;
    }

    @Override
    public int getIconResId() {
        return R.drawable.idcard;
    }

    @Override
    public int getLabelResId() {
        return R.string.idcard;
    }
}
