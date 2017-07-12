package com.ruiaa.toolpck.tool.calculation.currencyrate;

import android.os.Bundle;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.base.ToolbarActivity;

public class CurrencyRateActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_rate);
        setToolbar();
    }

    private void setToolbar(){
        initToolbar();
        setToolbarLeftBack();
        setTitle(R.string.currency_rate);
    }
}
