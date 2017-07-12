package com.ruiaa.toolpck.tool.query.weather;

import android.os.Bundle;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.base.ToolbarActivity;

public class WeatherActivity extends ToolbarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        setToolbar();
    }

    private void setToolbar(){
        initToolbar();
        setToolbarLeftBack();
        setTitle(R.string.weather);
    }
}
