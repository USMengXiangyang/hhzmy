package com.hhzmy.adaper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhzmy.base.Goodslist;
import com.hhzmy.test.R;

import java.util.List;

/**
 * Created by asus on 2016/11/17.
 */
public class My_Grid_Goods_List_Adapter extends BaseAdapter {
    private List<Goodslist> data;
    private Context context;
    private String str;
    private String address;
    private TextView goods_add_id;

    public My_Grid_Goods_List_Adapter(String address,String str, List<Goodslist> data, Context context) {
        this.address = address;
        this.data = data;
        this.str = str;
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
        view = View.inflate(context, R.layout.my_grid_goods_list, null);
        goods_add_id = (TextView) view.findViewById(R.id.goods_add_id);
        TextView goods_add = (TextView) view.findViewById(R.id.goods_add);
        ImageView goods_add_img = (ImageView) view.findViewById(R.id.goods_add_img);



        if (i==0) {
            goods_add_id.setText(data.get(i).getTitle() + address+"\n  " + str);

        } if (data.get(i).getMsg1().length() > 15) {
            goods_add_id.setText(data.get(i).getTitle());
            goods_add.setText(data.get(i).getMsg1().substring(0, 16) + "...");
        } else {
            goods_add.setText(data.get(i).getMsg1());
        }
       /* goods_add_id.setText(data.get(i).getTitle()+address);
        goods_add.setText(data.get(i).getMsg1());*/
        goods_add_img.setImageResource(data.get(i).getImg_data());
        return view;
    }


}
