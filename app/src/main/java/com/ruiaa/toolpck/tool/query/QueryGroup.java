package com.ruiaa.toolpck.tool.query;

import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.tool.ToolInfo;
import com.ruiaa.toolpck.tool.query.barcode.BarcodeToolInfo;
import com.ruiaa.toolpck.tool.query.commonphone.CommonPhoneToolInfo;
import com.ruiaa.toolpck.tool.query.express.ExpressToolInfo;
import com.ruiaa.toolpck.tool.query.idcard.IdcardToolInfo;
import com.ruiaa.toolpck.tool.query.phonecode.PhoneCodeToolInfo;
import com.ruiaa.toolpck.tool.query.pm25.Pm25ToolInfo;
import com.ruiaa.toolpck.tool.query.qrcode.QrcodeToolInfo;
import com.ruiaa.toolpck.tool.query.weather.WeatherToolInfo;
import com.ruiaa.toolpck.tool.query.zodiac.ZodiacToolInfo;

/**
 * Created by ruiaa on 2016/12/10.
 */

public class QueryGroup extends ToolInfo.Group {
    public QueryGroup(){
        super();

        registerChildToolInfo(this,new PhoneCodeToolInfo());
        registerChildToolInfo(this,new CommonPhoneToolInfo());
        registerChildToolInfo(this,new ExpressToolInfo());
        registerChildToolInfo(this,new BarcodeToolInfo());
        registerChildToolInfo(this,new QrcodeToolInfo());
        registerChildToolInfo(this,new WeatherToolInfo());
        registerChildToolInfo(this,new Pm25ToolInfo());
        registerChildToolInfo(this,new ZodiacToolInfo());
        registerChildToolInfo(this,new IdcardToolInfo());
    }

    @Override
    public int getLabelResId() {
        return R.string.query;
    }
}
