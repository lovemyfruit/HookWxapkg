package com.beichen.hookwxx5.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.beichen.hookwxx5.R;
import com.beichen.hookwxx5.data.InjectItem;
import com.beichen.hookwxx5.plugin.Utils;

import java.util.LinkedHashMap;
import java.util.List;

public class InjectExAdapter extends BaseExpandableListAdapter {

    private LinkedHashMap<String, List<InjectItem>> map;
    Context context;
    int groupLayoutId;
    int childrenLayoutId;

    public InjectExAdapter(Context context, int groupLayoutId, int childrenLayoutId, LinkedHashMap<String, List<InjectItem>> map){
        this.context = context;
        this.groupLayoutId = groupLayoutId;
        this.childrenLayoutId = childrenLayoutId;
        this.map = map;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return map.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        List<InjectItem> itemList = Utils.LinkedHashMapIndex2Value(map, groupPosition);
        return itemList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return Utils.LinkedHashMapIndex2Key(map, groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<InjectItem> itemList = Utils.LinkedHashMapIndex2Value(map, groupPosition);
        return itemList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null){
            view = LayoutInflater.from(context).inflate(groupLayoutId, parent, false);
        }else {
            view = convertView;
        }
        String groupGame = (String) getGroup(groupPosition);
        TextView title = view.findViewById(R.id.tv_group_title);
        title.setText(groupGame);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null){
            view = LayoutInflater.from(context).inflate(childrenLayoutId, parent, false);
        }else {
            view = convertView;
        }
        InjectItem injectItem = (InjectItem) getChild(groupPosition, childPosition);
        TextView tv_child_display_name = view.findViewById(R.id.tv_child_display_name);
        TextView tv_child_avaliable = view.findViewById(R.id.tv_child_avaliable);
        TextView tv_child_node = view.findViewById(R.id.tv_child_node);
        TextView tv_child_appid = view.findViewById(R.id.tv_child_appid);
        TextView tv_child_jscode = view.findViewById(R.id.tv_child_jscode);
        tv_child_display_name.setText("显示名字: " + injectItem.getDisplayName());
        tv_child_avaliable.setText("是否可用：" + Boolean.toString(injectItem.isAvailable()));
        tv_child_node.setText("备注: " + injectItem.getNode());
        tv_child_appid.setText("appId: " + injectItem.getAppId());
        tv_child_jscode.setText("js脚本: " + injectItem.getJsCode());
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();

    }
}
