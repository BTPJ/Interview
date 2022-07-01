/**
 * 线程同步，卖火车票的问题
 *
 * @author BTPJ  2022/2/16
 */
public class ThreadSync {
    /**
     * 总共100张票
     */
    private static int mTickets = 100;

    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyRunnable(), "窗口1");
        Thread thread2 = new Thread(new MyRunnable(), "窗口2");
        Thread thread3 = new Thread(new MyRunnable(), "窗口3");
        thread1.start();
        thread2.start();
        thread3.start();
    }

    /**
     * 卖火车票
     */
    private static void sellTicket() {
        // 也可以在代码块中使用类锁
        synchronized (ThreadSync.class) {
            // 总共1000张票
            while (mTickets > 0) {
                System.out.println(Thread.currentThread().getName() + "正在卖第" + mTickets + "张票");
                mTickets--;
                try {
                    // 模拟卖票耗时
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            sellTicket();
        }
    }
}
