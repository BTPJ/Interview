package state;

/**
 * 客户端调用
 *
 * @author BTPJ  2023/1/11
 */
public class client {

    public static void main(String[] args) {
        TvController tvController = new TvController();
        tvController.setTvState(new PowerOnState());
        // tvController.setTvState(new PowerOffState());
        tvController.powerOff();
        tvController.powerOn();
        tvController.nextChannel();
        tvController.prevChannel();
        tvController.turnUp();
        tvController.turnDown();
    }
}
