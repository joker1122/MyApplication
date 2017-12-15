// IMyAidlInterface.aidl
package com.example.joker.myapplication;

// Declare any non-default types here with import statements
//import com.example.joker.myapplication.Book.aidl;
import com.example.joker.myapplication.Book;

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void insert(in Book book);
    Book get(int id);
}
