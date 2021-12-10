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
    private static final AtomicInteger atomicInteger = new AtomicInteger(1000000000);

    /**
     * 每次减一
     */
    private void reduce() {
        atomicInteger.decrementAndGet();
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
                System.out.println("普通方式执行线程加Atomic方式完毕，结果为" + atomicInteger + "，耗时" + (System.currentTimeMillis() - startTime) + "ms");
                break;
            }
        }
    }


    static class MyRunnable implements Runnable {

        @Override
        public void run() {

            // 子线程中做10000次reduce操作
            for (int i = 0; i < 1000000; i++) {
                mAtomicDemo.reduce();
            }

        }
    }

    public static void main(String[] args) {
        mAtomicDemo.execute(); // 21s左右
    }
}
