package com.btpj.multithread;

/**
 * 使用Synchronized关键字实现同步
 *
 * @author LTP 2021/12/1
 */
public class SynchronizedDemo {
    static final SynchronizedDemo mSynchronizedDemo = new SynchronizedDemo();
    private static int mNum = 1000000000;

    /**
     * 采用类锁的方式加锁，普通方法锁的是对象，当多个对象时无用,这里由于只有一个SynchronizedDemo对象，故都可以
     */
    private void reduce() {
        synchronized (AtomicDemo.class) {
            mNum--;
        }
    }

    /**
     * 执行
     */
    public void execute() {
        // 普通方式开启1000个线程执行
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            new Thread(new MyRunnable()).start();
        }
        while (true) {
            if (Thread.activeCount() == 1) {
                System.out.println("普通方式执行线程加Synchronized锁完毕，结果为" + mNum + "，耗时" + (System.currentTimeMillis() - startTime) + "ms");
                break;
            }
        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                mSynchronizedDemo.reduce();
            }

        }
    }

    public static void main(String[] args) {
        mSynchronizedDemo.execute(); // 12s左右
    }
}