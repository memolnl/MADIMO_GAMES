package com.example.madimo_games.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.madimo_games.R;

import java.util.ArrayList;
import java.util.Map;

public class ExpLAdapter extends BaseExpandableListAdapter {
    private ArrayList<String> listCategoria;
    private Map<String, ArrayList<String>> mapChill;
    private Context context;

    public ExpLAdapter(ArrayList<String> listCategoria, Map<String, ArrayList<String>> mapChill, Context context) {
        this.listCategoria = listCategoria;
        this.mapChill = mapChill;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return listCategoria.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mapChill.get(listCategoria.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listCategoria.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mapChill.get(listCategoria.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
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
        String titulosCategoria = (String) getGroup(groupPosition);
        convertView = LayoutInflater.from(context).inflate(R.layout.activity_elv_group, null);
        TextView txtGroup = convertView.findViewById(R.id.txt_Group);
        txtGroup.setText(titulosCategoria);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String item = (String) getChild(groupPosition, childPosition);
        convertView = LayoutInflater.from(context).inflate(R.layout.activity_elv_child, null);
        TextView txtChild = convertView.findViewById(R.id.txt_Child);
        txtChild.setText(item);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
