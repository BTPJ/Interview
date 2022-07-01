package proxy.statics;

/**
 * 实际调用静态代理的位置
 * 1个静态代理 只服务1种类型的目标对象，若要服务多类型的目标对象，则需要为每种目标对象都实现一个静态代理对象
 *
 * @author LTP  2021/11/9
 */
public class Client {
    public static void main(String[] args) {
        Subject subject = new RealSubject();
        // 创建代理类，并将委托类传入构造
        Proxy proxy = new Proxy(subject);
        proxy.buyHuaWei();
    }
}
