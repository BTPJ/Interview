package singleton;

/**
 * 单例模式 ~ 双重校验锁DCL（Double check lock）
 * 是针对线程安全的懒汉式的优化版本
 *
 * @author LTP 2021/11/5
 */
public class Singleton_DCL {

    /**
     * volatile关键字（可见性、防止指令重排、不保证原子性）此处用到了防止指令重排的特性
     * 在一个线程分配了mInstance所指向的内存地址，但并未初始化时（发生指令重排），
     * 第二个线程在第一个判空的位置会判断mInstance不为空从而直接返回当前mInstance，但事实上mInstance并未初始化而造成错误
     * 具体参考：<a href="https://blog.csdn.net/llllllkkkkkooooo/article/details/115360630">...</a>
     */
    private static volatile Singleton_DCL mInstance;

    private Singleton_DCL() {
    }

    public static Singleton_DCL getInstance() {
        // 第一个判空是减少synchronized加锁，提高效率
        if (mInstance == null) {
            // 执行位置1
            synchronized (Singleton_DCL.class) {
                // 第二次判空是防止有多个线程调用getInstance()时都执行在第一次判空之后并阻塞在synchronized之前（执行位置1）
                // 从而导致一个线程中初始化后下一个线程继续初始化，从而破坏单例
                if (mInstance == null) {
                    mInstance = new Singleton_DCL();
                }
            }
        }
        return mInstance;
    }
}
