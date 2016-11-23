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
 * Created by asus on 2016/11/16.
 */
public class My_List_adapter extends BaseAdapter {
    private Context context;
    private List<String> data;
    private String path;

    public My_List_adapter(Context context, List<String> data, String path) {
        this.context = context;
        this.data = data;
        this.path = path;
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
        view = View.inflate(context, R.layout.list_item,null);
        ImageView img = (ImageView) view.findViewById(R.id.list_img);
        ImageLoader.getInstance().displayImage(path+data.get(i),img);
        return view;
    }
}
