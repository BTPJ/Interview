package wait_notify;

import wait_notify.bean.Message;

/**
 * 等待3s自动释放的线程，当然如果有notify()唤醒该线程，他也会提前释放
 *
 * @author BTPJ  2022/2/23
 */
public class WaitTimeThread extends Thread {
    private final Message message;

    public WaitTimeThread(String name, Message message) {
        super(name);
        this.message = message;
    }

    @Override
    public void run() {
        super.run();
        String threadName = Thread.currentThread().getName();
        synchronized (message) {
            try {
                System.out.println(threadName + "使用wait(3000)在等待3s");
                message.wait(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + " 3s结束自动唤醒，等待完毕");
        }
    }
}
