package wait_notify;

import wait_notify.bean.Message;

/**
 * 无限等待线程
 *
 * @author BTPJ  2022/2/23
 */
public class WaitThread extends Thread {
    private final Message message;

    public WaitThread(String name, Message message) {
        super(name);
        this.message = message;
    }

    @Override
    public void run() {
        super.run();
        String threadName = Thread.currentThread().getName();
        synchronized (message) {
            try {
                System.out.println(threadName + "使用wait()在等待");
                message.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + "被notify()通知唤醒，等待完毕");
        }
    }
}
