package state;

/**
 * 封装状态下的行为
 *
 * @author BTPJ  2023/1/11
 */
public interface TvState {
    void powerOn();
    void powerOff();
    void nextChannel();
    void prevChannel();
    void turnUp();
    void turnDown();
}
