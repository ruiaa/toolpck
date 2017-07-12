package com.ruiaa.toolpck.tool.measure.compass;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.base.ToolbarActivity;
import com.ruiaa.toolpck.databinding.ActivityCompassBinding;
import com.ruiaa.toolpck.tool.measure.SensorUtil;

public class CompassActivity extends ToolbarActivity {

    private ActivityCompassBinding binding;
    private SensorUtil sensorUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_compass);

        sensorUtil=new SensorUtil(this);
        setToolbar();
        setCompass();
    }

    @Override
    protected void onDestroy() {
        sensorUtil.unRegister();
        super.onDestroy();
    }

    private void setToolbar(){
        initToolbar();
        setToolbarLeftBack();
        setTitle(R.string.compass);
    }

    private void setCompass(){
        sensorUtil.register(SensorUtil.ORIENTATION);
        sensorUtil.setRefreshListener(floats -> {
            if (floats.length<3) return;

            //绕z轴，指向北为0，北~东~南：0~1.5~3.14........北~西~南：0~-1.5~-3.14
            float p=floats[0];

            //to 0~1
            if (p>0){
                p=p/((float) Math.PI*2);
            }else {
                p=p/((float) Math.PI*2)+1;
            }

            //屏幕朝下
            float y=floats[2];
            if (y>Math.PI/2||y<-Math.PI/2){
                p=1-p;
            }
            binding.activityGradienterView.setPosition(p);
            //LogUtil.i("setCompass--"+floats[0]);
        });
    }


}
