package copy;

/**
 * 浅拷贝：默认拷贝方式，只拷贝基本数据类型变量，不拷贝引用数据类型变量，只是拷贝的引用变量指向的对象相同
 * <p>
 * 深拷贝：两种数据类型的变量都拷贝一份新的，
 * 通常要实现深拷贝需要实现Cloneable接口并重写对象的clone方法（继承自Object）或使用Gson序列化，
 * 由于性能花销较大，某些情况下强制使用浅拷贝（如，数组的clone()方法、Arrays.copyOf()方法等）
 *
 * @author BTPJ  2023/3/28
 */
public class CopyDemo {

    public static void main(String[] args) {
        testLightCopy();
        testDeepCopy();
    }

    /**
     * 测试浅拷贝
     */
    private static void testLightCopy() {
        User user1 = new User(26, "BTPJ", new Address("湖北", "黄冈"));
        User user2 = user1;
        user2.setName("LTP");
        System.out.println(user1==user2);
        System.out.println(user1.getName());
    }

    /**
     * 测试深拷贝
     */
    private static void testDeepCopy() {
        User user1 = new User(26, "BTPJ", new Address("湖北", "黄冈"));
        User user2 = user1.clone();
        user2.setName("LTP");
        System.out.println(user1==user2);
        System.out.println(user1.getName());
    }
}
