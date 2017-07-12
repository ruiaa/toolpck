package com.ruiaa.toolpck.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.util.AndroidKeyboardAdjustResizeNoWork;
import com.ruiaa.toolpck.util.RxManager;
import com.ruiaa.toolpck.util.ThemeUtil;

import rx.Subscription;

/**
 * Created by ruiaa on 2016/10/21.
 */

public class BaseActivity extends AppCompatActivity {

    protected int themeResId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (themeResId == -1) {
            themeResId= ThemeUtil.setCurrentTheme(this);
        }
        setTheme(themeResId);

        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    /*
     *  after setContent();
     *  keyboard adjustResize
     */
    protected void fixKeyboardAdjustResizeNoEffect() {
        AndroidKeyboardAdjustResizeNoWork.assistActivity(this);
    }

    /*
     *
     * 设置主题
     *
     */
    public void changeTheme(int colorPrimary) {
        ThemeUtil.saveThemeSelected(colorPrimary);
        //themeResId=ThemeUtil.colorPrimary2ThemeRes(colorPrimary);
        //setTheme(themeResId);

        restart();
    }

    public void changeNightMode(boolean n){
        ThemeUtil.saveNightModeSwitch(n);
        restart();
    }

    public void restart(){
        Intent intent=getIntent();
        finish();
        //overridePendingTransition(R.anim.activity_in_alpha, R.anim.activity_out_alpha);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_in_alpha, R.anim.activity_out_alpha);
    }


    /*
     *
     * 管理RxJava的subscription的释放
     *
     */
    private RxManager rxManager = new RxManager();

    public void addSubscription(Subscription s) {
        rxManager.add(s);
    }

    public void clearSubscription() {
        rxManager.clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rxManager.clear();
    }
}
