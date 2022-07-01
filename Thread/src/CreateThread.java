import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 多线程的创建方式
 *
 * @author BTPJ  2022/2/15
 */
public class CreateThread {

    public static void main(String[] args) {
        System.out.println("当前线程：" + Thread.currentThread().getName());
        // 继承Thread类的方式创建并启动线程
        MyThread myThread = new MyThread();
        myThread.start();

        // 实现Runnable接口的方式创建并启动线程
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable).start();

        // 实现Callable接口的方式创建并启动线程
        MyCallable myCallable = new MyCallable();
        // 需要借助FutureTask
        FutureTask<String> futureTask = new FutureTask<String>(myCallable) {
            @Override
            protected void done() {
                System.out.println("线程执行前");
                super.done();
                System.out.println("线程执行后");
                try {
                    System.out.println("done获取Call方法的返回值：" + get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }

            }
        };
        new Thread(futureTask).start();

        try {
            System.out.println("外部获取Call方法的返回值"+futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            System.out.println("继承Thread类的方式创建线程：" + Thread.currentThread().getName());
        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("实现Runnable接口的方式创建线程：" + Thread.currentThread().getName());
        }
    }

    static class MyCallable implements Callable<String> {
        @Override
        public String call() {
            System.out.println("实现Callable接口的方式创建线程：" + Thread.currentThread().getName());
            return "hello，Callable";
        }
    }
}
