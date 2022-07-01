// IBookManager.aidl
package com.btpj.ipc.aidl;
import com.btpj.ipc.aidl.Book;
import com.btpj.ipc.aidl.IOnNewBookArrivedListener;

interface IBookManager {
    List<Book> getBookList();

    void addBook(in Book book);

    void registerListener(IOnNewBookArrivedListener listener);

    void unRegisterListener(IOnNewBookArrivedListener listener);
}