package com.example.day04listgrid;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by asus on 2016/11/3.
 */
public class MyListAdapter extends BaseAdapter {
    List<Bean2> data;
    Context context;

    public MyListAdapter(List<Bean2> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
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
        view = View.inflate(context,R.layout.lv_item,null);
        ImageView img2 = (ImageView) view.findViewById(R.id.img2);
        TextView name2 = (TextView) view.findViewById(R.id.name2);
        TextView time = (TextView) view.findViewById(R.id.time);
        img2.setImageResource(data.get(i).getImg());
        name2.setText(data.get(i).getName());
        time.setText(data.get(i).getShijian());
        return view;
    }
}
