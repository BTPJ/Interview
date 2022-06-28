package template;

/**
 * 调用类
 *
 * @author BTPJ  2022/6/28
 */
public class TemplateMain {
    public static void main(String[] args) {
        CommonComputer commonComputer = new CommonComputer();
        commonComputer.startup();
        System.out.println("-----------------------------------------");

        MilitaryComputer militaryComputer = new MilitaryComputer();
        militaryComputer.startup();
    }
}
