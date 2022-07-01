package singleton;

/**
 * 单例模式 ~ 线程安全的懒汉式
 *
 * @author LTP  2021/11/9
 */
class Singleton_lazySync {

    private static Singleton_lazySync mInstance;

    private Singleton_lazySync() {
    }

    public static synchronized Singleton_lazySync getInstance() {
        if (mInstance == null) {
            mInstance = new Singleton_lazySync();
        }
        return mInstance;
    }
}
