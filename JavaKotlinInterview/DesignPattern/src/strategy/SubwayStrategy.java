package strategy;

/**
 * 地铁的具体计算方式
 *
 * @author LTP  2021/11/29
 */
public class SubwayStrategy implements CalculateStrategy {

    @Override
    public int calculate(int km) {
        // 计价规则：6km内（含）3元、6~12km（含）4元、12~22km（含）5元、22~32km（含）6元
        if (km <= 6) {
            return 3;
        } else if (km <= 12) {
            return 4;
        } else if (km <= 22) {
            return 5;
        } else {
            return 6;
        }
    }
}
