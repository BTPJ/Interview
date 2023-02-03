package command;

/**
 * 后退的命令实现
 *
 * @author BTPJ  2023/2/3
 */
public class RetreatCommand implements ICommand {
    /**
     * 具体实现是车，所以得有一个车的引用对象
     */
    private final Telecar telecar;

    public RetreatCommand(Telecar telecar) {
        this.telecar = telecar;
    }

    @Override
    public void execute() {
        telecar.retreat();
    }
}
