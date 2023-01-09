package decorator;

/**
 * 穿李宁品牌衣服的具体装饰类
 *
 * @author BTPJ  2023/1/9
 */
public class LiNingDecorator extends PersonDecorator {
    public LiNingDecorator(Person person) {
        super(person);
    }

    @Override
    public void dressed() {
        super.dressed();
        dressedLiNing();
    }

    /**
     * 穿一套李宁的运动装备
     */
    private void dressedLiNing() {
        System.out.println("穿李宁的运动装备");
    }
}
