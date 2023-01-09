package decorator;

/**
 * 小明集成Person抽象类，本身只穿了内衣内裤
 *
 * @author BTPJ  2023/1/9
 */
public class XiaoMing extends Person {

    @Override
    public void dressed() {
        System.out.println("小明穿了内衣内裤");
    }
}
