package com.ruiaa.toolpck.tool.query.express;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.utils.NetworkUtils;
import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.base.ToolbarActivity;
import com.ruiaa.toolpck.common.DialogHelper;
import com.ruiaa.toolpck.databinding.ActivityExpressBinding;
import com.ruiaa.toolpck.util.LogUtil;
import com.ruiaa.toolpck.util.ResUtil;
import com.ruiaa.toolpck.util.StringStyles;

public class ExpressActivity extends ToolbarActivity {

    private ActivityExpressBinding binding;
    private ExpressUtil expressUtil;
    private String expressNumber;
    private DialogHelper dialogHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_express);

        expressUtil=new ExpressUtil();
        dialogHelper=new DialogHelper(this);

        setToolbar();
        setListener();
    }

    private void setToolbar(){
        initToolbar();
        setToolbarLeftBack();
        setTitle(R.string.express_query);
    }

    private void setListener(){
        binding.expressQuery.setOnClickListener(v -> {
            expressNumber=binding.expressInput.getText().toString();
            if (expressNumber.trim().isEmpty()) return;
            if (NetworkUtils.isAvailableByPing()){
                binding.expressResult.setText("");
                queryShipper(expressNumber);
                binding.expressQuery.setEnabled(false);
            }else {
                dialogHelper.showTextPositiveDialog(ResUtil.getString(R.string.no_network)+" , "+ResUtil.getString(R.string.please_open_network));
            }
        });
    }

    private void queryShipper(final String expNum){
        if (expNum==null||expNum.trim().isEmpty()) return;
        new Thread(()->{
            try{
                ExpressUtil.ExpressOrderForShipper shipper=expressUtil.getExpressShipper(expNum);
                if (shipper==null||!shipper.success||shipper.shippers==null||shipper.shippers.isEmpty()){
                    onFail(ResUtil.getString(R.string.query_fail_retry));
                }else {
                    if (shipper.shippers.size()==1){
                        queryTrack(shipper.shippers.get(0).shipperCode,expNum);
                    }else {
                        selectShipper(shipper);
                    }
                }
            }catch (Exception e){
                LogUtil.e("queryShipper####",e);
            }
        }).start();
    }

    private void selectShipper(@NonNull final ExpressUtil.ExpressOrderForShipper shipper){
        ExpressActivity.this.runOnUiThread(()->{
            new MaterialDialog.Builder(ExpressActivity.this)
                    .title(R.string.select_shipper)
                    .items(shipper.shippers)
                    .itemsCallbackSingleChoice(0,((dialog, itemView, which, text) -> {
                        if (shipper.shippers.size()>which) {
                            binding.expressQuery.setEnabled(false);
                            new Thread(() -> {
                                try {
                                    queryTrack(shipper.shippers.get(which).shipperCode,shipper.logisticCode);
                                } catch (Exception e) {
                                    LogUtil.e("selectShipper####", e);
                                }
                            }).start();
                        }
                        return true;
                    }))
                    .positiveText(R.string.sure)
                    .negativeText(R.string.cancel)
                    .show();
            binding.expressQuery.setEnabled(true);
        });
    }

    private void queryTrack(String shipperCode,String expNum)throws Exception{
        ExpressUtil.ExpressOrderForTrack tracks=expressUtil.getExpressTrack(shipperCode, expNum);
        if (tracks!=null){
            onSuccess(tracks);
        }
    }

    private void onSuccess(final ExpressUtil.ExpressOrderForTrack tracks){
        ExpressActivity.this.runOnUiThread(()->{
            if (!tracks.success){
                onFail(ResUtil.getString(R.string.query_fail_retry));
            }else if (tracks.traces.isEmpty()){
                onFail(tracks.reason);
            }else {
                StringStyles.RichText richText=new StringStyles.RichText();
                richText.append("\n");
                for(ExpressUtil.ExpressTrack track:tracks.traces){
                    richText.append("  "+track.time+"\n",ResUtil.getThemeColor(ExpressActivity.this,R.attr.colorPrimary));
                    richText.append(track.station+"\n");
                    if (track.remark!=null&&!track.remark.trim().isEmpty()){
                        richText.append(track.remark+"\n");
                    }
                    richText.append("\n");
                }

                binding.expressResult.setText(richText.build());
            }
            binding.expressQuery.setEnabled(true);
        });
    }

    private void onFail(String msg){
        ExpressActivity.this.runOnUiThread(()->{
            dialogHelper.showTextPositiveDialog(msg);
            binding.expressQuery.setEnabled(true);
        });
    }
}
