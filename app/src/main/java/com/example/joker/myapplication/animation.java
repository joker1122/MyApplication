package com.example.joker.myapplication;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by joker on 2017/12/13.
 */

public class animation extends Animation {
    private Camera camera;
    private float locationX;
    private float locationY;
    private float locationZ;
    private float degree;

    public animation(float locationX,float locationY,float locationZ,float degree) {
        this.locationX=locationX;
        this.locationY=locationY;
        this.locationZ=locationZ;
        this.degree=degree;
    }

    public animation(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        camera=new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final Camera mcamera=camera;
        final Matrix matrix=t.getMatrix();
        mcamera.save();
//        mcamera.translate(300*interpolatedTime,300*interpolatedTime,300*interpolatedTime);
        mcamera.rotateZ(degree*interpolatedTime);
        mcamera.getMatrix(matrix);
        mcamera.restore();
//        float degree=360*interpolatedTime;
//        float f[]={
//                (float) Math.cos(degree),-0.5F,0,
//                0.5F,0.5F,0,
//                0,0,1
//        };
//        t.getMatrix().setValues(f);
//        t.getMatrix().preRotate(degree,300,300);
//        t.getMatrix().preRotate(degree);

    }
}
