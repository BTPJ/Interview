package state;

/**
 * 实现开机状态下的行为
 *
 * @author BTPJ  2023/1/11
 */
public class PowerOnState implements TvState {

    @Override
    public void powerOn() {
        System.out.println("遥控器闪两下红灯提示电视已经开机");
    }

    @Override
    public void powerOff() {
        System.out.println("电视关机");
    }

    @Override
    public void nextChannel() {
        System.out.println("下一频道");
    }

    @Override
    public void prevChannel() {
        System.out.println("上一频道");
    }

    @Override
    public void turnUp() {
        System.out.println("调高音量");
    }

    @Override
    public void turnDown() {
        System.out.println("调低音量");
    }
}
