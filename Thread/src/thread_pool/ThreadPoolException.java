package thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolException {

    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(3);
//        for (int i = 0; i < 5; i++) {
//            executorService.submit(() -> {
//                Object object = null;
//                try {
//                    object.toString();
//                } catch (Exception e) {
//                   System.out.println("当前执行线程：" + Thread.currentThread().getName() + " 抛出异常：" + e);
//                }
//            });
//        }

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            Future<?> future = executorService.submit(() -> {
                System.out.print("当前执行线程" + Thread.currentThread().getName());
                Object object = null;
                object.toString();
            });

            try {
                future.get();
            } catch (Exception e) {
                System.out.println(",抛出异常：" + e);
            }
        }

//        ExecutorService executorService = Executors.newFixedThreadPool(3, thread -> {
//            Thread thread1 = new Thread(thread);
//            thread1.setUncaughtExceptionHandler((t1, e) -> {
//                System.out.println("当前执行线程" + t1.getName() + "抛出异常：" + e);
//            });
//            return thread1;
//        });
//
//        for (int i = 0; i < 5; i++) {
//            executorService.execute(() -> {
//                Object object = null;
//                object.toString();
//            });
//        }
    }
}
