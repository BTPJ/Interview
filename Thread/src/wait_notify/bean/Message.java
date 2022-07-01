package wait_notify.bean;

/**
 * 消息实体
 *
 * @author BTPJ  2022/2/23
 */
public class Message {
    private String name;

    public Message(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
