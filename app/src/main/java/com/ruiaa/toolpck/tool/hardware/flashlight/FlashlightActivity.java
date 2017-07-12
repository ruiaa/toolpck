package com.ruiaa.toolpck.tool.hardware.flashlight;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.SeekBar;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.base.ToolbarActivity;
import com.ruiaa.toolpck.databinding.ActivityFlashlightBinding;
import com.ruiaa.toolpck.util.ResUtil;
import com.ruiaa.toolpck.util.ThemeUtil;

public class FlashlightActivity extends ToolbarActivity{

    private ActivityFlashlightBinding binding;
    private FlashlightUtil flashlightUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //themeResId=R.style.AppTheme_Night;
        flashlightUtil=FlashlightUtil.getInstance();
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_flashlight);

        fixKeyboardAdjustResizeNoEffect();

        setToolbar();
        setTurnOnOff();
        setBrightness();
        setTwinkle();
        setSOS();
    }

    @Override
    protected void onDestroy() {
        flashlightUtil.release();
        super.onDestroy();
    }

    private void setToolbar(){
        initToolbar();
        setToolbarLeftBack();
        setTitle(R.string.flashlight);
    }

    private void setTurnOnOff(){
        binding.activityFlashlightLightSwitch.setSvgColor(ResUtil.getColorStateList(ThemeUtil.getNightModeSwitch()?R.color.color_turn_on_off_night:R.color.color_trun_on_off_day));
        binding.activityFlashlightLightSwitch.setSelected(flashlightUtil.isTurnOn());
        binding.activityFlashlightLightSwitch.setOnClickListener(view -> {
            binding.activityFlashlightBrightnessBar.setEnabled(!view.isSelected());
            if (!view.isSelected()){
                binding.activityFlashlightBrightnessBar.setProgress(100);
            }
            view.setSelected(flashlightUtil.setTurnOn(!view.isSelected()));
        });
    }

    private void setBrightness(){
        binding.activityFlashlightBrightnessBar.setEnabled(flashlightUtil.isTurnOn());
        binding.activityFlashlightBrightnessBar.setMax(100);
        binding.activityFlashlightBrightnessBar.setProgress(flashlightUtil.getBrightness());
        binding.activityFlashlightBrightnessBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                flashlightUtil.setBrightness(seekBar.getProgress());
            }
        });
    }

    private void setTwinkle(){

        binding.activityFlashlightTwinkleSwitch.setChecked(flashlightUtil.isTwinkle());
        binding.activityFlashlightTwinkleSwitch.setOnCheckedChangeListener((compoundButton, b) -> {
            flashlightUtil.setTwinkle(b);
            if (!b){
                binding.activityFlashlightLightSwitch.setSelected(false);
                flashlightUtil.setTurnOn(false);
            }
        });
        binding.activityFlashlightTwinkleBrightBar.setMax(100);
        binding.activityFlashlightTwinkleBrightBar.setProgress((int)flashlightUtil.getBrightTimeLength());
        binding.activityFlashlightTwinkleBrightBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                flashlightUtil.setBrightTimeLength(seekBar.getProgress());
            }
        });
        binding.activityFlashlightTwinkleDarkBar.setMax(100);
        binding.activityFlashlightTwinkleDarkBar.setProgress((int)flashlightUtil.getDarkTimeLength());
        binding.activityFlashlightTwinkleDarkBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                flashlightUtil.setDarkTimeLength(seekBar.getProgress());
            }
        });
    }

    private void setSOS(){
        binding.activityFlashlightSosSwitch.setChecked(flashlightUtil.isSOS());
        binding.activityFlashlightSosSwitch.setOnCheckedChangeListener((compoundButton, b) -> {
            flashlightUtil.setSOS(b);
            binding.activityFlashlightLightSwitch.setSelected(b);
            binding.activityFlashlightLightSwitch.setEnabled(!b);
            binding.activityFlashlightTwinkleSwitch.setEnabled(!b);
        });
    }
}
