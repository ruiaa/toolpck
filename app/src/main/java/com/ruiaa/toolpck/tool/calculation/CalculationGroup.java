package com.ruiaa.toolpck.tool.calculation;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.tool.ToolInfo;
import com.ruiaa.toolpck.tool.calculation.calculator.CalculatorToolInfo;
import com.ruiaa.toolpck.tool.calculation.unitconvert.UnitConvertToolInfo;

/**
 * Created by ruiaa on 2016/12/10.
 */

public class CalculationGroup extends ToolInfo.Group {

    public CalculationGroup(){
        super();
        registerChildToolInfo(this,new CalculatorToolInfo());
        registerChildToolInfo(this,new UnitConvertToolInfo());
        //registerChildToolInfo(this,new CurrencyRateToolInfo());
        //registerChildToolInfo(this,new PersonalTaxToolInfo(order+70));
    }

    @Override
    public int getLabelResId() {
        return R.string.calculation;
    }
}
