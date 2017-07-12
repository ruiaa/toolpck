package com.ruiaa.toolpck.tool.hardware.soundrecord;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.tool.ToolInfo;

/**
 * Created by ruiaa on 2016/12/24.
 */

public class SoundRecordToolInfo extends ToolInfo {
    @Override
    public Class<?> getActivityClass() {
        return SoundRecordActivity.class;
    }

    @Override
    public int getIconResId() {
        return R.drawable.soundrecord;
    }

    @Override
    public int getLabelResId() {
        return R.string.sound_record;
    }
}
