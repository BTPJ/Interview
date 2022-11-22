package iterator_pattern;

/**
 * 老板
 *
 * @author BTPJ  2022/11/21
 */
public class BossLeader extends Leader {
    @Override
    protected int getApproveLimit() {
        return Integer.MAX_VALUE;
    }

    @Override
    protected void handle(int money) {
        System.out.println("老板处理了" + money + "元报销单据");
    }
}
