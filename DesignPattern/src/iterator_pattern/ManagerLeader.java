package iterator_pattern;

/**
 * 经理
 *
 * @author BTPJ  2022/11/21
 */
public class ManagerLeader extends Leader{
    @Override
    protected int getApproveLimit() {
        return 1000;
    }

    @Override
    protected void handle(int money) {
        System.out.println("经理处理了" + money + "元报销单据");
    }
}
