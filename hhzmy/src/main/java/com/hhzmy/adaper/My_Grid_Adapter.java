package com.hhzmy.adaper;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhzmy.bean.JsonBean;
import com.hhzmy.test.R;
import com.hhzmy.webview.WebViewActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by asus on 2016/11/14.
 */
public class My_Grid_Adapter extends BaseAdapter {
    private List<JsonBean.DataBean.TagBean> data;
    private Context context;
    private ViewHolder holder;
    private String path;

    public My_Grid_Adapter(String path,Context context, List<JsonBean.DataBean.TagBean> data) {
        this.context = context;
        this.path = path;
        this.data = data;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        JsonBean.DataBean.TagBean tag = data.get(i);
        if (view==null){
            view = View.inflate(context, R.layout.home_gride_item,null);
            holder = new ViewHolder();
            holder.grid_img = (ImageView) view.findViewById(R.id.grid_img);
            holder.grid_tv = (TextView) view.findViewById(R.id.grid_tv);
            view.setTag(holder);

        }else{
            holder = (ViewHolder) view.getTag();
        }
        ImageLoader.getInstance().displayImage(path+tag.getPicUrl(),holder.grid_img);
        holder.grid_tv.setText(data.get(i).getElementName());
        holder.grid_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("picurl",data.get(i).getLinkUrl());
                context.startActivity(intent);
            }
        });
        return view;
    }
    class ViewHolder{
        ImageView grid_img;
        TextView grid_tv;

    }

}
