package singleton;

/**
 * 单例模式 ~ 静态内部类
 * 相比较饿汉式的好处是：第一次加载Singleton_staticInnerClass并不会初始化mInstance（调用构造器），
 * 只有在调用getInstance()时,才会加载SingletonHolder从而初始化mInstance,
 * 参考：https://blog.csdn.net/weixin_30530523/article/details/96640126
 *
 * @author LTP  2021/11/9
 */
class Singleton_staticInnerClass {

    private Singleton_staticInnerClass() {
        System.out.println("构造器");
    }

    public static Singleton_staticInnerClass getInstance() {
        return SingletonHolder.mInstance;
    }

    private static class SingletonHolder {
        public static final Singleton_staticInnerClass mInstance = new Singleton_staticInnerClass();
    }

    // 执行顺序：main-构造器
    public static void main(String[] args) {
        System.out.println("main");
        Singleton_staticInnerClass instance =Singleton_staticInnerClass.getInstance();
    }
}
