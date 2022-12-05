package facade;

/**
 * 通话子系统具体实现类
 *
 * @author BTPJ  2022/12/5
 */
public class PhoneImpl implements Phone{
    @Override
    public void dail() {
        System.out.println("拨打电话");
    }

    @Override
    public void hangup() {
        System.out.println("挂断电话");
    }
}
