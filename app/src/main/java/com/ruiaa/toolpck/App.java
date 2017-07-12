package com.ruiaa.toolpck;

import android.app.Application;
import android.content.Context;

import com.ruiaa.toolpck.drawables.SVGLoader;
import com.ruiaa.toolpck.util.AppUtil;
import com.ruiaa.toolpck.util.ConvertUtil;
import com.ruiaa.toolpck.util.KeyStorage;
import com.ruiaa.toolpck.util.ResUtil;
import com.ruiaa.toolpck.util.StringStyles;
import com.ruiaa.toolpck.util.ToastUtil;

/**
 * Created by ruiaa on 2016/12/7.
 */

public class App extends Application {

    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;

        //util
        KeyStorage.init(appContext);
        ToastUtil.register(appContext);
        ResUtil.register(appContext);
        StringStyles.register(appContext);
        ConvertUtil.register(appContext);
        AppUtil.register(appContext);

        //svg
        SVGLoader.load(this);
    }
}
