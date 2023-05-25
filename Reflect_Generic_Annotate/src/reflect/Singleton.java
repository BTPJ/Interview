package reflect;

/**
 * 单例模式
 *
 * @author BTPJ  2023/3/28
 */
public class Singleton {

    private static volatile Singleton mInstance;

    private Singleton() {
        if (mInstance != null) {
            System.out.println("不能破解单例");
            throw new RuntimeException("请不要尝试破解单例");
        }
    }

    public static Singleton getInstance() {
        if (mInstance == null) {
            synchronized (Singleton.class) {
                if (mInstance == null) {
                    mInstance = new Singleton();
                }
            }
        }
        return mInstance;
    }
}
