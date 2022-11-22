package iterator_pattern;

/**
 * 总监
 *
 * @author BTPJ  2022/11/21
 */
public class InspectorLeader extends Leader{
    @Override
    protected int getApproveLimit() {
        return 5000;
    }

    @Override
    protected void handle(int money) {
        System.out.println("总监处理了" + money + "元报销单据");
    }
}
