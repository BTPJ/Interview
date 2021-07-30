// ISecurityCenter.aidl
package com.btpj.ipc.binder_pool;

interface ISecurityCenter {
    String encrypt(String content);
    String decrypt(String password);
}