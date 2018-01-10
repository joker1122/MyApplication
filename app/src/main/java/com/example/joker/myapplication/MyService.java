package com.example.joker.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;

public class MyService extends Service {
    private ArrayList<Book> bookArrayList;
    Looper mLooper=null;
    Handler mhandler=null;
    public MyService() {
        bookArrayList=new ArrayList<>();
    }
    IBinder binder=new IMyAidlInterface.Stub() {
        @Override
        public void insert(Book book) throws RemoteException {
            bookArrayList.add(book);
        }

        @Override
        public Book get(int id) throws RemoteException {
            for (Book book:bookArrayList){
                if (book.getBookID()==id)
                    return book;
            }
            return null;
        }
    };
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
