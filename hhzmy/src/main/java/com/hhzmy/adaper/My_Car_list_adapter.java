package com.hhzmy.adaper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.hhzmy.bean.CarData;
import com.hhzmy.test.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by asus on 2016/11/19.
 */
public class My_Car_list_adapter extends BaseAdapter {
   public static LinkedList<Boolean> linkedList = new LinkedList<>();
    List<CarData> carDatas;
    Context context;
    private ViewHolder holder;

    public My_Car_list_adapter(List<CarData> carDatas, Context context) {
        this.carDatas = carDatas;
        this.context = context;
        for (int i = 0; i < carDatas.size(); i++) {
            linkedList.add(false);
        }
    }
    // 对外提供一个方法，获取到这个集合
    public List<Boolean> getSelect() {
        return linkedList;
    }

    @Override
    public int getCount() {
        return carDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return carDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view = View.inflate(context, R.layout.car_list_item,null);
            holder = new ViewHolder();
            holder.car_name = (TextView) view.findViewById(R.id.car_name);
            holder.car_price = (TextView) view.findViewById(R.id.car_price);
            holder.checkBox = (CheckBox) view.findViewById(R.id.checkbox);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.car_name.setText(carDatas.get(i).getGoodsName());
        holder.car_price.setText(carDatas.get(i).getGoodsNewPrice()+"");
        holder.checkBox.setChecked(linkedList.get(i));
        return view;
    }
    class ViewHolder{
        CheckBox checkBox;
        TextView car_name;
        TextView car_price;


    }
}
