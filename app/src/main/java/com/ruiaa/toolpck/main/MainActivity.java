package com.ruiaa.toolpck.main;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.base.ToolbarActivity;
import com.ruiaa.toolpck.databinding.ActivityMainBinding;
import com.ruiaa.toolpck.tool.ToolInfo;
import com.ruiaa.toolpck.util.KeyStorage;
import com.ruiaa.toolpck.util.LogUtil;
import com.ruiaa.toolpck.util.ResUtil;
import com.ruiaa.toolpck.util.ThemeUtil;

import java.util.ArrayList;

import static com.ruiaa.toolpck.tool.ToolInfo.AllTool.ALL_TOOL;


public class MainActivity extends ToolbarActivity {

    private static int Select_Position =-1;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setToolbar();
        setPager();
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*if (themeResId != ThemeUtil.getCurrentThemeRes()) {
            binding.activityMainTab.setVisibility(View.GONE);
            binding.activityMainPager.setVisibility(View.GONE);
            restart();
        }*/
        LogUtil.i("saveStorage####"+ KeyStorage.get(ALL_TOOL));
        LogUtil.i("saveStorage####"+KeyStorage.contains(ALL_TOOL));
    }

    @Override
    protected void onPause() {
        Select_Position=binding.activityMainPager.getCurrentItem();
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        ToolInfo.AllTool.saveStorage();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setToolbar() {
        initToolbar();
        setToolbarRight(R.menu.menu_main_activity, item -> {
            switch (item.getItemId()) {
                case R.id.menu_main_activity_setting: {
                    startActivity(new Intent(MainActivity.this, SettingActivity.class));
                    finish();
                    return true;
                }
            }
            return false;
        });
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(Color.WHITE);
    }

    private void setPager() {
        TabLayout tabLayout = binding.activityMainTab;
        ViewPager viewPager = binding.activityMainPager;

        if (ThemeUtil.getNightModeSwitch()){
            tabLayout.setBackgroundColor(ResUtil.getColor(R.color.night_toolbar_bg));
        }

        ArrayList<ToolInfo.Group> list = ToolInfo.AllTool.getInstance().getAllGroup();

        if (Select_Position <0){
            if (list.contains(ToolInfo.AllTool.getInstance().getOftenGroup())) {
                Select_Position = list.indexOf(ToolInfo.AllTool.getInstance().getOftenGroup());
            }else {
                Select_Position =0;
            }
        }
        ToolListPagerAdapter adapter = new ToolListPagerAdapter(getFragmentManager(), list);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        viewPager.setCurrentItem(Select_Position);

    }

    private static class ToolListPagerAdapter extends FragmentPagerAdapter {

        ArrayList<ToolInfo.Group> list;

        public ToolListPagerAdapter(FragmentManager fm, ArrayList<ToolInfo.Group> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return ToolListFragment.newInstance(list.get(position).getGroupType());
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public String getPageTitle(int position) {
            return list.get(position).getLabel();
        }
    }

}
