package com.example.joker.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.ArrayList;

public class MyService extends Service {
    private ArrayList<Book> bookArrayList;
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
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return binder;
    }
}
