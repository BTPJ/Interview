package singleton;

/**
 * 单例模式 ~ 懒汉式
 *
 * @author LTP  2021/11/9
 */
class Singleton_lazy {

    private static Singleton_lazy mInstance;

    private Singleton_lazy() {
    }

    public static Singleton_lazy getInstance() {
        if (mInstance == null) {
            mInstance = new Singleton_lazy();
        }
        return mInstance;
    }
}
