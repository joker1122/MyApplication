package com.example.joker.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by joker on 2017/11/30.
 */

public class fragment2 extends Fragment {
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d("get","onHiddenChanged2");
    }

    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);
        Log.d("get","onInflate2");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("get","onCreate2");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("get","onCreateView2");
        View view;
        view=inflater.inflate(R.layout.fragment2,container);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("get","onStart2");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("get","onResume2");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("get","onPause2");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("get","onStop2");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("get","onDestroy2");
    }
}
