package com.example.joker.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by joker on 2017/11/23.
 */

public class Book implements Parcelable {
    private String BookNane;
    private int BookID;
    private static int describe=1235;

    public void setBookNane(String bookNane){
       BookNane=bookNane;
    }
    public void setBookID(int id){
       BookID=id;
    }
    public String getBookNane(){
       return BookNane;
    }
    public int getBookID(){
       return BookID;
    }
    public int describeContents(){
       return Parcelable.CONTENTS_FILE_DESCRIPTOR;
    }

    public void writeToParcel(Parcel dest, int flags){
       dest.writeString(getBookNane());
       dest.writeInt(getBookID());
    }
    public static final Parcelable.Creator<Book> CREATOR=new Creator<Book>() {
       @Override
       public Book createFromParcel(Parcel source) {
           Book book=new Book();
           book.setBookNane(source.readString());
           book.setBookID(source.readInt());
           return book;
       }

       @Override
       public Book[] newArray(int size) {
           return new Book[size];
       }
    };
}
