package com.ruiaa.toolpck.base;

import android.graphics.Color;
import android.support.annotation.DrawableRes;
import android.support.annotation.MenuRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.util.ThemeUtil;


/**
 * Created by ruiaa on 2016/10/21.
 */

public abstract class ToolbarActivity extends BaseActivity {

    protected Toolbar toolbar=null;
    protected boolean toolbarIsHidden = false;

    /*
     * toolbar
     */
    protected void initToolbar() {
        if (toolbar==null){
            toolbar = (Toolbar)findViewById(R.id.toolbar);
        }
        toolbar.setTitleTextColor(Color.WHITE);
        if (ThemeUtil.getNightModeSwitch()){
            toolbar.setBackgroundResource(R.color.night_toolbar_bg);
        }
    }

    protected void hideOrShowToolbar() {
        toolbar.animate()
                .translationY(toolbarIsHidden ? 0 : -toolbar.getHeight())
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        toolbarIsHidden = !toolbarIsHidden;
    }

    public void setTitle(String s) {
        toolbar.setTitle(s);
    }

    public void setTitle(@StringRes int title){
        toolbar.setTitle(title);
    }

    public void setToolbarLeft(@DrawableRes int iconId,View.OnClickListener onClickListener){
        toolbar.setNavigationIcon(iconId);
        if (onClickListener!=null) {
            toolbar.setNavigationOnClickListener(onClickListener);
        }
    }

    public void setToolbarLeftBack(){
        setToolbarLeft(R.drawable.md_nav_back,view -> {
            ToolbarActivity.this.onBackPressed();
        });
    }

    public void setToolbarRight(@MenuRes int menuResId, Toolbar.OnMenuItemClickListener onMenuItemClickListener){
        toolbar.inflateMenu(menuResId);
        if (onMenuItemClickListener!=null){
            toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
        }
    }


}
