package com.hhzmy.adaper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.hhzmy.test.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by asus on 2016/11/15.
 */
public class My_shouye_grid_ada extends BaseAdapter {
    private List<String> data;
    private Context context;
    private String path;
    private ViewHolder holder;

    public My_shouye_grid_ada(String path,List<String> data, Context context) {
        this.data = data;
        this.path = path;
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
        if (view==null) {
            view = View.inflate(context, R.layout.grid_item, null);
            holder= new ViewHolder();
            holder.img = (ImageView) view.findViewById(R.id.grid_img_item);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        ImageLoader.getInstance().displayImage(path+data.get(i),holder.img);

        return view;
    }

    class ViewHolder {
        ImageView img;
    }
}
