package com.hhzmy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by asus on 2016/11/15.
 */
public class My_shouye_grid_view1 extends GridView {

    public My_shouye_grid_view1(Context context) {
        super(context);
    }

    public My_shouye_grid_view1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public My_shouye_grid_view1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpce = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpce);
    }
}
