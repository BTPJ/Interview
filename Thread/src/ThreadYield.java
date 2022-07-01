/**
 * 线程礼让
 *
 * @author BTPJ  2022/2/16
 */
public class ThreadYield {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            for (int i = 0; i < 4; i++) {
                System.out.println(Thread.currentThread().getName() + "执行到：" + i);
                if (i == 2) {
                    Thread.yield();
                }
            }
        };

        Thread thread1 = new Thread(runnable,"线程1");
        Thread thread2 = new Thread(runnable,"线程2");
        Thread thread3 = new Thread(runnable,"线程3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
