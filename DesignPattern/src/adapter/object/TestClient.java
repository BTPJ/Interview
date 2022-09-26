package adapter.object;

import adapter.common.Volt220;

/**
 * 对象适配器测试类
 *
 * @author BTPJ  2022/9/26
 */
public class TestClient {
    public static void main(String[] args) {
        VoltAdapter adapter = new VoltAdapter(new Volt220());
        System.out.println("对象适配器方式输出电压为：" + adapter.getVolt110());
    }
}
