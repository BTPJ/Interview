// IOnNewBookArrivedListener.aidl
package com.btpj.ipc.aidl;
import com.btpj.ipc.aidl.Book;

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book book);
}