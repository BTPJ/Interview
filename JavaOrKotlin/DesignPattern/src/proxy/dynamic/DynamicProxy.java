package proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理类
 *
 * @author LTP  2021/11/9
 */
public class DynamicProxy implements InvocationHandler {
    // 申明被代理的对象,此时并不知道委托类的类型（Object）
    private final Object proxyObject;

    public DynamicProxy(Object proxyObject) {
        this.proxyObject = proxyObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 收取代购费用
        System.out.println("收取代购费用");
        // 利用反射调用被代理对象的方法
        Object result = method.invoke(proxyObject, args);
        System.out.println("快递代理货物");
        return result;
    }
}
