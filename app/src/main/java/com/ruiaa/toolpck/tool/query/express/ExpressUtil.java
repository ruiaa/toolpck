package com.ruiaa.toolpck.tool.query.express;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.ruiaa.toolpck.util.EncodeUtil;
import com.ruiaa.toolpck.util.LogUtil;

import java.net.URLEncoder;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by ruiaa on 2016/12/23.
 */

public class ExpressUtil {

    private static final String KEY="02dca700-3760-4d43-804f-37eb001a0ed1";
    private static final String ID="1272966";
    private static final String EXPRESS_SHIPPER_URL ="http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx";
    private static final String EXPRESS_TRACK_URL="http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx";


    private OkHttpClient okHttpClient;

    public ExpressUtil(){
        okHttpClient=new OkHttpClient();
        Observable.just(1)
                .observeOn(Schedulers.io())
                .subscribe(integer -> {
                    try {
                        //getExpressShipper("47111872992");
                        //getExpressShipper("423085939816");
                        //getExpressShipper("46350306426");
                    }catch (Exception e){
                        LogUtil.e("ExpressUtil####",e);
                    }
                });
    }

    public ExpressOrderForShipper getExpressShipper(String expNumber)throws Exception{
        String requestData= "{'LogisticCode':'" + expNumber + "'}";
        FormBody formBody=new FormBody.Builder()
                .add("RequestData", URLEncoder.encode(requestData, "UTF-8"))
                .add("EBusinessID",ID)
                .add("RequestType", "2002")
                .add("DataSign", URLEncoder.encode(EncodeUtil.encodeBase64(EncodeUtil.encodeMD5(requestData+KEY)), "UTF-8"))
                .add("DataType", "2")
                .build();
        Request request=new Request.Builder()
                .url(EXPRESS_SHIPPER_URL)
                .addHeader("accept", "*/*")
                .addHeader("connection", "Keep-Alive")
                .addHeader("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(formBody)
                .build();
        Response response=okHttpClient.newCall(request).execute();
        String result=response.body().string();
        //LogUtil.i("getExpressShipper####"+result);
        Gson gson=new Gson();
        ExpressOrderForShipper forShipper=gson.fromJson(result,ExpressOrderForShipper.class);
//        forShipper.shippers.addAll(forShipper.shippers);
//        forShipper.shippers.addAll(forShipper.shippers);
        response.close();
        //getExpressTrack(forShipper.shippers.get(0).shipperCode,expNumber);
        return forShipper;
    }

    public ExpressOrderForTrack getExpressTrack(String shipperCode,String expNumber) throws Exception{
        String requestData= "{'OrderCode':'','ShipperCode':'" + shipperCode + "','LogisticCode':'" + expNumber + "'}";
        //LogUtil.i("getExpressTrack####"+requestData);
        FormBody formBody=new FormBody.Builder()
                .add("RequestData", URLEncoder.encode(requestData, "UTF-8"))
                .add("EBusinessID",ID)
                .add("RequestType", "1002")
                .add("DataSign", URLEncoder.encode(EncodeUtil.encodeBase64(EncodeUtil.encodeMD5(requestData+KEY)), "UTF-8"))
                .add("DataType", "2")
                .build();
        Request request=new Request.Builder()
                .url(EXPRESS_TRACK_URL)
                .addHeader("accept", "*/*")
                .addHeader("connection", "Keep-Alive")
                .addHeader("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(formBody)
                .build();
        Response response=okHttpClient.newCall(request).execute();
        String s=response.body().string();
        //LogUtil.i("getExpressTrack####"+s);
        Gson gson=new Gson();
        ExpressOrderForTrack forTrack=gson.fromJson(s,ExpressOrderForTrack.class);
        response.close();
        return forTrack;
    }


    public static class ExpressOrderForShipper{
        @SerializedName("EBusinessID")
        public String eEBusinessID;
        @SerializedName("Success")
        public boolean success;
        @SerializedName("LogisticCode")
        public String logisticCode;
        @SerializedName("Shippers")
        public List<ExpressShipper> shippers;
    }

    public static class ExpressShipper{
        @SerializedName("ShipperCode")
        public String shipperCode;
        @SerializedName("ShipperName")
        public String shipperName;

        public String toString(){
            return shipperName;
        }
    }

    public static class ExpressOrderForTrack{
        @SerializedName("EBusinessID")
        public String eEBusinessID;
        @SerializedName("ShipperCode")
        public String shipperCode;
        @SerializedName("Success")
        public boolean success;
        @SerializedName("Reason")
        public String reason;
        @SerializedName("LogisticCode")
        public String logisticCode;
        @SerializedName("State")
        public String state;  //2-在途中,3-签收,4-问题件
        @SerializedName("Traces")
        public List<ExpressTrack> traces;
    }

    public static class ExpressTrack{
        @SerializedName("AcceptTime")
        public String time;
        @SerializedName("AcceptStation")
        public String station;
        @SerializedName("Remark")
        public String remark;
    }
}
