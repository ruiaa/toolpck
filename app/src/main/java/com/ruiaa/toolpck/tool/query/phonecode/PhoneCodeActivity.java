package com.ruiaa.toolpck.tool.query.phonecode;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.blankj.utilcode.utils.NetworkUtils;
import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.base.ToolbarActivity;
import com.ruiaa.toolpck.common.DialogHelper;
import com.ruiaa.toolpck.databinding.ActivityPhoneCodeBinding;
import com.ruiaa.toolpck.util.AppUtil;
import com.ruiaa.toolpck.util.ResUtil;

public class PhoneCodeActivity extends ToolbarActivity {

    private ActivityPhoneCodeBinding binding;
    private PhoneCodeUtil.OnSucceedListener onSucceedListener;
    private PhoneCodeUtil.OnFailedListener onFailedListener;
    private PhoneCodeUtil phoneCodeUtil;
    private DialogHelper dialogHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_phone_code);
        phoneCodeUtil = new PhoneCodeUtil(this);
        dialogHelper=new DialogHelper(this);

        setToolbar();
        setListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppUtil.showKeyBoard(binding.phoneCodeInput);
    }

    private void setToolbar() {
        initToolbar();
        setToolbarLeftBack();
        setTitle(R.string.phone_code_query);
    }

    private void setListener() {
        onSucceedListener = ((location, codeType, areaCode, postCode) -> {
            binding.setPhoneLocation(location);
            binding.setPhoneCardType(codeType);
            binding.setPhoneAreaCode(areaCode);
            binding.setPhonePostCode(postCode);
            PhoneCodeActivity.this.runOnUiThread(()-> {
                binding.phoneCodeInput.setEnabled(true);
            });
        });
        onFailedListener = () -> {
            PhoneCodeActivity.this.runOnUiThread(()->{
                binding.phoneCodeInput.setEnabled(true);
                if (NetworkUtils.isAvailableByPing()){
                    dialogHelper.showTextPositiveDialog(ResUtil.getString(R.string.query_fail)+" , "+ResUtil.getString(R.string.try_again));
                }else {
                    dialogHelper.showTextPositiveDialog(ResUtil.getString(R.string.no_network)+" , "+ResUtil.getString(R.string.please_open_network));
                }

            });
        };
        binding.phoneCodeCommit.setOnClickListener(v -> {
            if (binding.phoneCodeInput.getText().length() < 7) {
                dialogHelper.showTextPositiveDialog(R.string.phone_code_query_input_error);
            } else {
                binding.phoneCodeInput.setEnabled(false);
                phoneCodeUtil.getPhoneCodeInfo(binding.phoneCodeInput.getText().toString(), onSucceedListener, onFailedListener);
            }
        });

    }

}
