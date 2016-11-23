package com.hhzmy.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hhzmy.adaper.My_Grid_Goods_List_Adapter;
import com.hhzmy.adaper.My_goods_adapter;
import com.hhzmy.base.Goodslist;
import com.hhzmy.bean.GoodsBean;
import com.hhzmy.dataneturl.OkHttp;
import com.hhzmy.test.R;
import com.hhzmy.webview.BaiduActivity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Request;

/**
 * Created by asus on 2016/11/16.
 */
public class GoodsFragment extends Fragment implements Runnable {
    private String goods_path = "http://mock.eoapi.cn/success/L11SvLlRPNYsdV5F6df54qhT7VHcr6CJ";
    private GoodsBean goodsBean;
    private ViewPager goods_f_vp;
    private View view;
    private TextView title_price_time;
    private TextView title_name;
    private ListView goods_list;
    private List<Goodslist> goodslists;
    private String map;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 先去获取到当前条目索引
            int currentItem = goods_f_vp.getCurrentItem();
            // 条目索引+1
            currentItem++;
            // 重新设置给viewPager
            goods_f_vp.setCurrentItem(currentItem);
            // 再调用发延时消息的方法
//            sendDelayMessage();
        }
    };
    private String address;
    private My_Grid_Goods_List_Adapter adapter;
    private String str_exit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.goods_fragment, container, false);
        sp = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        initView();
        initData();
        initGoodsData();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                title_price_time.setText("￥299.0  距结束  " + msg.obj);
                super.handleMessage(msg);
            }
        };
        new Thread(this).start();
        int[] imgdata = {R.mipmap.barcode_suceess, R.mipmap.barcode_suceess, R.mipmap.barcode_suceess};
        My_goods_adapter adapter = new My_goods_adapter(getActivity(), imgdata, handler);
        goods_f_vp.setAdapter(adapter);
        return view;
    }


    private void initGoodsData() {
        goodslists = new ArrayList<>();
        goodslists.add(new Goodslist(" 送至  ", "", R.mipmap.arrow_next));
        goodslists.add(new Goodslist(" 已选 ", "请选择商品", R.mipmap.arrow_next));
        goodslists.add(new Goodslist(" 任性付  ", "新用户满88减30（部分商品不参与打折）", R.mipmap.arrow_next));

    }

    private void initView() {
        goods_f_vp = (ViewPager) view.findViewById(R.id.goods_vp);
        title_price_time = (TextView) view.findViewById(R.id.title_price_time);
        title_name = (TextView) view.findViewById(R.id.title_name);
        goods_list = (ListView) view.findViewById(R.id.goods_list);

    }

    private void initData() {
        OkHttp.getAsync(goods_path, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Log.e("reqyestFailure", "请求异常");

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Gson gson = new Gson();
                goodsBean = gson.fromJson(result, GoodsBean.class);
                //itemName
                GoodsBean.DataBeanX.Data1Bean.DataBean.ItemInfoVoBean infoVo = goodsBean.getData().getData1().getData().getItemInfoVo();
                String str = infoVo.getItemName();
               /* SpannableStringBuilder builder = new SpannableStringBuilder(str);
                String rexgString = "#";
                Pattern pattern = Pattern.compile(rexgString);
                Matcher matcher = pattern.matcher(str);
                while (matcher.find()){
                    builder.setSpan(new ImageSpan(getActivity(),R.mipmap.public_zi_ying_new_label),matcher.start(),matcher.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                title_name.setText(str);*/
                //图文混排------↓
                SpannableString spannableStringLeft = new SpannableString("left");
                Bitmap bitmapLeft = BitmapFactory.decodeResource(getResources(), R.mipmap.public_zi_ying_new_label);
                ImageSpan imageSpanLeft = new ImageSpan(bitmapLeft, DynamicDrawableSpan.ALIGN_BOTTOM);
                spannableStringLeft.setSpan(imageSpanLeft, 0, 3, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                title_name.append(spannableStringLeft);
                title_name.append(str);
                initListViewData();


            }
        });
    }
    private void initListViewData() {
        str_exit = goodsBean.getData().getPrescription().getShipOffSetText();
         adapter = new My_Grid_Goods_List_Adapter(address, str_exit, goodslists, getActivity());
        goods_list.setAdapter(adapter);
        goods_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), BaiduActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        address = data.getStringExtra("address");
        Log.e("=====", address);
        adapter = new My_Grid_Goods_List_Adapter(address, str_exit, goodslists, getActivity());
        goods_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void run() {
        while (true) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
                String time = format.format(new Date());
                handler.sendMessage(handler.obtainMessage(100, time));
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
       /* map = sp.getString("map","");
       // tv_dizhi.setText(map);
        Toast.makeText(getActivity(),map,Toast.LENGTH_SHORT).show();*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        edit = sp.edit();
        edit.clear();
        edit.commit();

    }

}
