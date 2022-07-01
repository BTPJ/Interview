package template;

/**
 * 通常用户的电脑
 *
 * @author BTPJ  2022/6/28
 */
public class CommonComputer extends AbsComputer {

    @Override
    protected void login() {
        System.out.println("通常用户设置了密码，只需要正确输入密码进入系统");
    }
}
