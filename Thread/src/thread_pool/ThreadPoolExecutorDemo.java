package thread_pool;

import sun.nio.ch.ThreadPool;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.concurrent.*;

public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
//        scheduledExecutorService.scheduleWithFixedDelay(() -> {
//            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
//            String currentTime = formatter.format(Calendar.getInstance().getTime());
//            System.out.println(Thread.currentThread().getName() + "执行,时间为：" + currentTime);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }, 1, 3, TimeUnit.SECONDS);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20,
                10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        for (int i = 1; i < 50; i++) {
            int finalI = i;
            threadPoolExecutor.execute(() -> {
                try {
                    System.out.println("当前执行线程：" + finalI);
                    Thread.sleep(1000);
                    System.out.print("-------------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
