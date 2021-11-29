package com.btpj.design_pattern.strategy;

/**
 * 策略模式具体调用类
 *
 * @author LTP  2021/11/29
 */
public class ClientCalculate {
    private CalculateStrategy calculateStrategy;

    /**
     * 设置具体的计算策略
     *
     * @param calculateStrategy 计算策略
     */
    public void setCalculateStrategy(CalculateStrategy calculateStrategy) {
        this.calculateStrategy = calculateStrategy;
    }

    public static void main(String[] args) {
        ClientCalculate clientCalculate = new ClientCalculate();
        clientCalculate.setCalculateStrategy(new BusStrategy());
        System.out.println("公交14km价格：" + clientCalculate.calculatePrice(14));

        clientCalculate.setCalculateStrategy(new SubwayStrategy());
        System.out.println("地铁12km价格：" + clientCalculate.calculatePrice(12));
    }

    /**
     * 计算交通的具体价格，交给抽象策略最终交由相应的策略来完成
     *
     * @param km 距离
     * @return 价格
     */
    private int calculatePrice(int km) {
        return calculateStrategy.calculate(km);
    }
}
