package com.ruiaa.toolpck.tool;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;
import com.ruiaa.toolpck.tool.calculation.CalculationGroup;
import com.ruiaa.toolpck.tool.calculation.calculator.CalculatorToolInfo;
import com.ruiaa.toolpck.tool.hardware.HardwareGroup;
import com.ruiaa.toolpck.tool.measure.MeasureGroup;
import com.ruiaa.toolpck.tool.query.QueryGroup;
import com.ruiaa.toolpck.util.KeyStorage;
import com.ruiaa.toolpck.util.LogUtil;
import com.ruiaa.toolpck.util.ResUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruiaa on 2016/12/7.
 */

public abstract class ToolInfo {

    @SerializedName("typeStr")
    private String typeStr;

    public ToolInfo() {
        this.typeStr = getClass().getName();
    }

    public Intent getActivityIntent(Context context) {
        return new Intent(context, getActivityClass());
    }

    abstract public Class<?> getActivityClass();

    abstract public int getIconResId();

    abstract public int getLabelResId();

    public String getLabel() {
        return ResUtil.getString(getLabelResId());
    }

    public String toString() {
        return getLabel();
    }


    public abstract static class Group {

        @SerializedName("allChild")
        private ArrayList<ToolInfo> allChild = null;
        @SerializedName("groupType")
        private String groupType;

        public Group() {
            groupType = getClass().getName();
        }

        protected void registerChildToolInfo(Group group, ToolInfo toolInfo) {
            if (group.allChild == null) {
                group.allChild = new ArrayList<>();
            }
            group.allChild.add(toolInfo);
        }

        public ArrayList<ToolInfo> getChildToolInfo() {
            if (allChild == null) {
                allChild = new ArrayList<>();
            }
            return allChild;
        }

        public void resetChildToolInfo(List<ToolInfo> toolInfos) {
            if (toolInfos == null) return;
            getChildToolInfo().clear();
            getChildToolInfo().addAll(toolInfos);
        }

        abstract public int getLabelResId();

        public String getLabel() {
            return ResUtil.getString(getLabelResId());
        }

        public String getGroupType() {
            return groupType;
        }

        public String toString() {
            return getLabel();
        }
    }

    public static class AllTool {

        public static final String ALL_TOOL = "all_tool";

        private static AllTool instance = null;

        public static AllTool getInstance() {
            if (instance == null) {
                if (KeyStorage.contains(ALL_TOOL)) {
                    instance=parseStorage();
                    instance.checkAllGroup();
                } else {
                    instance = new AllTool();
                    instance.initAllGroup();
                }
            }
            return instance;
        }

        public static void saveStorage() {
            Gson gson = new Gson();
            KeyStorage.put(ALL_TOOL, gson.toJson(getInstance()));
        }

        private static AllTool parseStorage(){
            GsonBuilder builder = new GsonBuilder()
                    .registerTypeAdapter(ToolInfo.class, new JsonDeserializer<ToolInfo>() {
                        @Override
                        public ToolInfo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                                throws JsonParseException {
                            try {
                                String type = ((JsonObject) json).get("typeStr").getAsString();
                                return context.deserialize(json,Class.forName(type));
                            } catch (Exception e) {
                                LogUtil.e("deserialize####", e);
                                return new CalculatorToolInfo();
                            }
                        }
                    })
                    .registerTypeAdapter(Group.class, new JsonDeserializer<Group>() {
                        @Override
                        public Group deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                                throws JsonParseException {
                            try{
                                String type = ((JsonObject) json).get("groupType").getAsString();
                                return context.deserialize(json,Class.forName(type));
                            }catch (Exception e){
                                LogUtil.e("deserialize####",e);
                                return new CalculationGroup();
                            }
                        }
                    });
            Gson gson = builder.create();
            return gson.fromJson((String) KeyStorage.get(ALL_TOOL), AllTool.class);
        }


        /*
         *
         *
         *
         *
         */



        private ArrayList<Group> allGroup;

        public AllTool() {
        }

        private void initAllGroup() {
            allGroup = new ArrayList<>();
            allGroup.add(new CalculationGroup());
            allGroup.add(new MeasureGroup());
            allGroup.add(new OftenGroup());
            allGroup.add(new QueryGroup());
            allGroup.add(new HardwareGroup());
        }

        private void checkAllGroup(){

        }

        /*
         *
         *
         *
         *
         */

        public ArrayList<Group> getAllGroup() {
            if (allGroup == null || allGroup.isEmpty()) {
                initAllGroup();
            }
            return allGroup;
        }

        public Group getGroup(String groupType) {
            getAllGroup();
            for (Group g : getAllGroup()) {
                if (g.getGroupType().equals(groupType)) {
                    return g;
                }
            }
            return getAllGroup().get(0);
        }

        public Group getOftenGroup() {
            return getGroup(OftenGroup.class.getName());
        }

        public List<ToolInfo> getAllToolInfos() {
            getAllGroup();
            List<ToolInfo> list = new ArrayList<>();
            for (Group group : getAllGroup()) {
                if (OftenGroup.class.getName().equals(group.getGroupType())) continue;
                for (ToolInfo toolInfo : group.getChildToolInfo()) {
                    list.add(toolInfo);
                }
            }
            return list;
        }

    }
}
