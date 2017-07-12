package com.ruiaa.toolpck.tool.hardware;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.tool.ToolInfo;
import com.ruiaa.toolpck.tool.hardware.flashlight.FlashLightToolInfo;
import com.ruiaa.toolpck.tool.hardware.soundrecord.SoundRecordToolInfo;
import com.ruiaa.toolpck.tool.hardware.vibrate.VibratorToolInfo;

/**
 * Created by ruiaa on 2016/12/10.
 */

public class HardwareGroup extends ToolInfo.Group {
    public HardwareGroup(){
        super();

        registerChildToolInfo(this,new FlashLightToolInfo());
        registerChildToolInfo(this,new VibratorToolInfo());
        registerChildToolInfo(this,new SoundRecordToolInfo());
    }

    @Override
    public int getLabelResId() {
        return R.string.mobile_phone;
    }
}
