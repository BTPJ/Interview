package wait_notify;

import wait_notify.bean.Message;

/**
 * Wait和Notify方法使用Demo
 *
 * @author BTPJ  2022/2/23
 */
public class WaitNotifyDemo {

    public static void main(String[] args) {
        Message msg = new Message("消息");
        WaitThread waitThread1 = new WaitThread("等待线程A", msg);
        waitThread1.start();

        WaitTimeThread waitTimeThread = new WaitTimeThread("等待线程B", msg);
        waitTimeThread.start();

        NotifyThread notifyThread = new NotifyThread("唤醒线程", msg);
        notifyThread.start();
    }
}
