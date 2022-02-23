/**
 * 线程中断
 *
 * @author BTPJ  2022/2/15
 */
public class StopThread {
    private static int mCount = 0;
    private static boolean mRecycler = true;

    public static void main(String[] args) throws InterruptedException {
//        Thread thread = new Thread(new MyRunnable());
//        thread.start();
//        // 延迟三秒中断线程
//        Thread.sleep(3000);
//        // 调用interrupt方法请求中断线程
//        thread.interrupt();

        Thread thread2 = new Thread(new MyRunnable2());
        thread2.start();
        // 延迟三秒中断线程
        Thread.sleep(3000);
        // 修改标志位
        mRecycler = false;
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            // 循环条件为当前线程不被中断
            while (!Thread.currentThread().isInterrupted()) {
                mCount++;
                System.out.println("当前count值：" + mCount);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // 捕获到异常后需再次调用interrupt方法请求中断线程
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
            System.out.println("线程执行已结束");
        }
    }

    static class MyRunnable2 implements Runnable {
        @Override
        public void run() {
            // 循环条件为当前线程不被中断
            while (mRecycler) {
                mCount++;
                System.out.println("当前count值：" + mCount);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程执行已结束");
        }
    }
}
