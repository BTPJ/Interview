import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock锁实现线程同步，卖火车票的问题
 *
 * @author BTPJ  2022/2/16
 */
public class ThreadLock {
    /**
     * 总共100张票
     */
    private static int mTickets = 100;

    private static Lock mLock = new ReentrantLock();

    public static void main(String[] args) {
//        Thread thread1 = new MyThread("窗口1");
//        Thread thread2 = new MyThread("窗口2");
//        Thread thread3 = new MyThread("窗口3");
//        thread1.start();
//        thread2.start();
//        thread3.start();

        ThreadLock threadLock = new ThreadLock();
        threadLock.add();
//        add();
    }

    private int count = 0;

    private void add() {
        mLock.lock();
        try {
            count++;
            System.out.print(count + " ");
            if (count < 10) {
                add();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mLock.unlock();
        }
    }

    /**
     * 卖火车票
     */
    private static void sellTicket() {
        // 总共1000张票
        mLock.lock();
        try {
            while (mTickets > 0) {
                // 模拟卖票耗时
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + "正在卖第" + mTickets + "张票");
                mTickets--;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mLock.unlock();
        }
    }

    static class MyThread extends Thread {
        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            sellTicket();
        }
    }
}
