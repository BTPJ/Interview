package iterator_pattern;

/**
 * 组长
 *
 * @author BTPJ  2022/11/21
 */
public class GroupLeader extends Leader {
    @Override
    protected int getApproveLimit() {
        return 500;
    }

    @Override
    protected void handle(int money) {
        System.out.println("组长处理了" + money + "元报销单据");
    }
}
