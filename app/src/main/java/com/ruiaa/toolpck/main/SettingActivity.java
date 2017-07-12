package com.ruiaa.toolpck.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.rey.material.widget.Switch;
import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.base.ToolbarActivity;
import com.ruiaa.toolpck.databinding.ActivitySettingBinding;
import com.ruiaa.toolpck.util.ResUtil;
import com.ruiaa.toolpck.util.ThemeUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends ToolbarActivity implements ColorChooserDialog.ColorCallback {

    @BindView(R.id.setting_night_theme_switch)
    Switch nightThemeSwitch;

    private ActivitySettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_setting);
        ButterKnife.bind(this);

        setToolbar();
        setNightModeSwitch();
    }

    private void setToolbar(){
        initToolbar();
        //setToolbarLeftBack();
        setToolbarLeft(R.drawable.md_nav_back,view -> {
            startActivity(new Intent(SettingActivity.this,MainActivity.class));
            finish();
        });
    }

    private void setNightModeSwitch(){
        nightThemeSwitch.setChecked(ThemeUtil.getNightModeSwitch());
        nightThemeSwitch.setOnCheckedChangeListener((view, checked) -> {
            view.postDelayed(()->{
                changeNightMode(checked);
            },250);
        });
    }


    @Override
    public void onColorSelection(@NonNull ColorChooserDialog dialog, @ColorInt int selectedColor) {
        dialog.dismiss();
        if (selectedColor != ResUtil.getThemeColor(this,R.attr.colorPrimary)){
            binding.settingTheme.postDelayed(()->changeTheme(selectedColor),100L);
        }
    }

    @OnClick(R.id.setting_night_theme)
    public void onNightClick(View view) {
        changeNightMode(!nightThemeSwitch.isChecked());
    }

    @OnClick(R.id.setting_theme)
    public void onThemeClick(View view){
        new ColorChooserDialog.Builder(this, R.string.theme)
                .customColors(R.array.colors, null)
                .doneButton(R.string.sure)
                .cancelButton(R.string.cancel)
                .allowUserColorInput(false)
                .allowUserColorInputAlpha(false)
                .show();
    }

    @OnClick(R.id.setting_feedback)
    public void onFeedbackClick(View view){

    }

    @OnClick(R.id.setting_about)
    public void onAboutClick(View view){

    }

    @OnClick(R.id.setting_update)
    public void onCheckUpdate(View view){

    }

}
