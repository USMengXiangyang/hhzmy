package com.bwie.test;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by asus on 2016/11/1.
 */
public class CircleView extends View {

    private float sweepAngle;
    private float startAngle;
    private float textSize;
    private int textColor;
    public int arcColor;
    public int circleColor = Color.BLUE;
    private float mCircleXY;
    //外圆画笔
    private Paint mArcPaint;
    private float mRadius;
    //内园画笔
    private Paint mCirclePaint;
    private Paint mTextPaint;
    private RectF mRectF;

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        if (ta != null) {
            circleColor = ta.getColor(R.styleable.CircleView_circleColor, 0);
            arcColor = ta.getColor(R.styleable.CircleView_arcColor, 0);
            textColor = ta.getColor(R.styleable.CircleView_textColor, 0);
            textSize = ta.getDimension(R.styleable.CircleView_textSize, 60);
//            text = ta.getString(R.styleable.CircleView_text);
            startAngle = ta.getInt(R.styleable.CircleView_startAngle, 0);
            sweepAngle = ta.getInt(R.styleable.CircleView_sweepAngle, 360);
            ta.recycle();
            ;
        }

    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        init(w,h);
    }

    private void init(int w,int h) {
        int length = Math.min(w, h);
        mCircleXY = length / 2;
        mRadius = length * 0.5f / 2;
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setColor(Color.RED);
        mRectF = new RectF(length * 0.1f, length * .01f, length * 0.9f, length * 0.9f);

        mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArcPaint.setColor(Color.BLUE);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth((w * 0.1f));

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(textSize);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        drawSth(canvas);
    }

    private void drawSth(Canvas canvas) {
        canvas.drawCircle(mCircleXY, mCircleXY, mRadius, mCirclePaint);
        canvas.drawArc(mRectF, startAngle, sweepAngle, false, mArcPaint);
        canvas.drawText("司南",  mCircleXY, mCircleXY + textSize
                / 4, mTextPaint);
    }
}
