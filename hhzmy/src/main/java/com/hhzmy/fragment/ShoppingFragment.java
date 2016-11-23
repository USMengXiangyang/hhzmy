package com.hhzmy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hhzmy.adaper.My_Car_list_adapter;
import com.hhzmy.bean.CarData;
import com.hhzmy.bean.ShopCarDataBean;
import com.hhzmy.dataneturl.OkHttp;
import com.hhzmy.pay.PayDemoActivity;
import com.hhzmy.test.R;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * Created by asus on 2016/11/8.
 */
public class ShoppingFragment extends Fragment implements View.OnClickListener{
    private View view;
    private ListView shop_lv_view;
    private CheckBox checkall;
    private TextView shop_editbutton;
    private RelativeLayout rela_allprice;
    private Button shop_bayall;
    private TextView allprice;
    private String path="http://mock.eoapi.cn/success/megQ2CBFAeFzJzIwTSVdNnpQYZCrsrIq";
    private String json;
    private ArrayList<CarData> list;
    private My_Car_list_adapter adapter;
    private double sum = 0.00;
    private String shop_editbutton_text;
    private String shop_bayall_text;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shopping_fragment,container,false);
        //获取控件ID
        infoview();
        //得到购物车数据
        getCarData();
        checkall.setOnClickListener(this);
        shop_editbutton.setOnClickListener(this);
        shop_bayall.setOnClickListener(this);
        shop_lv_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.linkedList.set(i, !adapter.linkedList.get(i));
                //点击的同时判断是否全选
                if(adapter.linkedList.contains(false)){
                    checkall.setChecked(false);
                    sum = 0.00;
                    allprice.setText("总计:￥" + sum);
                }else{
                    checkall.setChecked(true);
                    sum = sum + (list.get(i).getGoodsNewPrice()) * 1;
                    allprice.setText("总计:￥" + sum);
                }
                adapter.notifyDataSetChanged();
            }
        });
        return view;
    }

    private void getCarData() {
        OkHttp.getAsync(path, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                json = result;
                //Toast.makeText(ShopCarActivity.this, json + "", Toast.LENGTH_SHORT).show();
                showData(json);
            }
        });
    }

    private void showData(String json) {
        Gson gson = new Gson();
        ShopCarDataBean shopping = gson.fromJson(json, ShopCarDataBean.class);
        //商品型号
        List<ShopCarDataBean.DataBeanX.Data1Bean.DataBean.ItemClusterDisplayVOBean.ColorListBean> cl = shopping.getData().getData1().getData().getItemClusterDisplayVO().getColorList();
        //商品名称
        ShopCarDataBean.DataBeanX.Data1Bean.DataBean.ItemInfoVoBean name = shopping.getData().getData1().getData().getItemInfoVo();

        //价格
        ShopCarDataBean.DataBeanX.PriceBean.SaleInfoBean jia = shopping.getData().getPrice().getSaleInfo().get(0);
        //jia1.setText(jia.getNetPrice());现价
        //jia2.setText(jia.getRefPrice());原价
        list = new ArrayList<>();
        CarData cardata = new CarData();
        cardata.setGoodsName(name.getItemName() + "");
        cardata.setGoodsNewPrice(Double.parseDouble(jia.getNetPrice()));
        cardata.setRefPrice(Double.parseDouble(jia.getRefPrice()));
        cardata.setGoodsCL(cl.get(0).getCharacterValueName() + "");
        list.add(cardata);
        //Toast.makeText(ShopCarActivity.this, list.get(0).getNowPrice() + "", Toast.LENGTH_SHORT).show();
        adapter = new My_Car_list_adapter(list,getActivity());
        shop_lv_view.setAdapter(adapter);
    }

    private void infoview() {
        shop_lv_view = (ListView)view.findViewById(R.id.shop_lv_view);
        checkall = (CheckBox)view. findViewById(R.id.checkall);
        shop_editbutton = (TextView)view.findViewById(R.id.shop_editbutton);
        rela_allprice = (RelativeLayout)view.findViewById(R.id.rela_allprice);
        shop_bayall = (Button)view.findViewById(R.id.shop_bayall);
        allprice = (TextView)view.findViewById(R.id.allprice);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.checkall:
                boolean flag = checkall.isChecked();
                double f1 = 0;
                for (int i = 0; i < adapter.getSelect().size(); i++) {
                    adapter.getSelect().set(i, flag);
                }
                if (flag == true) {
                    for (int i = 0; i < list.size(); i++) {
                        if (i == 0) {
                            sum = 0.00;
                        }
                        sum = sum + (list.get(i).getGoodsNewPrice()) * 1;
                        BigDecimal b1 = new BigDecimal(Double.toString(sum));
                        BigDecimal b2 = new BigDecimal(Double.toString(1));
                        f1 = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    }
                    allprice.setText("总计:￥" + f1);
                } else if (flag == false) {
                    sum = 0.00;
                    allprice.setText("总计:￥" + sum);
                }
                // shop_lv_view.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                break;
            case R.id.shop_editbutton:
                shop_editbutton_text = shop_editbutton.getText().toString();
                shop_bayall_text = shop_bayall.getText().toString();
                if (shop_editbutton_text.equals("编辑")) {
                    shop_editbutton.setText("完成");
                    rela_allprice.setVisibility(View.GONE);
                    if (shop_bayall_text.equals("结算")) {
                        shop_bayall.setText("删除");
                    }
                } else if (shop_editbutton_text.equals("完成")) {
                    shop_editbutton.setText("编辑");
                    rela_allprice.setVisibility(View.VISIBLE);
                    if (shop_bayall_text.equals("删除")) {
                        shop_bayall.setText("结算");
                    }
                }
                break;
            case R.id.shop_bayall:
                shop_bayall_text = shop_bayall.getText().toString();
                if (shop_bayall_text.equals("删除")) {
                    /*for (int i = 0; i < list.size(); i++) {
                        sum = sum - (list.get(i).getNowPrice());
                        shop_lv_view.setAdapter(new shop_lv_adapter(ShopCarActivity.this, null));
                        adapter.notifyDataSetChanged();
                    }
                    allprice.setText("总计:￥" + sum);*/
                    Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
                } else if (shop_bayall_text.equals("结算")) {
                    Toast.makeText(getActivity(), "准备结算", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), PayDemoActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }
}
