package com.ruiaa.toolpck.tool.measure;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.tool.ToolInfo;
import com.ruiaa.toolpck.tool.measure.alarm.AlarmToolInfo;
import com.ruiaa.toolpck.tool.measure.compass.CompassToolInfo;
import com.ruiaa.toolpck.tool.measure.environment.EnvironmentToolInfo;
import com.ruiaa.toolpck.tool.measure.gradienter.GradienterToolInfo;
import com.ruiaa.toolpck.tool.measure.protractor.ProtractorToolInfo;
import com.ruiaa.toolpck.tool.measure.ruler.RulerToolInfo;
import com.ruiaa.toolpck.tool.measure.timecount.TimeCountToolInfo;
import com.ruiaa.toolpck.tool.measure.timecountdown.TimeCountDownToolInfo;

/**
 * Created by ruiaa on 2016/12/10.
 */

public class MeasureGroup extends ToolInfo.Group {
    public MeasureGroup(){

        registerChildToolInfo(this,new TimeCountToolInfo());
        registerChildToolInfo(this,new TimeCountDownToolInfo());
        registerChildToolInfo(this,new AlarmToolInfo());
        registerChildToolInfo(this,new RulerToolInfo());
        registerChildToolInfo(this,new ProtractorToolInfo());
        registerChildToolInfo(this,new CompassToolInfo());
        registerChildToolInfo(this,new GradienterToolInfo());
        registerChildToolInfo(this,new EnvironmentToolInfo());
    }

    @Override
    public int getLabelResId() {
        return R.string.measure;
    }
}
