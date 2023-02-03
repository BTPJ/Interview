package command;

/**
 * 客户端调用
 *
 * @author BTPJ  2023/2/3
 */
public class Client {
    public static void main(String[] args) {
        // 首先要有遥控车
        Telecar telecar = new Telecar();

        // 构造遥控
        Buttons buttons = new Buttons();
        buttons.setForwardCommand(new ForwardCommand(telecar));
        buttons.setRetreatCommand(new RetreatCommand(telecar));

        // 可以开始操作发送命令了
        buttons.forward();
        buttons.retreat();
    }
}
