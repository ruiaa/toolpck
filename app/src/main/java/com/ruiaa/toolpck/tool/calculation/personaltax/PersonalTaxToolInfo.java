package com.ruiaa.toolpck.tool.calculation.personaltax;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.tool.ToolInfo;

/**
 * Created by ruiaa on 2016/12/24.
 */

public class PersonalTaxToolInfo extends ToolInfo {

    @Override
    public Class<?> getActivityClass() {
        return PersonalTaxActivity.class;
    }

    @Override
    public int getIconResId() {
        return R.drawable.personaltax;
    }

    @Override
    public int getLabelResId() {
        return R.string.personal_tax;
    }
}
