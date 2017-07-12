package com.ruiaa.toolpck.tool.measure.alarm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.base.ToolbarActivity;
import com.ruiaa.toolpck.databinding.ActivityAlarmBinding;
import com.ruiaa.toolpck.tool.measure.timecount.TimeCountUtil;

public class AlarmActivity extends ToolbarActivity {

    private ActivityAlarmBinding binding;
    private TimeCountUtil timeCountUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_alarm);
        timeCountUtil =new TimeCountUtil(this,false);

        setToolbar();
    }

    private void setToolbar(){
        initToolbar();
        setToolbarLeftBack();
        setTitle(R.string.alarm);
    }
}
