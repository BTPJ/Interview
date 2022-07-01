package template;

/**
 * 军用级别的电脑
 *
 * @author BTPJ  2022/6/28
 */
public class MilitaryComputer extends AbsComputer {

    @Override
    protected void checkHardware() {
        System.out.println("检查硬件防火墙");
    }

    @Override
    protected void loadOS() {
        System.out.println("加载军用级专用操作系统");
    }

    @Override
    protected void login() {
        System.out.println("采用指纹密码的方式进入系统");
    }
}
