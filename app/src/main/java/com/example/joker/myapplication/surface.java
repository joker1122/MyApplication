package com.example.joker.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;
import android.widget.Toast;

/**
 * Created by joker on 2017/11/25.
 */

public class surface extends SurfaceView implements SurfaceHolder.Callback{
    private  SurfaceHolder holder;
    private Mythread mythread;
    private gesture mgesture;
    private GestureDetector mgestureDetector;
    private float drawX;
    private float drawY;
    private Canvas canvas=null;
    int count=0;
    public surface(Context context) {
        this(context,null);
    }
    public surface(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        drawX=400;
        drawY=400;
        holder=this.getHolder();
        holder.addCallback(this);
        mythread=new Mythread(holder);
        mgesture=new gesture();
        mgestureDetector=new GestureDetector(context,mgesture);
        Log.d("get","context is :"+context);
//        this.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mythread.isRun=!mythread.isRun;
//                if(mythread.isRun)
//                    mythread.start();
//            }
//        });
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder,int format,int width,int heigth){
        Log.d("get","surfaceChanged");

    }
    @Override
    public void surfaceCreated(SurfaceHolder holder){
        Log.d("get","surfaceCreated");
        mythread.isRun=true;
        mythread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mythread.isRun=false;
        Log.d("get","surfaceDestroyed");
    }
    public void draws(Canvas canvas){
        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.BLUE);
        canvas.drawText("this is" + count, drawX, drawY, paint);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mgestureDetector.onTouchEvent(event);
        return true;
    }
    class gesture implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener{
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d("get","distanceX :"+distanceX);
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.d("get","onDoubleTap"+e.getX());
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d("get","velocityX:"+velocityX);
            return true;
        }
    }
    class Mythread extends Thread{
        private final SurfaceHolder holder;
        private boolean isRun=false;
        private Mythread(SurfaceHolder holder){
            this.holder=holder;
            isRun=true;
        }
        public void run() {
            while(isRun){
                try {
                    synchronized (holder) {
                        canvas = holder.lockCanvas();
                        Log.d("get","count="+count);
                        if (canvas != null) {
                            draws(canvas);
                            Thread.sleep(1000);
                            count=count+1;
                        }
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    if(canvas!=null){
                    holder.unlockCanvasAndPost(canvas);
                    }
                }
            }

        }
    }
}
