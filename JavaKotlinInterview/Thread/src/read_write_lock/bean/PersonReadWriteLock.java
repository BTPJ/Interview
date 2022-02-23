package read_write_lock.bean;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 使用ReadWriteLock读写锁方式
 *
 * @author BTPJ  2022/2/22
 */
public class PersonReadWriteLock implements IPerson {
    private String name = "";
    private int age = 0;

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    /**
     * 读锁
     */
    private final Lock readLock = readWriteLock.readLock();
    /**
     * 写锁
     */
    private final Lock writeLock = readWriteLock.writeLock();

    @Override
    public String getName() {
        try {
            readLock.lock();
            // 模拟读取耗时
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
        return name;
    }

    @Override
    public void setName(String name) {
        try {
            writeLock.lock();
            // 模拟读取耗时
            Thread.sleep(10);
            this.name = name;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public int getAge() {
        try {
            readLock.lock();
            // 模拟读取耗时
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
        return age;
    }

    @Override
    public void setAge(int age) {
        try {
            writeLock.lock();
            // 模拟读取耗时
            Thread.sleep(10);
            this.age = age;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }
}
