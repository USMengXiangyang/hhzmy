package com.hhzmy.adaper;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hhzmy.test.R;

/**
 * Created by asus on 2016/11/19.
 */
public class My_goods_adapter extends PagerAdapter {
    private Context context;
    private View view;
    private ImageView img;
    private int[] imgdata;
    private Handler handler;

    public My_goods_adapter(Context context,int[] imgdata,Handler handler) {
        this.context = context;
        this.imgdata = imgdata;
        this.handler = handler;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        view = View.inflate(context, R.layout.vp_item,null);
        img = (ImageView) view.findViewById(R.id.iv_vp);
        img.setImageResource(imgdata[position%imgdata.length]);
       /* img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //移除所有回调消息
                        handler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_UP:
                        //抬起
                        handler.sendEmptyMessageAtTime(0, 2000);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        //取消
                        handler.sendEmptyMessageAtTime(0, 2000);
                        break;

                    default:
                        break;
                }
                return true;
            }
        });*/
        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
