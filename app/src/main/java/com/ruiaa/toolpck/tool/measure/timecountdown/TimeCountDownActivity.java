package com.ruiaa.toolpck.tool.measure.timecountdown;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.base.ToolbarActivity;
import com.ruiaa.toolpck.databinding.ActivityTimeCountDownBinding;
import com.ruiaa.toolpck.databinding.ViewSliderChooseBinding;
import com.ruiaa.toolpck.util.ResUtil;

public class TimeCountDownActivity extends ToolbarActivity {

    private ActivityTimeCountDownBinding binding;
    private ViewSliderChooseBinding chooseBinding;
    private TimeCountDownUtil timeCountDownUtil;
    private MaterialDialog timePicker = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_time_count_down);
        chooseBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.view_slider_choose, (ViewGroup) (binding.getRoot()), false);
        timeCountDownUtil = TimeCountDownUtil.getInstance(this);

        binding.setLeftCountTime(timeCountDownUtil.leftCountTime);
        binding.setPauseing(timeCountDownUtil.pauseing);


        initDialog();
        setToolbar();
        setListener();
    }

    @Override
    protected void onDestroy() {
        timeCountDownUtil.release();
        super.onDestroy();
    }

    private void setToolbar() {
        initToolbar();
        setToolbarLeftBack();
        setTitle(R.string.time_count_down);
    }

    private void setListener() {
        binding.timeCountDownStart.setOnClickListener(v -> {
            //timeCountDownUtil.start(1000*60);
            timePicker.setTitle(R.string.set_time);
            timePicker.show();
        });
        binding.timeCountDownContinuePause.setOnClickListener(v -> {
            timeCountDownUtil.setPauseing(!timeCountDownUtil.isPauseing());
        });
        binding.timeCountDownReset.setOnClickListener(v -> {
            timeCountDownUtil.reset();
        });
    }

    private void initDialog() {
        timePicker = new MaterialDialog.Builder(TimeCountDownActivity.this)
                .title(ResUtil.getString(R.string.time_count_down))
                .titleGravity(GravityEnum.CENTER)
                .customView(chooseBinding.getRoot(), false)
                .positiveText(R.string.sure)
                .onPositive((dialog, which) -> {
                    timeCountDownUtil.start(chooseBinding.viewSliderChooseSliderMinute.getValue() * 60 * 1000 + chooseBinding.viewSliderChooseSliderSecond.getValue() * 1000);
                })
                .build();

    }

    private void initTimeLabelList() {

    }

    private void addTimeLabel() {

    }


}
