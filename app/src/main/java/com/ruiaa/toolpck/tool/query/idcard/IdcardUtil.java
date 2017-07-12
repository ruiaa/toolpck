package com.ruiaa.toolpck.tool.query.idcard;

import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ruiaa on 2016/12/26.
 */

public class IdcardUtil {

    //http://apis.juhe.cn/idcard/index?key=您申请的KEY&cardno=330326198903081211&dtype=json
    //http://apis.juhe.cn/idcard/leak?key=您申请的KEY&cardno=330326198903081211&dtype=json
    private static final String key="7e11b3d89c540cb1b1c45133b27f8b62";

    private OkHttpClient okHttpClient;

    public IdcardUtil(){
        okHttpClient=new OkHttpClient();
    }

    public void queryInfo(String cardnum,final OnFinish onFinish){
        if (cardnum==null||cardnum.trim().isEmpty()) return;
        if (onFinish==null) return;
        Request request=new Request.Builder()
                .url("http://apis.juhe.cn/idcard/index?key="+key+"&cardno="+cardnum+"&dtype=json")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onFinish.onfinish(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson=new Gson();
                String s=response.body().string();
               // LogUtil.i("onResponse####"+s);
                QueryResult queryResult=gson.fromJson(s,QueryResult.class);
                onFinish.onfinish(queryResult);
            }
        });
    }

    public void queryLeak(String cardnum,final OnFinish onFinish){
        if (cardnum==null||cardnum.trim().isEmpty()) return;
        if (onFinish==null) return;
        Request request=new Request.Builder()
                .url("http://apis.juhe.cn/idcard/leak?key="+key+"&cardno="+cardnum+"&dtype=json")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
               // onFinish.onfinish(null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s=response.body().string();
               // LogUtil.i("onResponse####"+s);
            }
        });
    }

    public static class QueryResult{
        @SerializedName("resultcode")
        public String resultCode;
        @SerializedName("reason")
        public String reason;
        @SerializedName("result")
        public IdcardInfo idcardInfo;
    }
    public static class IdcardInfo{
        @SerializedName("area")
        public String area;
        @SerializedName("sex")
        public String sex;
        @SerializedName("birthday")
        public String birthday;
    }

   public interface OnFinish{
        void onfinish(@Nullable QueryResult queryResult);
    }
}
