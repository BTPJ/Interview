package adapter.class_;

/**
 * 类适配器测试类
 *
 * @author BTPJ  2022/9/26
 */
public class TestClient {
    public static void main(String[] args) {
        VoltAdapter adapter = new VoltAdapter();
        System.out.println("类适配器方式输出电压为：" + adapter.getVolt110());
    }
}
