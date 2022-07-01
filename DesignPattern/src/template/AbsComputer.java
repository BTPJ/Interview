package template;

/**
 * 启动计算机的抽象模板
 *
 * @author BTPJ  2022/6/28
 */
public abstract class AbsComputer {

    protected void openPower() {
        System.out.println("打开计算机电源");
    }

    protected void checkHardware() {
        System.out.println("计算机硬件检查");
    }

    protected void loadOS() {
        System.out.println("加载windows/mac os操作系统");
    }

    protected void login() {
        System.out.println("默认未设置密码直接登录");
    }

    /**
     * 开启计算机，规定计算机的启动模板
     */
    public final void startup() {
        System.out.println("-----------计算机开机start");
        openPower();
        checkHardware();
        loadOS();
        login();
        System.out.println("-----------计算机开机end");
    }
}
