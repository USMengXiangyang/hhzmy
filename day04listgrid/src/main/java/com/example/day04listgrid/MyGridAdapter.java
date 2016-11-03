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
public class MyGridAdapter extends BaseAdapter {
    List<Bean1> data1;
    Context context;

    public MyGridAdapter(List<Bean1> data1, Context context) {
        this.data1 = data1;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data1.size();
    }

    @Override
    public Object getItem(int i) {
        return data1.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = View.inflate(context,R.layout.gv_item,null);
        ImageView img = (ImageView) view.findViewById(R.id.img);
        TextView name = (TextView) view.findViewById(R.id.name);
        img.setImageResource(data1.get(i).getImg());
        name.setText(data1.get(i).getName());
        return view;
    }
}
