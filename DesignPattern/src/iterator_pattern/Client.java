package iterator_pattern;

/**
 * 客户端调用
 *
 * @author BTPJ  2022/11/21
 */
public class Client {

    public static void main(String[] args) {
        GroupLeader groupLeader = new GroupLeader();
        ManagerLeader managerLeader = new ManagerLeader();
        InspectorLeader inspectorLeader = new InspectorLeader();
        BossLeader bossLeader = new BossLeader();
        groupLeader.nextHandler = managerLeader;
        managerLeader.nextHandler = inspectorLeader;
        inspectorLeader.nextHandler = bossLeader;

        groupLeader.handleRequest(300);
        System.out.println("-----------------------------------");
        groupLeader.handleRequest(600);
        System.out.println("-----------------------------------");
        groupLeader.handleRequest(1200);
        System.out.println("-----------------------------------");
        groupLeader.handleRequest(6000);
    }
}
