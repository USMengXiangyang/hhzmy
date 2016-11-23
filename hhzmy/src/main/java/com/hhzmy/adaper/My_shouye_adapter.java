package com.hhzmy.adaper;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hhzmy.bean.JsonBean;
import com.hhzmy.test.R;
import com.hhzmy.webview.WebViewActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by asus on 2016/11/9.
 */
public class My_shouye_adapter extends PagerAdapter {
    private Context context;
    private Handler handler;
    private String path;
    List<JsonBean.DataBean.TagBean> data;
    private ImageView img;
    private View view;

    public My_shouye_adapter(String path,Context context, Handler handler, List<JsonBean.DataBean.TagBean> data) {
        this.context = context;
        this.path = path;
        this.handler = handler;
        this.data = data;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        view = View.inflate(context, R.layout.vp_item, null);
        img = (ImageView) view.findViewById(R.id.iv_vp);
        ImageLoader.getInstance().displayImage(path + data.get(position % data.size()).getPicUrl(), img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(context, WebViewActivity.class);
                in.putExtra("imgurl", data.get(position%data.size()).getLinkUrl());
                context.startActivity(in);
            }
        });
        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
