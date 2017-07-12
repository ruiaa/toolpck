package com.ruiaa.toolpck.tool.query.qrcode;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.tool.ToolInfo;

/**
 * Created by ruiaa on 2016/12/24.
 */

public class QrcodeToolInfo extends ToolInfo {

    @Override
    public Class<?> getActivityClass() {
        return QrcodeActivity.class;
    }

    @Override
    public int getIconResId() {
        return R.drawable.qrcode;
    }

    @Override
    public int getLabelResId() {
        return R.string.qrcode;
    }
}
