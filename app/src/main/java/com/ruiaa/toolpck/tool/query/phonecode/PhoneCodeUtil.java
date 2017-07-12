package com.ruiaa.toolpck.tool.query.phonecode;

import android.content.Context;

import com.ruiaa.toolpck.util.LogUtil;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ruiaa on 2016/12/21.
 */

public class PhoneCodeUtil {

    private OkHttpClient okHttpClient;

    public PhoneCodeUtil(Context context) {
        okHttpClient = new OkHttpClient();
    }



    private boolean isPhoneCode(String value) {
        if (value == null || value.isEmpty()) return false;

        String regExp = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$";

        Pattern p = Pattern.compile("^[1][34578][0-9]\\d{4,8}$");

        Matcher m = p.matcher(value);

        return m.find();
    }

    public boolean getPhoneCodeInfo(String code, final OnSucceedListener onSucceedListener, final OnFailedListener onFailedListener){
        if (!isPhoneCode(code)){
            return false;
        }else {
            Request request=new Request.Builder()
                    .url("http://www.ip138.com:8080/search.asp?mobile="+code+"&action=mobile")
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    if (onFailedListener!=null) onFailedListener.onFail();
                    LogUtil.e("onFailure####",e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (onSucceedListener!=null){
                        String htmlStr=new String(response.body().bytes(), "gb2312");
                        Elements elements=Jsoup.parse(htmlStr).select(".tdc2");
                        if (elements.size()>=5){
                            String location=elements.get(1).text();
                            String codeType=elements.get(2).text();
                            String areaCode=elements.get(3).text();
                            String postCode=elements.get(4).text();
                            if (postCode!=null&&postCode.length()>=6) {
                                postCode=postCode.substring(0,6);
                            }
                            onSucceedListener.onSucceed(location,codeType,areaCode,postCode);
                        }
                        response.close();
                    }
                }
            });
            return true;
        }
    }

    public interface OnSucceedListener{
        void onSucceed(String location,String codeType,String areaCode,String postCode);
    }

    public interface OnFailedListener{
        void onFail();
    }


}
