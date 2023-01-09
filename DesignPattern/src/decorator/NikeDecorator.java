package decorator;

/**
 * 穿李宁品牌衣服的具体装饰类
 *
 * @author BTPJ  2023/1/9
 */
public class NikeDecorator extends PersonDecorator {
    public NikeDecorator(Person person) {
        super(person);
    }

    @Override
    public void dressed() {
        super.dressed();
        dressedNike();
    }

    /**
     * 穿一套李宁的运动装备
     */
    private void dressedNike() {
        System.out.println("穿Nike的运动装备");
    }
}
