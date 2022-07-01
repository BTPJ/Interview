package wait_notify;

import wait_notify.bean.Message;

/**
 * 唤醒线程
 *
 * @author BTPJ  2022/2/23
 */
public class NotifyThread extends Thread {
    private final Message message;

    public NotifyThread(String name, Message message) {
        super(name);
        this.message = message;
    }

    @Override
    public void run() {
        super.run();
        String threadName = Thread.currentThread().getName();
        synchronized (message) {
            System.out.println(threadName + "使用notify()唤醒线程");
            message.notify(); // 使用notify将唤醒一个线程
            // message.notifyAll(); // 如果使用notifyAll，将唤醒所有等待的线程
        }
    }
}
