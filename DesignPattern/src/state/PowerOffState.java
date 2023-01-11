package state;

/**
 * 实现关机状态下的行为
 *
 * @author BTPJ  2023/1/11
 */
public class PowerOffState implements TvState{

    @Override
    public void powerOn() {
        System.out.println("电视开机");
    }

    @Override
    public void powerOff() {
        System.out.println("遥控器闪两下红灯提示电视并未开机");
    }

    @Override
    public void nextChannel() {
        System.out.println("遥控器闪两下红灯提示电视并未开机");
    }

    @Override
    public void prevChannel() {
        System.out.println("遥控器闪两下红灯提示电视并未开机");
    }

    @Override
    public void turnUp() {
        System.out.println("遥控器闪两下红灯提示电视并未开机");
    }

    @Override
    public void turnDown() {
        System.out.println("遥控器闪两下红灯提示电视并未开机");
    }
}
