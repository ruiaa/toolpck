package com.ruiaa.toolpck.tool.query.idcard;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.blankj.utilcode.utils.NetworkUtils;
import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.base.ToolbarActivity;
import com.ruiaa.toolpck.common.DialogHelper;
import com.ruiaa.toolpck.databinding.ActivityIdcardBinding;
import com.ruiaa.toolpck.util.ResUtil;

public class IdcardActivity extends ToolbarActivity {

    private ActivityIdcardBinding binding;
    private IdcardUtil idcardUtil;
    private DialogHelper dialogHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_idcard);

        idcardUtil=new IdcardUtil();
        dialogHelper=new DialogHelper(this);

        setToolbar();
        setListener();
    }

    private void setToolbar(){
        initToolbar();
        setToolbarLeftBack();
        setTitle(R.string.idcard);
    }

    private void onSuccess(final IdcardUtil.IdcardInfo idcardInfo){
        IdcardActivity.this.runOnUiThread(()->{
            binding.idcardQuery.setEnabled(true);
            binding.setIdcardLocation(idcardInfo.area);
            binding.setIdcardSex(idcardInfo.sex);
            binding.setIdcardBirth(idcardInfo.birthday);
        });
    }

    private void onFail(final String msg){
        IdcardActivity.this.runOnUiThread(()->{
            binding.idcardQuery.setEnabled(true);
            dialogHelper.showTextPositiveDialog(msg);
        });
    }

    private void setListener(){
        binding.idcardQuery.setOnClickListener(v -> {
            String s=binding.idcardQueryInput.getText().toString();
            if (s.length()==15||s.length()==18){
                binding.idcardQuery.setEnabled(false);
                binding.setIdcardBirth("");
                binding.setIdcardLocation("");
                binding.setIdcardSex("");
                idcardUtil.queryInfo(s,queryResult -> {
                    if (queryResult==null){
                        if (NetworkUtils.isAvailableByPing()){
                            onFail(ResUtil.getString(R.string.query_fail_retry));
                        }else {
                            onFail(ResUtil.getString(R.string.no_network_please_open_network));
                        }
                    }else {
                        if (queryResult.resultCode.equals("200")){
                            onSuccess(queryResult.idcardInfo);
                        }else {
                            if (queryResult.idcardInfo!=null){
                                onSuccess(queryResult.idcardInfo);
                            }
                            onFail(queryResult.reason);
                        }
                    }
                });
            }else {
                dialogHelper.showTextPositiveDialog(R.string.idcard_input_hint);
            }
        });
    }
}
