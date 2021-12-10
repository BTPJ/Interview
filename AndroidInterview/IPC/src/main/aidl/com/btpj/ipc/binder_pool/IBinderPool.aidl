// IBinderPool.aidl
package com.btpj.ipc.binder_pool;

interface IBinderPool {

    IBinder queryBinder(int binderCode);
}