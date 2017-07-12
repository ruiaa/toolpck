package com.ruiaa.toolpck.tool.query.commonphone;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.ruiaa.toolpck.BR;
import com.ruiaa.toolpck.R;
import com.ruiaa.toolpck.base.ToolbarActivity;
import com.ruiaa.toolpck.common.adapter.SimpleRecyclerAdapter;
import com.ruiaa.toolpck.common.widget.DividerForRecycler;
import com.ruiaa.toolpck.databinding.ActivityCommonPhoneBinding;
import com.ruiaa.toolpck.util.LogUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonPhoneActivity extends ToolbarActivity {

    private ActivityCommonPhoneBinding binding;
    private List<PhoneType> phoneTypeList;
    private ObservableField<String> showType;
    private DividerForRecycler dividerForRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_common_phone);
        showType=new ObservableField<>("");

        //LogUtil.i("onCreate####start");
        setToolbar();
        getCommonPhone();
        setAdapter();
        //LogUtil.i("onCreate####finish");
    }

    private void setToolbar() {
        initToolbar();
        setToolbarLeftBack();
        setTitle(R.string.common_phone);
    }

    private void getCommonPhone() {
        Gson gson = new Gson();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open("commonphone.json"), "utf-8"));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();

            PhoneType[] arr = gson.fromJson(stringBuilder.toString(), PhoneType[].class);
            phoneTypeList = Arrays.asList(arr);
        } catch (Exception e) {
            LogUtil.e("getCommonPhone####", e);
            phoneTypeList = new ArrayList<>();
        }
    }

    private void setAdapter() {
        dividerForRecycler=new DividerForRecycler(this,false,true);
        for (PhoneType phoneType : phoneTypeList) {
            phoneType.adapter = new SimpleRecyclerAdapter<>(
                    this,
                    R.layout.view_item_text_label_content,
                    phoneType.phoList,
                    ((holder, position, model) -> {
                        holder.getBinding().setVariable(BR.label, model.label);
                        holder.getBinding().setVariable(BR.content, model.phoNum);
                    })
            );
        }
        SimpleRecyclerAdapter<PhoneType> typeAdapter = new SimpleRecyclerAdapter<>(
                this,
                R.layout.item_common_phone_list,
                phoneTypeList,
                ((holder, position, model) -> {
                    RecyclerView recyclerView = (RecyclerView) holder.getBinding().getRoot().findViewById(R.id.item_common_phone_list);
                    recyclerView.setAdapter(model.adapter);
                    if (recyclerView.getLayoutManager()==null) {
                        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                        recyclerView.addItemDecoration(dividerForRecycler);
                    }
                    holder.getBinding().setVariable(BR.commonPhoneShowType,showType);
                    holder.getBinding().setVariable(BR.commonPhoneTypeLabel,model.label);
                }),
                ((holder, position, model) -> {
                    if (model.onClickListener==null){
                        model.onClickListener=v -> {
                            if (showType.get().equals(model.label)){
                                showType.set("");
                            }else {
                                showType.set(model.label);
                            }
                        };
                    }
                    holder.getBinding().getRoot().findViewById(R.id.item_common_phone_list_label).setOnClickListener(model.onClickListener);
                })
        );
        binding.commonPhoneList.setAdapter(typeAdapter);
        binding.commonPhoneList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    public static class PhoneType {
        @SerializedName("type")
        public String label;
        @SerializedName("phos")
        public List<Phone> phoList;

        transient public SimpleRecyclerAdapter<Phone> adapter = null;
        transient public View.OnClickListener onClickListener=null;

        public PhoneType(String label) {
            this.label = label;
            phoList = new ArrayList<>();
            LogUtil.i("PhoneType####" + label + "#");
        }

        public void add(Phone phone) {
            phoList.add(phone);
        }

        public void add(String label, String phoNum) {
            phoList.add(new Phone(label, phoNum));
        }
    }

    public static class Phone {
        @SerializedName("lab")
        public String label;
        @SerializedName("p")
        public String phoNum;

        public Phone(String label, String phoNum) {
            this.label = label;
            this.phoNum = phoNum;
        }

    }

/*    private void saveCommonPhone() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(new Request.Builder().url("http://www.00cha.com/tel.htm").build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Elements elements = Jsoup.parse(new String(response.body().bytes(), "gb2312")).select("td");
                List<PhoneType> phonetypes = new ArrayList<PhoneType>();
                PhoneType phoneType = null;
                boolean ispho = false;
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                for (int i = 0; i < elements.size() - 1; i++) {
                    String s = p.matcher(elements.get(i).text()).replaceAll("");
                    if (s.isEmpty()) continue;

                    LogUtil.d("****onResponse####" + s + "#");

                    try {
                        Double.parseDouble(s);
                        ispho = true;
                        phoneType.add(p.matcher(elements.get(i + 1).text()).replaceAll(""), s);


                    } catch (NumberFormatException e) {
                        if (!ispho) {
                            phoneType = new PhoneType(p.matcher(elements.get(i).text()).replaceAll(""));
                            phonetypes.add(phoneType);
                        }
                        ispho = false;
                    }
                }
                saveFile(phonetypes);
            }
        });
    }

    private void saveFile(List<PhoneType> phoneTypeList) {
        File file = new File(Environment.getExternalStorageDirectory(), "aaaphone.txt");
        try {
            OutputStream outputStream = new FileOutputStream(file);
            Gson gson = new Gson();
            outputStream.write(gson.toJson(phoneTypeList).getBytes("utf-8"));
            outputStream.close();
        } catch (IOException e) {
            LogUtil.e("saveFile####", e);
        }
    }*/

}
