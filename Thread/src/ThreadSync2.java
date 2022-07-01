/**
 * 线程同步，synchronized对象锁
 *
 * @author BTPJ  2022/2/16
 */
public class ThreadSync2 {

    public static void main(String[] args) {
        Thread thread1 = new MyThreadA("类锁线程1", new ThreadSync2());
        Thread thread2 = new MyThreadB("类锁线程2", new ThreadSync2());
        ThreadSync2 threadSync2 = new ThreadSync2();
        Thread thread3 = new MyThreadC("对象锁线程1", threadSync2);
        Thread thread4 = new MyThreadD("对象锁线程2", threadSync2);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

    /**
     * 类锁1，synchronized应用于静态方法实现类锁
     */
    public synchronized static void classLock1() {
        System.out.println(Thread.currentThread().getName() + ", 开始运行，并获得ThreadLock类锁");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ", 结束运行，并释放ThreadLock类锁");
    }

    /**
     * 类锁2，synchronized应用于代码块（后面括号中放置的是ThreadLock.class类对象）实现类锁
     */
    public static void classLock2() {
        synchronized (ThreadSync2.class) {
            System.out.println(Thread.currentThread().getName() + ", 开始运行，并获得ThreadLock类锁");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ", 结束运行，并释放ThreadLock类锁");
        }
    }

    /**
     * 对象锁1，synchronized应用于普通方法实现对象锁
     */
    public synchronized void objectLock1() {
        System.out.println(Thread.currentThread().getName() + ", 开始运行，并获得ThreadLock对象锁");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ", 结束运行，并释放ThreadLock对象锁");
    }

    /**
     * 对象锁2,synchronized应用于代码块（后面括号中放置的是this,表示类的对象）实现对象锁
     */
    public void objectLock2() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + ", 开始运行，并获得ThreadLock对象锁");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ", 结束运行，并释放ThreadLock对象锁");
        }
    }

    static class MyThreadA extends Thread {
        private final ThreadSync2 threadSync2;

        public MyThreadA(String name, ThreadSync2 threadSync2) {
            super(name);
            this.threadSync2 = threadSync2;
        }

        @Override
        public void run() {
            super.run();
            threadSync2.classLock1();
        }
    }

    static class MyThreadB extends Thread {
        private final ThreadSync2 threadSync2;

        public MyThreadB(String name, ThreadSync2 threadSync2) {
            super(name);
            this.threadSync2 = threadSync2;
        }

        @Override
        public void run() {
            super.run();
            threadSync2.classLock2();
        }
    }

    static class MyThreadC extends Thread {
        private final ThreadSync2 threadSync2;

        public MyThreadC(String name, ThreadSync2 threadSync2) {
            super(name);
            this.threadSync2 = threadSync2;
        }

        @Override
        public void run() {
            super.run();
            threadSync2.objectLock1();
        }
    }

    static class MyThreadD extends Thread {
        private final ThreadSync2 threadSync2;

        public MyThreadD(String name, ThreadSync2 threadSync2) {
            super(name);
            this.threadSync2 = threadSync2;
        }

        @Override
        public void run() {
            super.run();
            threadSync2.objectLock2();
        }
    }

}
