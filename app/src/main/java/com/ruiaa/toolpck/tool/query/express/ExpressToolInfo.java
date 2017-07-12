package com.ruiaa.toolpck.tool.query.express;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.tool.ToolInfo;

/**
 * Created by ruiaa on 2016/12/23.
 */

public class ExpressToolInfo extends ToolInfo {
    @Override
    public Class<?> getActivityClass() {
        return ExpressActivity.class;
    }

    @Override
    public int getIconResId() {
        return R.drawable.express;
    }

    @Override
    public int getLabelResId() {
        return R.string.express_query;
    }
}
