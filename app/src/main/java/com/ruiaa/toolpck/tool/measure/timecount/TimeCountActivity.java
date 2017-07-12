package com.ruiaa.toolpck.tool.measure.timecount;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.ruiaa.toolpck.BR;
import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.base.ToolbarActivity;
import com.ruiaa.toolpck.common.adapter.SimpleRecyclerAdapter;
import com.ruiaa.toolpck.databinding.ActivityTimeCountBinding;
import com.ruiaa.toolpck.util.LogUtil;

import java.util.ArrayList;

public class TimeCountActivity extends ToolbarActivity {

    private ActivityTimeCountBinding binding;
    private TimeCountUtil timeCountUtil;
    private boolean counting=false;
    private boolean pauseing=false;
    private ArrayList<Long> recordTimeList;
    private SimpleRecyclerAdapter<Long> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_time_count);

        timeCountUtil =new TimeCountUtil(this,true);
        recordTimeList=new ArrayList<>();

        binding.setCounting(false);
        binding.setPauseing(false);
        binding.setCountTime(timeCountUtil.countTimeMillis);

        setToolbar();
        setListener();
        initRecordList();
    }

    @Override
    protected void onDestroy() {
        timeCountUtil.release();
        super.onDestroy();
    }

    private void setToolbar(){
        initToolbar();
        setToolbarLeftBack();
        setTitle(R.string.time_count);
    }

    private void initRecordList(){
        adapter=new SimpleRecyclerAdapter<>(
                this,
                R.layout.item_time_count_record,
                recordTimeList,
                ((holder, position, model) -> {
                    holder.getBinding().setVariable(BR.timeRecordTime, TimeCountUtil.getTimeMinSecMil(model));
                    if (position==0){
                        holder.getBinding().setVariable(BR.timeRecordIndex,"01");
                        holder.getBinding().setVariable(BR.timeRecordIncrease,"+"+ TimeCountUtil.getTimeMinSecMil(model));
                    }else {
                        holder.getBinding().setVariable(BR.timeRecordIncrease,"+"+ TimeCountUtil.getTimeMinSecMil(model-recordTimeList.get(position-1)));
                        if (position<9){
                            holder.getBinding().setVariable(BR.timeRecordIndex,"0"+(position+1));
                        }else {
                            holder.getBinding().setVariable(BR.timeRecordIndex,String.valueOf(position+1));
                        }
                    }
                })
        );
        binding.timeCountListRecord.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true));
        binding.timeCountListRecord.setAdapter(adapter);
        //binding.timeCountListRecord.addItemDecoration(new DividerForRecycler(this,false,true));
    }

    private void setListener(){
        binding.timeCountStart.setOnClickListener(view -> {
            startCount();
        });
        binding.timeCountResetRecord.setOnClickListener(view -> {
            if (pauseing){
                resetCount();
            }else {
                recordCount();
            }
        });
        binding.timeCountContinuePause.setOnClickListener(view -> {
            if (pauseing){
                continueCount();
            }else {
                pauseCount();
            }
        });

    }

    private void startCount(){
        counting=true;
        pauseing=false;
        binding.setCounting(counting);
        binding.setPauseing(pauseing);
        timeCountUtil.reset();
        timeCountUtil.setPauseing(pauseing);
    }

    private void pauseCount(){
        pauseing=true;
        binding.setPauseing(pauseing);
        timeCountUtil.setPauseing(pauseing);
    }

    private void continueCount(){
        pauseing=false;
        binding.setPauseing(pauseing);
        timeCountUtil.setPauseing(pauseing);
    }

    private void resetCount(){
        counting=false;
        binding.setCounting(counting);
        timeCountUtil.setPauseing(true);
        timeCountUtil.reset();
        recordTimeList.clear();
        adapter.notifyDataSetChanged();
    }

    private void recordCount(){
        recordTimeList.add(timeCountUtil.countTimeMillis.get());
        adapter.notifyItemInserted(recordTimeList.size()-1);
        binding.timeCountListRecord.scrollToPosition(recordTimeList.size()-1);
        LogUtil.i("recordCount--"+binding.timeCountListRecord.getHeight());
    }

}
