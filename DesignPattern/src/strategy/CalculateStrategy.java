package strategy;

/**
 * 定义计算策略
 *
 * @author LTP  2021/11/29
 */
public interface CalculateStrategy {

    /**
     * 抽象计算方法
     *
     * @param km 距离
     * @return 根据距离计算出的费用
     */
    int calculate(int km);
}
