package produce_consume;

import produce_consume.bean.Product;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 阻塞队列实现生产者消费者模式
 *
 * @author BTPJ  2022/2/22
 */
public class ProduceConsume {
    /** 容量为10的有节队列 */
    private static final BlockingQueue<Product> mQueue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {
        Producer p = new Producer();
        Consumer c1 = new Consumer();
        Consumer c2 = new Consumer();
        new Thread(p).start();
        new Thread(c1, "消费者1").start();
        new Thread(c2, "消费者2").start();
    }

    static class Producer implements Runnable {
        public void run() {
            try {
                while (true) {
                    mQueue.put(produce());
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        /**
         * 生产产品
         *
         * @return Product
         * @throws InterruptedException InterruptedException
         */
        Product produce() throws InterruptedException {
            // 模拟生产耗时
            Thread.sleep(500);
            String name = "产品" + new Random().nextInt(100);
            System.out.println("生产者生产：" + name + ",产品个数为" + mQueue.size());
            return new Product(name);
        }
    }

    static class Consumer implements Runnable {

        public void run() {
            try {
                while (true) {
                    consume(mQueue.take());
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        void consume(Product product) throws InterruptedException {
            // 模拟消费耗时
            Thread.sleep(1500);
            System.out.println(Thread.currentThread().getName() + "消费:" + product.getName());
        }
    }
}
