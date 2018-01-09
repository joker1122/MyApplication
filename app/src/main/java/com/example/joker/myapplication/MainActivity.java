package com.example.joker.myapplication;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.PixelFormat;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;


/* http://blog.csdn.net/xsf50717/article/details/50472341  */
/*  http://www.jianshu.com/p/34e0fe5f9e31  */
/*  https://www.jianshu.com/p/6afb0c17df43 */
/*  http://blog.csdn.net/i_lovefish/article/details/8050025   */
public class MainActivity extends AppCompatActivity {

//    final ThreadLocal <mhandle> hd=null;
    FragmentManager fragmentManager=null;
    FragmentTransaction fragmentTransaction=null;
    ImageView imageView=null;
    surface view=null;
    Button button=null;
    testview mtest=null;
    private buttonview mbuttonview;
    final AnimatorSet manimatorset=null;
    ObjectAnimator mvalueanimator=null;
    WindowManager mwindowmanager=null;
    WindowManager.LayoutParams mlayoutparams=null;
    mybroadcastreceiver mb=null;
    mhandle hd=null;
    Handler mhandle=null;
    public ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IMyAidlInterface myAidlInterface=IMyAidlInterface.Stub.asInterface(service);
            Book book=new Book();
            book.setBookID(1);
            book.setBookNane("Android");
            try{
                myAidlInterface.insert(book);
                Log.d("get",myAidlInterface.get(1).getBookNane());
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
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
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
        mwindowmanager=getWindowManager();
        imageView=(ImageView)findViewById(R.id.image11);
        button=(Button)findViewById(R.id.mbutton);

        IntentFilter mfilter=new IntentFilter();
        mfilter.addAction("hello");
        mb=new mybroadcastreceiver();
        registerReceiver(mb,mfilter);
        final Intent mint=new Intent();
        mint.setAction("hello");

        mhandle=new Handler();

        hd=new mhandle();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"width:"+v.getWidth(),Toast.LENGTH_SHORT).show();
                mtest=new testview(MainActivity.this);
                mlayoutparams=new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,0,0, PixelFormat.TRANSPARENT);
                mlayoutparams.flags= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
                mlayoutparams.type=WindowManager.LayoutParams.TYPE_APPLICATION;
                mlayoutparams.x=0;
                mlayoutparams.y=0;
                mwindowmanager.addView(mtest,mlayoutparams);
                sendBroadcast(mint);
                button.setEnabled(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            Thread.currentThread().sleep(3000);
                            Log.d("get","loop    "+Looper.myLooper());
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
//                        Message message=Message.obtain();
//                        message.what=1;
//                        hd.sendMessage(message);
                        mhandle.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("get","mhandle    "+Thread.currentThread().getName());
                            }
                        });
                    }
                }).start();
            }
        });
        mbuttonview=new buttonview(button);

        Intent mintent=new Intent(this,MyService.class);
        bindService(mintent,serviceConnection,Context.BIND_AUTO_CREATE);

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
//        final Animation animation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.animation);
//
//        AnimationDrawable drawable=(AnimationDrawable)imageView.getBackground();
//        drawable.start();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                final animation manima=new animation(imageView.getLayoutParams().width/2,imageView.getLayoutParams().height/2,-8,360);
                manima.setDuration(2000);
                manima.setFillAfter(true);

                imageView.startAnimation(manima);
//        ObjectAnimator.ofFloat(imageView,"translationX",imageView.getWidth()/2).start();
                AnimatorSet mset=new AnimatorSet();
                mvalueanimator=ObjectAnimator.ofArgb(button,"backgroundColor",0xFFFF8080,0xFF8080FF);
                mvalueanimator.setDuration(3000);
                mvalueanimator.setRepeatCount(ValueAnimator.INFINITE);
                mvalueanimator.setRepeatMode(ValueAnimator.REVERSE);
                mvalueanimator.setEvaluator(new evalutor());
                mvalueanimator.setInterpolator(new interpolator());
//        mvalueanimator.start();

                ObjectAnimator mvalueanimator1=ObjectAnimator.ofInt(mbuttonview,"width",button.getWidth(),button.getWidth()/2);
                mvalueanimator1.setDuration(2000);

                mset.playTogether(mvalueanimator,mvalueanimator1);
                mset.start();
                break;
            case MotionEvent.ACTION_MOVE:
                if(mlayoutparams!=null){
                    Log.d("get","msg"+event.getX()+"---"+event.getY());
                    mlayoutparams.x=(int)event.getX();
                    mlayoutparams.y=(int)event.getY();
                    mwindowmanager.updateViewLayout(mtest,mlayoutparams);
                }
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.animation,R.anim.animation);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mvalueanimator!=null && mvalueanimator.isRunning()){
            mvalueanimator.pause();
        }
        if(mtest!=null){
            mwindowmanager.removeView(mtest);
            mtest=null;
        }
        if(mb!=null){
           unregisterReceiver(mb);
        }
        if(serviceConnection!=null) {
            unbindService(serviceConnection);
        }
    }

    class buttonview{
        View mview;
        public buttonview(View view){
            mview=view;
        }
        public void setWidth(int width){
            mview.getLayoutParams().width=width;
            mview.requestLayout();
        }
        public int getWidth(){
            return mview.getLayoutParams().width;
        }
    }
    class mhandle extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    if(mtest!=null){
                        mwindowmanager.removeView(mtest);
                        mtest=null;
                    }
                    break;
                default:
                    break;
            }
        }
    }
    class mybroadcastreceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
                Log.d("get","hello");
        }
    }
}
