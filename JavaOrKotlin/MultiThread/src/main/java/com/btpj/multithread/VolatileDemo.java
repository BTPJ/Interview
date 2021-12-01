package com.btpj.multithread;

/**
 * volatile：可见性、不保证原子性、防止指令重排
 *
 * @author LTP  2021/12/1
 */
public class VolatileDemo {

    //    private boolean mFlag = true;
    // volatile针对轻量级的赋值操作同步有用
    private volatile boolean mFlag = true;

    private void task1() {
        new Thread(() -> {
            while (mFlag) {
                // System.out.print("BTPJ，"); 里面做一些稍微耗时的即使不加Volatile关键字也会停止
            }
        }).start();
    }

    private void task2() {
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mFlag = false;
        }).start();
    }

    public static void main(String[] args) {
        VolatileDemo volatileDemo = new VolatileDemo();
        volatileDemo.task1();
        volatileDemo.task2();
    }
}
