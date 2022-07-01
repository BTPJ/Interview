/**
 * 线程串行
 *
 * @author BTPJ  2022/2/15
 */
public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "执行开始");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行结束");
        }, "线程1");

        Thread thread2 =  new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "执行开始");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行结束");
        }, "线程2");

        Thread thread3 =  new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "执行开始");
            try {
                Thread.sleep(3000);
                Thread.yield();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行结束");
        }, "线程3");

        // 分别开启
        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        thread3.start();
    }
}
