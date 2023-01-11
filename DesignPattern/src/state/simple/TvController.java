package state.simple;

/**
 * 电视控制类（使用包含大量if-else的普通方式）
 *
 * @author BTPJ  2023/1/10
 */
public class TvController {
    /**
     * 关机状态
     */
    private static final int POWER_OFF = 0;
    /**
     * 开机状态
     */
    private static final int POWER_ON = 1;

    /**
     * 当前状态
     */
    private int mState = POWER_OFF;

    /**
     * 电视开机操作
     */
    public void powerOn() {
        if (mState == POWER_ON) {
            System.out.println("遥控器闪两下红灯提示电视已经开机");
        } else {
            mState = POWER_ON;
            System.out.println("电视开机");
        }
    }

    /**
     * 电视关机操作
     */
    public void powerOff() {
        if (mState == POWER_ON) {
            mState = POWER_OFF;
            System.out.println("电视关机");
        } else {
            System.out.println("遥控器闪两下红灯提示电视并未开机");
        }
    }

    /**
     * 增加频道
     */
    public void nextChannel() {
        if (mState == POWER_ON) {
            System.out.println("下一频道");
        } else {
            System.out.println("遥控器闪两下红灯提示电视并未开机");
        }
    }

    /**
     * 减少频道
     */
    public void prevChannel() {
        if (mState == POWER_ON) {
            System.out.println("上一频道");
        } else {
            System.out.println("遥控器闪两下红灯提示电视并未开机");
        }
    }

    /**
     * 增加频道
     */
    public void turnUp() {
        if (mState == POWER_ON) {
            System.out.println("调高音量");
        } else {
            System.out.println("遥控器闪两下红灯提示电视并未开机");
        }
    }

    /**
     * 减少频道
     */
    public void turnDown() {
        if (mState == POWER_ON) {
            System.out.println("调低音量");
        } else {
            System.out.println("遥控器闪两下红灯提示电视并未开机");
        }
    }
}
