package com.ruiaa.toolpck.tool.query.barcode;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.tool.ToolInfo;

/**
 * Created by ruiaa on 2016/12/24.
 */

public class BarcodeToolInfo extends ToolInfo {

    @Override
    public Class<?> getActivityClass() {
        return BarcodeActivity.class;
    }

    @Override
    public int getIconResId() {
        return R.drawable.barcode;
    }

    @Override
    public int getLabelResId() {
        return R.string.barcode;
    }
}
