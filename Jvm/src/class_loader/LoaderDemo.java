package class_loader;

/**
 * 获取类加载器
 *
 * @author BTPJ  2022/8/31
 */
public class LoaderDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader classLoader = LoaderDemo.class.getClassLoader();
        System.out.println(classLoader);

        // 1、通过ClassLoader.loadClass()来加载类，静态代码块不会执行
        classLoader.loadClass("class_loader.LoaderTest");

        System.out.println("-------------------------");

        // 2、使用Class.forName()来加载类，默认会执行静态代码块
        Class.forName("class_loader.LoaderTest");

        System.out.println("-------------------------");

        // 3、使用Class.forName()来加载类，但指定了classLoader,便不会执行静态代码块
        Class.forName("class_loader.LoaderTest", false, classLoader);
    }
}
