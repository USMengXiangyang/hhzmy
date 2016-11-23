package com.hhzmy.adaper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hhzmy.bean.JsonBean;
import com.hhzmy.test.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by asus on 2016/11/16.
 */
public class My_shouye_recyc_adapter extends RecyclerView.Adapter<My_shouye_recyc_adapter.MyViewHolder> {
    private Context context;
    private List<JsonBean.DataBean.TagBean> data;
    private String path;

    public My_shouye_recyc_adapter(Context context, List<JsonBean.DataBean.TagBean> data,String path) {
        this.context = context;
        this.data = data;
        this.path = path;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.recyc_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ImageLoader.getInstance().displayImage(path+data.get(position).getPicUrl(), holder.img);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView img;

        public MyViewHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.recyc_img);
        }
    }
}
