package com.ruiaa.toolpck.tool.measure.ruler;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.base.ToolbarActivity;
import com.ruiaa.toolpck.databinding.ActivityRulerBinding;
import com.ruiaa.toolpck.util.ThemeUtil;

public class RulerActivity extends ToolbarActivity {

    private ActivityRulerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_ruler);

        setToolbar();
    }

    private void setToolbar(){
        initToolbar();
        setToolbarLeftBack();
        toolbar.setBackgroundColor(Color.TRANSPARENT);
        if (!ThemeUtil.getNightModeSwitch()){
            toolbar.setNavigationIcon(R.mipmap.nav_back);
        }
    }


}
