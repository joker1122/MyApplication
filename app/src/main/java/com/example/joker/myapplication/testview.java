package com.example.joker.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Scroller;

import java.lang.reflect.Method;
import java.util.logging.LogRecord;

/**
 * Created by joker on 2017/11/15.
 */

public class testview extends View {
    private Paint paint;
    private int mheight;
    private int mwidth;
    private float mleft;
    private float mtop;
    private float mbutton;
    private float mright;
    private Bitmap night;
    private Scroller mscroller;
    public testview(Context context) {
        this(context,null);
    }

    public testview(Context context,AttributeSet attrs) {
        super(context, attrs);
//        night= BitmapFactory.decodeFile("night.jpg");
        night= BitmapFactory.decodeResource(getResources(),R.drawable.night);
        paint=new Paint();
        mscroller=new Scroller(context);
        paint.setColor(Color.BLUE);
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        Log.d("get","onFocusChanged:"+previouslyFocusedRect);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawBitmap(night,mleft,mtop,paint);
        canvas.drawCircle(getWidth()/2,getHeight()/2,(Math.min(getWidth(),getHeight()))/2,paint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d("get","onLayout--left:"+left+"---top:"+top+"--rigth:"+right+"--button:"+bottom);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d("get","onFinishInflate");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthspaceMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthspaceSize=MeasureSpec.getSize(widthMeasureSpec);
        int heigthspaceMode=MeasureSpec.getMode(heightMeasureSpec);
        int heigthspaceSize=MeasureSpec.getSize(heightMeasureSpec);
        if(widthspaceMode==MeasureSpec.AT_MOST &&heigthspaceMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(200,200);
            Log.d("get","All");
        }else if(widthMeasureSpec==MeasureSpec.AT_MOST){
            setMeasuredDimension(200,heigthspaceSize);
            Log.d("get","width");
        }else if(heightMeasureSpec==MeasureSpec.AT_MOST){
            setMeasuredDimension(widthspaceSize,200);
            Log.d("get","heigth");
        }
        Log.d("get","widthMeasureSpec:"+widthMeasureSpec+"   heightMeasureSpec:"+heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d("get","onSizeChanged");
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                scrollY((int)event.getX(),(int)event.getY());
                break;
            case MotionEvent.ACTION_BUTTON_PRESS:
                break;
            case MotionEvent.ACTION_MOVE:
                mleft=event.getX();
                mtop=event.getY();
                WindowManager.LayoutParams params=(WindowManager.LayoutParams)getLayoutParams();
                params.x=(int)mleft;
                params.y=(int)mtop;
                break;
        }
//        invalidate();
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                return onTouchEvent(event);
            case MotionEvent.ACTION_MOVE:
                getParent().requestDisallowInterceptTouchEvent(false);
        }
        return super.dispatchTouchEvent(event);
    }

    public void scrollY(int desX, int desY){
        int startX=getScrollX();
        int startY=getScrollY();
        Log.d("get","startY:"+startY+"   desY:"+desY);
        if(getWidth()<getHeight()){
            int point=(getHeight()/2)-startY;
            if(desY>point)
                mscroller.startScroll(0,startY,0,(point+getWidth()/2)-desY,1000);
            else
                mscroller.startScroll(0,startY,0,(point-getWidth()/2)-desY,1000);
        }else{
            int point=(getWidth()/2)-startX;
            if(desX>point)
                mscroller.startScroll(startX,0,(point+getHeight()/2)-desX,0,1000);
            else
                mscroller.startScroll(startX,0,(point-getHeight()/2)-desX,0,1000);
        }
        invalidate();
    }
    @Override
    public void computeScroll() {
        if(mscroller.computeScrollOffset()){
            scrollTo(mscroller.getCurrX(),mscroller.getCurrY());
            postInvalidate();
        }
    }
}
