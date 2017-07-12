package com.ruiaa.toolpck.tool.calculation.currencyrate;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.tool.ToolInfo;

/**
 * Created by ruiaa on 2016/12/24.
 */

public class CurrencyRateToolInfo extends ToolInfo {
    @Override
    public Class<?> getActivityClass() {
        return CurrencyRateActivity.class;
    }

    @Override
    public int getIconResId() {
        return R.drawable.currencyrate;
    }

    @Override
    public int getLabelResId() {
        return R.string.currency_rate;
    }
}
