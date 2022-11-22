package iterator_pattern;

/**
 * 抽象领导类
 *
 * @author BTPJ  2022/11/21
 */
public abstract class Leader {
    /**
     * 下一个处理者，即更上级的领导
     */
    protected Leader nextHandler;

    /**
     * 处理报销请求
     *
     * @param money 报销金额
     */
    public final void handleRequest(int money) {
        if (money <= getApproveLimit()) {
            handle(money);
        } else {
            String handleLeader = "";
            String className = this.getClass().getSimpleName();
            switch (className) {
                case "GroupLeader":
                    handleLeader = "组长";
                    break;
                case "ManagerLeader":
                    handleLeader = "经理";
                    break;
                case "InspectorLeader":
                    handleLeader = "总监";
                    break;
                default:
                    handleLeader = "老板";
                    break;
            }
            System.out.println(handleLeader + "无法处理，需要转交给更上级处理");
            if (nextHandler != null) {
                nextHandler.handleRequest(money);
            }
        }
    }

    /**
     * 能处理的报销金额上限
     */
    protected abstract int getApproveLimit();

    /**
     * 报销实际处理
     *
     * @param money 报销金额
     */
    protected abstract void handle(int money);
}
