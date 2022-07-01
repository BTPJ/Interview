package proxy.dynamic;

/**
 * 真实对象类，委托类
 *
 * @author LTP  2021/11/9
 */
public class RealSubject2 implements Subject2 {
    @Override
    public void buyXiaomi() {
        System.out.println("我是美国佬，我要买小米");
    }
}
