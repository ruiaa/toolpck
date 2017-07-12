package com.ruiaa.toolpck.tool.measure.environment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.base.ToolbarActivity;
import com.ruiaa.toolpck.databinding.ActivityEnvironmentBinding;

public class EnvironmentActivity extends ToolbarActivity {

    ActivityEnvironmentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_environment);

        setToolbar();
    }

    private void setToolbar(){
        initToolbar();
        setToolbarLeftBack();
        setTitle(R.string.environment);
    }

    private void setTemperature(){

    }

}
