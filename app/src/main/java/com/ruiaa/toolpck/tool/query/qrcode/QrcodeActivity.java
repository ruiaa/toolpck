package com.ruiaa.toolpck.tool.query.qrcode;

import android.os.Bundle;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.base.ToolbarActivity;

public class QrcodeActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        setToolbar();
    }

    private void setToolbar(){
        initToolbar();
        setToolbarLeftBack();
        setTitle(R.string.qrcode);
    }
}

