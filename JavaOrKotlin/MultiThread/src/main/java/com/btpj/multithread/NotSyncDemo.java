package com.btpj.multithread;

/**
 * 不使用同步的方式执行
 *
 * @author LTP 2021/12/1
 */
public class NotSyncDemo {
    static final NotSyncDemo mNotSyncDemo = new NotSyncDemo();
    private static int mNum = 1000000000;


    /**
     * 不同步的方式减1
     */
    private void reduce() {
        mNum--;
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
                System.out.println("不同步方式执行线程加锁完毕，结果为" + mNum + "，耗时" + (System.currentTimeMillis() - startTime) + "ms");
                break;
            }
        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                mNotSyncDemo.reduce();
            }

        }
    }

    public static void main(String[] args) {
        mNotSyncDemo.execute(); // 8s左右
    }
}