package com.ruiaa.toolpck.tool.hardware.vibrate;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.base.ToolbarActivity;
import com.ruiaa.toolpck.databinding.ActivityVibratorBinding;
import com.ruiaa.toolpck.util.ResUtil;
import com.ruiaa.toolpck.util.ThemeUtil;

public class VibratorActivity extends ToolbarActivity{

    private ActivityVibratorBinding binding;
    private VibratorUtil vibratorUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_vibrator);
        vibratorUtil=VibratorUtil.getInstance(this);

        fixKeyboardAdjustResizeNoEffect();

        initOldData();
        setTurnOnOff();
        setToolbar();
        setLimitless();
        setRepeat();
    }

    @Override
    protected void onDestroy() {
        vibratorUtil.release();
        super.onDestroy();
    }

    private void setToolbar(){
        initToolbar();
        setToolbarLeftBack();
        setTitle(R.string.vibrate);
    }

    private void initOldData(){
        binding.activityVibratorSwitch.setSelected(vibratorUtil.isTurnOn());
        binding.activityVibratorLimitlessSwitch.setChecked(vibratorUtil.isLimitless());
        if (vibratorUtil.getContinueTime()!=0){
            binding.activityVibratorTimeInput.setText(String.valueOf(vibratorUtil.getContinueTime()));
        }
        binding.activityVibratorRepeatSwitch.setChecked(vibratorUtil.isRepeat());
        if (vibratorUtil.getVibratorTimeInPeriod()!=0){
            binding.activityVibratorRepeatStartInput.setText(String.valueOf(vibratorUtil.getVibratorTimeInPeriod()));
        }
        if (vibratorUtil.getWaitTimeInPeriod()!=0){
            binding.activityVibratorRepeatStopInput.setText(String.valueOf(vibratorUtil.getWaitTimeInPeriod()));
        }
    }

    private void setTurnOnOff(){
        vibratorUtil.setOnStopListener(()->{
            binding.activityVibratorSwitch.setSelected(false);
        });
        binding.activityVibratorSwitch.setOnClickListener(view -> {
            view.setSelected(vibratorUtil.setTurnOn(!view.isSelected()));
        });
        binding.activityVibratorSwitch.setSvgColor(ResUtil.getColorStateList(ThemeUtil.getNightModeSwitch()?R.color.color_turn_on_off_night:R.color.color_trun_on_off_day));
    }

    private void setLimitless(){
        binding.activityVibratorLimitlessSwitch.setOnCheckedChangeListener((view, checked) -> {
            vibratorUtil.setLimitless(checked);
            binding.activityVibratorTimeInput.setEnabled(!checked);
        });
        binding.activityVibratorTimeInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().isEmpty()) return;
                vibratorUtil.setContinueTime(Long.valueOf(editable.toString()));
            }
        });
    }

    private void setRepeat(){
        binding.activityVibratorRepeatSwitch.setOnCheckedChangeListener((view, checked) -> {
            vibratorUtil.setRepeat(checked);
        });
        binding.activityVibratorRepeatStartInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (vibratorUtil.isTurnOn()){
                    vibratorUtil.setTurnOn(false);
                    binding.activityVibratorSwitch.setSelected(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (vibratorUtil.isTurnOn()){
                    vibratorUtil.setTurnOn(false);
                    binding.activityVibratorSwitch.setSelected(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().isEmpty()) return;
                vibratorUtil.setVibratorTimeInPeriod(Long.valueOf(editable.toString()));
            }
        });
        binding.activityVibratorRepeatStopInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (vibratorUtil.isTurnOn()){
                    vibratorUtil.setTurnOn(false);
                    binding.activityVibratorSwitch.setSelected(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (vibratorUtil.isTurnOn()){
                    vibratorUtil.setTurnOn(false);
                    binding.activityVibratorSwitch.setSelected(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().isEmpty()) return;
                vibratorUtil.setWaitTimeInPeriod(Long.valueOf(editable.toString()));
            }
        });
    }

}
