package adapter.object;

import adapter.common.Volt110;
import adapter.common.Volt220;

/**
 * Adapter对象适配器角色（将220v转换为110v）
 *
 * @author BTPJ  2022/9/26
 */
public class VoltAdapter implements Volt110 {

    private Volt220 mVolt220;

    public VoltAdapter(Volt220 volt220) {
        this.mVolt220 = volt220;
    }

    public int getVolt220() {
        return mVolt220.getVolt220();
    }

    @Override
    public int getVolt110() {
        return 110;
    }
}
