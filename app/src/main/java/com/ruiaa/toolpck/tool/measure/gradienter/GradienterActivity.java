package com.ruiaa.toolpck.tool.measure.gradienter;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.base.ToolbarActivity;
import com.ruiaa.toolpck.databinding.ActivityGradienterBinding;
import com.ruiaa.toolpck.tool.measure.SensorUtil;

public class GradienterActivity extends ToolbarActivity {

    private ActivityGradienterBinding binding;
    private SensorUtil sensorUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_gradienter);

        sensorUtil = new SensorUtil(this);
        setToolbar();
        setGradienter();
    }

    @Override
    protected void onDestroy() {
        sensorUtil.unRegister();
        super.onDestroy();
    }

    private void setToolbar() {
        initToolbar();
        setToolbarLeftBack();
        setTitle(R.string.gradienter);
    }

    private void setGradienter(){
        sensorUtil.setRefreshListener(floats -> {
            if (floats.length < 3) return;

            float xRaw=floats[1];
            float yRaw=floats[2];

            float x = xRaw / (float) Math.PI + 0.5f; //-0.5~0.5 -> 0~1
            float y = -yRaw / (float) Math.PI + 0.5f;//-1~~1 -> -0.5~1.5
            if (y > 1) {
                y = 2 - y;
            } else if (y < 0) {
                y = -y;
            }

            binding.activityGradienterView.setPosition(y, x);
        });
        sensorUtil.register(SensorUtil.ORIENTATION);
    }

}
