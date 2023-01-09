package decorator;

/**
 * 用户装饰类，穿各种品牌的衣服
 *
 * @author BTPJ  2023/1/9
 */
public class PersonDecorator extends Person {
    // 持有对抽象类的引用
    protected Person person;

    public PersonDecorator(Person person) {
        this.person = person;
    }

    @Override
    public void dressed() {
        person.dressed();
    }
}
