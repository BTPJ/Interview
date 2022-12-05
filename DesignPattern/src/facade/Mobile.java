package facade;

/**
 * 手机外观类
 *
 * @author BTPJ  2022/12/5
 */
public class Mobile {
    private Phone mPhone = new PhoneImpl();
    private Camera mCamera = new CameraImpl();

    /**
     * 暴露拨打电话的方法
     */
    public void dail() {
        mPhone.dail();
    }

    /**
     * 暴露挂断电话的方法
     */
    public void hangup() {
        mPhone.hangup();
    }

    /**
     * 暴露拍照的方法
     */
    public void takePicture() {
        mCamera.open();
        mCamera.takePicture();
        mCamera.close();
    }

    /**
     * 模拟视频通话
     */
    public void videoChat() {
        System.out.println("------模拟视频通话-----");
        mPhone.dail();
        mCamera.open();
    }
}
