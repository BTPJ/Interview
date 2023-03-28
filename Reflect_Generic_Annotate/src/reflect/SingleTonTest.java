package reflect;

import java.lang.reflect.Constructor;

/**
 * 使用反射破解单例
 *
 * @author BTPJ  2023/3/28
 */
public class SingleTonTest {

    public static void main(String[] args) {
        SingleTon singleTon1 = SingleTon.getInstance();
        SingleTon singleTon2 = SingleTon.getInstance();
        System.out.println(singleTon1 == singleTon2);

        SingleTon singleTon3 = getInstanceByReflect();
        System.out.println(singleTon1 == singleTon3);
    }


    private static SingleTon getInstanceByReflect() {
        try {
            Constructor<SingleTon> constructor = SingleTon.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
