package decorator;

/**
 * 客户端调用
 *
 * @author BTPJ  2023/1/9
 */
public class Client {

    public static void main(String[] args) {
        Person person = new XiaoMing();

        // 穿李宁
        PersonDecorator decorator = new LiNingDecorator(person);
        decorator.dressed();

        System.out.println("---------------------------------");

        // 穿Nike
        PersonDecorator decorator2 = new NikeDecorator(person);
        decorator2.dressed();
    }
}
