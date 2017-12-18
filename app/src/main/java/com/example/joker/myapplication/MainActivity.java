package com.example.joker.myapplication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;

import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.IBinder;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager=null;
    FragmentTransaction fragmentTransaction=null;
    ImageView imageView=null;
    surface view=null;
    Button button=null;
    public ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IMyAidlInterface.Stub stub=(IMyAidlInterface.Stub)service;
            Book book=new Book();
            book.setBookID(1);
            book.setBookNane("Android");
            try{
                stub.insert(book);
                Log.d("get",stub.get(1).getBookNane());
            }catch (RemoteException e){
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
//        setContentView(R.layout.activity_main);
//        testview mtestview=(testview)findViewById(R.id.m_testview);

//        setContentView(R.layout.layout2);
//        init();

//        setContentView(R.layout.activity_main);
//        init();
//        Log.d("get","activity:"+getCallingActivity());


//        setContentView(R.layout.layout);
//        view=(surface)findViewById(R.id.surface_test);
//        button=(Button)findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Notification.Builder builder=new Notification.Builder(MainActivity.this);
//                builder.setSmallIcon(R.drawable.ic_launcher_background);
//                RemoteViews views=new RemoteViews(getPackageName(),R.layout.my_widget);
//                builder.setCustomContentView(views);
//                PendingIntent intent=PendingIntent.getActivity(MainActivity.this,0,new Intent(MainActivity.this,Main2Activity.class),PendingIntent.FLAG_UPDATE_CURRENT);
//                builder.setContentIntent(intent);
//                NotificationManager manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//                manager.notify(1,builder.build());
//            }
//        });
//        view.setZOrderOnTop(true);
//        view.setZOrderMediaOverlay(true);

        setContentView(R.layout.layout3);
        overridePendingTransition(R.anim.animation,R.anim.animation);
        imageView=(ImageView)findViewById(R.id.image11);

//        test view=new test(getApplicationContext(),null);
//        setContentView(view);
//        TextView textView=(TextView)findViewById(R.id.test);
    }
    public void init(){
        fragmentManager=getFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragment1 f1=new fragment1();
        fragmentTransaction.replace(R.id.frame_layout,f1);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final Animation animation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.animation);

//        AnimationDrawable drawable=(AnimationDrawable)imageView.getBackground();
//        drawable.start();

//        final animation manima=new animation();
//        manima.setDuration(1000);
        imageView.startAnimation(animation);


        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        overridePendingTransition(R.anim.animation,R.anim.animation);
    }
}
