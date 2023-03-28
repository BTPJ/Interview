package reflect;

/**
 * 单例模式
 *
 * @author BTPJ  2023/3/28
 */
public class SingleTon {

    private static volatile SingleTon mInstance;

    private SingleTon() {
        if (mInstance != null) {
            System.out.println("不能破解单例");
            throw new RuntimeException("请不要尝试破解单例");
        }
    }

    public static SingleTon getInstance() {
        if (mInstance == null) {
            synchronized (SingleTon.class) {
                if (mInstance == null) {
                    mInstance = new SingleTon();
                }
            }
        }
        return mInstance;
    }
}
