package com.ruiaa.toolpck.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ruiaa.toolpck.BR;
import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.base.BaseFragment;
import com.ruiaa.toolpck.common.DefaultItemTouchHelper;
import com.ruiaa.toolpck.common.adapter.SimpleRecyclerAdapter;
import com.ruiaa.toolpck.databinding.FragmentToollistBinding;
import com.ruiaa.toolpck.tool.OftenGroup;
import com.ruiaa.toolpck.tool.ToolInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by ruiaa on 2016/12/8.
 */

public class ToolListFragment extends BaseFragment {
    private static final String GROUP_TYPE = "group_type";

    public ToolListFragment() {

    }

    public static ToolListFragment newInstance(String groupType) {
        ToolListFragment fragment = new ToolListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(GROUP_TYPE, groupType);
        fragment.setArguments(bundle);
        return fragment;
    }


    private String groupType;
    private MainActivity activity;
    private FragmentToollistBinding binding;
    private RecyclerView recyclerView;
    private SimpleRecyclerAdapter<ToolInfo> adapter;
    private ToolInfo.Group group;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            groupType = getArguments().getString(GROUP_TYPE);
        }
        group = ToolInfo.AllTool.getInstance().getGroup(groupType);
        activity = (MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_toollist, container, false);

        recyclerView = binding.fragmentToollistRecycler;
        setAdapter();
        setFloatButton();

        return binding.getRoot();
    }

    private void setAdapter() {
        adapter = new SimpleRecyclerAdapter<ToolInfo>(
                activity,
                R.layout.item_tool_info,
                group.getChildToolInfo(),
                ((holder, position, model) -> {
                    holder.getBinding().setVariable(BR.toolInfoHeight, recyclerView.getWidth() / 3);
                    holder.getBinding().setVariable(BR.toolInfoText, model.getLabel());
                    holder.getBinding().setVariable(BR.toolInfoImg, model.getIconResId());
                }),
                ((holder, position, model) -> {
                    holder.getBinding().getRoot().setOnClickListener(v -> {
                        activity.startActivity(model.getActivityIntent(activity));
                    });
                })
        );

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 3));
        //recyclerView.addItemDecoration(new DividerForRecycler(activity,true,true));

        DefaultItemTouchHelper.DefaultItemTouchHelpCallback callback=new DefaultItemTouchHelper.DefaultItemTouchHelpCallback(new DefaultItemTouchHelper.DefaultItemTouchHelpCallback.OnItemTouchCallbackListener() {
            @Override
            public void onSwiped(int adapterPosition) {

            }

            @Override
            public boolean onMove(int srcPosition, int targetPosition) {
                Collections.swap(group.getChildToolInfo(),srcPosition,targetPosition);
                adapter.notifyItemMoved(srcPosition, targetPosition);
                return false;
            }
        });
        DefaultItemTouchHelper touchHelper=new DefaultItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
    }

    private void setFloatButton() {
        if (OftenGroup.class.getName().equals(groupType)) {
            binding.fragmentToollistFloatButton.setOnClickListener(v -> onAddOftenClick());
        } else {
            binding.fragmentToollistFloatButton.setVisibility(View.GONE);
        }
    }

    private void onAddOftenClick() {
        List<ToolInfo> allToolInfo=ToolInfo.AllTool.getInstance().getAllToolInfos();
        List<Integer> list=new ArrayList<>();
        for(ToolInfo oftenTool : group.getChildToolInfo() ){
            if (allToolInfo.contains(oftenTool)){
                list.add(allToolInfo.indexOf(oftenTool));
            }
        }
        new MaterialDialog.Builder(activity)
                .title(R.string.add_often_use)
                .items(allToolInfo)
                .itemsCallbackMultiChoice(list.toArray(new Integer[list.size()]), ((dialog, which, text) -> {
                    List<ToolInfo> newToolInfos=new ArrayList<>();
                    for(Integer i:which){
                        if (allToolInfo.size()>i){
                            newToolInfos.add(allToolInfo.get(i));
                        }
                    }

                    group.resetChildToolInfo(newToolInfos);
                    adapter.notifyDataSetChanged();
                    return true;
                }))
                .positiveText(R.string.sure)
                .negativeText(R.string.cancel)
                .show();
    }

}
