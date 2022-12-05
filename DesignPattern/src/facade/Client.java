package facade;

/**
 * 客户端调用
 *
 * @author BTPJ  2022/12/5
 */
public class Client {
    public static void main(String[] args) {
        Mobile mobile = new Mobile();
        mobile.takePicture();
        mobile.videoChat();
    }
}
