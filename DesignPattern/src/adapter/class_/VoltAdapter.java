package adapter.class_;

import adapter.common.Volt110;
import adapter.common.Volt220;

/**
 * Adapter类适配器角色（将220v转换为110v）
 *
 * @author BTPJ  2022/9/26
 */
public class VoltAdapter extends Volt220 implements Volt110 {
    @Override
    public int getVolt110() {
        return 110;
    }
}
