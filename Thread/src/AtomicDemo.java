import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * java.util.concurrent下面的原子操作，包括AtomicInteger、AtomicLong、AtomicBoolean等
 * 采用CAS原子组件的方式进行同步
 * CAS存在的问题：
 * 1.CPU占用高，高并发不适合
 * 2.复杂操作不适合
 * 3.无法避免ABA问题
 *
 * @author LTP 2021/12/1
 */
public class AtomicDemo {
    static final AtomicDemo mAtomicDemo = new AtomicDemo();

    //使用atomicInteger包装后进行原子操作能使得线程同步
    private static final AtomicInteger atomicInteger = new AtomicInteger(100000);
    private static int i = 100000;

    /**
     * 每次减一
     */
    private void reduce() {
        atomicInteger.decrementAndGet();
        i--;
    }

    /**
     * 执行
     */
    public void execute() {
        // 开启100个线程放入线程池执行
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new MyThread());
        }
        executorService.shutdown();
        while (true) {
            // 线程池中线程执行完毕
            if (executorService.isTerminated()) {
                System.out.println("Atomic方式执行完毕，结果为" + atomicInteger);
                System.out.println("普通方式执行完毕，结果为" + i);
                break;
            }
        }
    }


    static class MyThread extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 子线程中做10000次reduce操作
            for (int i = 0; i < 1000; i++) {
                mAtomicDemo.reduce();
            }
        }
    }

    public static void main(String[] args) {
        mAtomicDemo.execute(); // 21s左右
    }
}
