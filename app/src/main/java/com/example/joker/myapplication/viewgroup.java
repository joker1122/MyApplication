package com.example.joker.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by joker on 2017/12/4.
 */

public class viewgroup extends ViewGroup {
    public viewgroup(Context context) {
        super(context);
    }

    public viewgroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean isClickable() {
        return super.isClickable();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
    }

    @Override
    protected void onLayout(boolean changed,int l, int t, int r, int b){

    }

}
