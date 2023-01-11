package state;

/**
 * 电视控制器（可以理解为UML结构图中的Context）
 *
 * @author BTPJ  2023/1/11
 */
public class TvController {
    private TvState tvState;

    public void setTvState(TvState tvState) {
        this.tvState = tvState;
    }

    public void powerOn() {
        tvState.powerOn();
        // 调用开机方法后将状态改变为开机状态
        setTvState(new PowerOnState());
    }

    public void powerOff() {
        tvState.powerOff();
        // 调用关机机方法后将状态改变为关机状态
        setTvState(new PowerOffState());
    }

    public void nextChannel() {
        tvState.nextChannel();
    }

    public void prevChannel() {
        tvState.prevChannel();
    }

    public void turnUp() {
        tvState.turnUp();
    }

    public void turnDown() {
        tvState.turnDown();
    }
}
