package com.ruiaa.toolpck.tool.query.pm25;

import android.os.Bundle;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.base.ToolbarActivity;

public class Pm25Activity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pm25);
        setToolbar();
    }

    private void setToolbar(){
        initToolbar();
        setToolbarLeftBack();
        setTitle(R.string.pm25);
    }
}

