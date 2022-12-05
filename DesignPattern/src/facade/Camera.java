package facade;

/**
 * 定义摄像头子系统接口，主要包括打开、拍照以及关闭的功能
 *
 * @author BTPJ  2022/12/5
 */
public interface Camera {

    void open();

    void takePicture();

    void close();
}
