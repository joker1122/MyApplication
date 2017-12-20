package com.example.joker.myapplication;

import android.view.animation.Interpolator;

/**
 * Created by joker on 2017/12/19.
 */

public class interpolator implements Interpolator {
    public interpolator() {
    }

    @Override
    public float getInterpolation(float input) {
        return (float)(Math.sin(input+Math.PI/2-1));
    }
}
