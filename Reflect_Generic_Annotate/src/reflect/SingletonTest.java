package reflect;

import java.lang.reflect.Constructor;

/**
 * 使用反射破解单例
 *
 * @author BTPJ  2023/3/28
 */
public class SingletonTest {

    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        System.out.println(singleton1 == singleton2);

        Singleton singleton3 = getInstanceByReflect();
        System.out.println(singleton1 == singleton3);
    }

    /**
     * 通过反射获取单例
     *
     * @return 单例
     */
    private static Singleton getInstanceByReflect() {
        try {
            Constructor<Singleton> constructor = Singleton.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
