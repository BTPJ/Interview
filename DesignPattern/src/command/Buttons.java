package command;

/**
 * 模拟遥控上的前进后退按钮
 *
 * @author BTPJ  2023/2/3
 */
public class Buttons {
    /**
     * 命令只关心前进后退按键，不关心车，实现对Telecar车的解耦
     */
    private ForwardCommand forwardCommand;
    private RetreatCommand retreatCommand;

    public void setForwardCommand(ForwardCommand forwardCommand) {
        this.forwardCommand = forwardCommand;
    }

    public void setRetreatCommand(RetreatCommand retreatCommand) {
        this.retreatCommand = retreatCommand;
    }

    public void forward() {
        forwardCommand.execute();
    }

    public void retreat() {
        retreatCommand.execute();
    }
}
