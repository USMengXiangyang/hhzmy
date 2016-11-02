package com.bwie.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;

public class MainActivity extends AppCompatActivity {
    private int imageX = 0;//图片的X轴
    private int imageY = 0;//图片的Y轴
    private SurfaceHolder holder = null;
    private int screenWidth = 0;
    private int screenHeight = 0;
    private int imageWidth = 0;
    private int imageHeight = 0;
    private Bitmap bitmap = null;
    private float scale=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        this.screenWidth = super.getWindowManager().getDefaultDisplay().getWidth();
        this.screenHeight = super.getWindowManager().getDefaultDisplay().getHeight();
        this.bitmap = BitmapFactory.decodeResource(super.getResources(), R.mipmap.ic_launcher);
        this.imageWidth = this.bitmap.getWidth();
        this.imageHeight = this.bitmap.getHeight();
        this.imageX = (this.screenWidth - this.imageX) / 2;
        this.imageY = (this.screenHeight - this.imageY) / 2;
        super.setContentView(new MySurfaceView(this));

    }

    private class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

        public MySurfaceView(Context context) {
            super(context);
            MainActivity.this.holder = super.getHolder();
            MainActivity.this.holder.addCallback(this);

            super.setFocusable(true);
        }

        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            MainActivity.this.setimage(1,250,300);

        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        }
    }

    private void setimage(float scale, int width, int height) {
        Canvas canvas = MainActivity.this.holder.lockCanvas();
        Paint paint = new Paint();
        canvas.drawRect(0,0,MainActivity.this.screenWidth,MainActivity.this.screenHeight,paint);
        Matrix matrix = new Matrix();
        matrix.postScale(scale,scale);
        Bitmap target = Bitmap.createBitmap(MainActivity.this.bitmap,0,0,width,height,matrix,true);
        this.imageWidth = target.getWidth();
        this.imageHeight = target.getHeight();
        this.imageX = (this.screenWidth-this.imageWidth)/2;
        this.imageY = (this.screenHeight - this.imageHeight)/2;
        canvas.translate(this.imageX,this.imageY);
        canvas.drawBitmap(this.bitmap,matrix,paint);
        MainActivity.this.holder.unlockCanvasAndPost(canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int pointCount  = event.getPointerCount();
        if(pointCount==2){
            float pointA = event.getY(0);
            float pointB = event.getX(1);
            if(pointA<pointB){
                float temp = pointA;
                pointA = pointB;
                pointB = temp;
            }
            if(!(event.getAction() == MotionEvent.ACTION_UP)){
                MainActivity.this.setimage(scale,250,500);
            }
        }
        return super.onTouchEvent(event);
    }
}
