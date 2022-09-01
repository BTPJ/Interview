package class_loader;

/**
 * 获取类加载器
 *
 * @author BTPJ  2022/8/31
 */
public class JavaClassLoader {

    public static void main(String[] args) {
        ClassLoader classLoader = JavaClassLoader.class.getClassLoader();
        System.out.println(classLoader);
        System.out.println(classLoader.getParent());
        System.out.println(classLoader.getParent().getParent());
    }
}
