package com.bwie.test;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by asus on 2016/11/2.
 */
public class MyAdapter extends BaseAdapter {
    public LinkedList<Boolean> linkedList = new LinkedList<>();
    private List<String> data;
    Context context;
    CheckBox cAll;
    private boolean isHide;

    public MyAdapter(List<String> data,Context context,CheckBox cAll,boolean isHide) {
        super();
        this.data = data;
        this.context = context;
        this.cAll = cAll;
        this.isHide = isHide;
        for (int i=0;i<linkedList.size();i++){
                linkedList.add(false);
        }
    }
    // 对外提供一个方法，获取到这个集合
    public List<Boolean> getSelect() {
        return linkedList;
    }
    public void setHide(boolean isHide){
        this.isHide = isHide;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view!=null){
            view = View.inflate(context,R.layout.list_item,null);
            holder = new ViewHolder();
            holder.checkBox = (CheckBox) view.findViewById(R.id.cb);
            holder.tv = (TextView) view.findViewById(R.id.tv);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.checkBox.setChecked(linkedList.get(i));
        holder.tv.setText(data.get(i));
        return view;
    }
    class ViewHolder{
        CheckBox checkBox;
        TextView tv;
    }


}
