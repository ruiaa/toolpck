package com.ruiaa.toolpck.tool.calculation.unitconvert;

import android.os.Bundle;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.base.ToolbarActivity;

public class UnitConvertActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_convert);
        setToolbar();
    }

    private void setToolbar(){
        initToolbar();
        setToolbarLeftBack();
        setTitle(R.string.unit_convert);
    }
}
