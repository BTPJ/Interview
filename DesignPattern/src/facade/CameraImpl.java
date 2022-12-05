package facade;

/**
 * 摄像头子系统具体实现类
 *
 * @author BTPJ  2022/12/5
 */
public class CameraImpl implements Camera {
    @Override
    public void open() {
        System.out.println("打开摄像头");
    }

    @Override
    public void takePicture() {
        System.out.println("拍照");
    }

    @Override
    public void close() {
        System.out.println("关闭摄像头");
    }
}
