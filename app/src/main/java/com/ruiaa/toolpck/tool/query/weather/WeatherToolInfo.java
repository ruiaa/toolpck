package com.ruiaa.toolpck.tool.query.weather;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.tool.ToolInfo;

/**
 * Created by ruiaa on 2016/12/24.
 */

public class WeatherToolInfo extends ToolInfo {

    @Override
    public Class<?> getActivityClass() {
        return WeatherActivity.class;
    }

    @Override
    public int getIconResId() {
        return R.drawable.weather;
    }

    @Override
    public int getLabelResId() {
        return R.string.weather;
    }
}
