/**
 * volatile：可见性、不保证原子性、防止指令重排
 *
 * @author LTP  2021/12/1
 */
public class VolatileDemo {

    private boolean mFlag = true;
    // volatile针对轻量级的赋值操作同步有用
//    private volatile boolean mFlag = true;

    private void task1() {
        new Thread(() -> {
            while (mFlag) {
                // 注意这里不要放任何耗时操作，即使是打印也不行，只看task1线程会不会3s后自动停止
                // System.out.print("BTPJ，");
            }
        }).start();
    }

    private void task2() {
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                mFlag = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        VolatileDemo volatileDemo = new VolatileDemo();
        volatileDemo.task1();
        volatileDemo.task2();
    }
}
