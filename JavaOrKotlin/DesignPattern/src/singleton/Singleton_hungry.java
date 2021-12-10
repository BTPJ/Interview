package singleton;

/**
 * 单例模式 ~ 饿汉式
 *
 * @author LTP  2021/11/9
 */
class Singleton_hungry {

    private static final Singleton_hungry mInstance = new Singleton_hungry();

    private Singleton_hungry() {
        System.out.println("构造器");
    }

    public static Singleton_hungry getInstance() {
        return mInstance;
    }

    // 执行顺序：构造器-main
    public static void main(String[] args) {
        System.out.println("main");
        Singleton_hungry instance =Singleton_hungry.getInstance();
    }
}
