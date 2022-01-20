import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 采用线程池 + Synchronized关键字
 *
 * @author LTP 2021/12/1
 */
public class ExecutorDemo {
    static final ExecutorDemo mExecutorDemo = new ExecutorDemo();
    private static int mNum = 1000000000;

    /**
     * 采用类锁的方式加锁，普通方法锁的是对象，当多个对象时无用,这里由于只有一个SynchronizedDemo对象，故都可以
     */
    private void reduce() {
        synchronized (ExecutorDemo.class) {
            mNum--;
        }
    }

    /**
     * 执行
     */
    public void execute() {
        // 使用线程池开启1000个线程执行
        long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new MyRunnable());
        }
        executorService.shutdown();
        while (true) {
            //  if (Thread.activeCount() == 1) {
            if (executorService.isTerminated()) {
                System.out.println("线程池方式执行线程加Synchronized锁完毕，结果为" + mNum + "，耗时" + (System.currentTimeMillis() - startTime) + "ms");
                break;
            }
        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                mExecutorDemo.reduce();
            }
        }
    }

    public static void main(String[] args) {
        mExecutorDemo.execute(); // 13s左右
    }
}
