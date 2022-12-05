package facade;

/**
 * 定义通话子系统接口，主要包括拨号和挂断的功能
 *
 * @author BTPJ  2022/12/5
 */
public interface Phone {

    /**
     * 拨号
     */
    void dail();

    /**
     * 挂断
     */
    void hangup();
}
