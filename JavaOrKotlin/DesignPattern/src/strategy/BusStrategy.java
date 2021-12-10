package strategy;

/**
 * 公交的具体计算方式
 *
 * @author LTP  2021/11/29
 */
public class BusStrategy implements CalculateStrategy {

    @Override
    public int calculate(int km) {
        // 计价规则：起步价1元（包含10km），每超过5km加收1元
        int extraKm = km - 10;
        int extraFactor = (int) Math.ceil(extraKm / 5.0);
        return 1 + extraFactor;
    }
}
