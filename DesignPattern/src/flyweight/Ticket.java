package flyweight;

import java.util.Random;

/**
 * 具体高铁票对象
 *
 * @author BTPJ  2022/8/17
 */
public class Ticket implements ITicket {
    private final String number; // 车次
    private int price; // 车票价格
    private String seat; // 座位类型

    public Ticket(String number) {
        this.number = number;
    }

    @Override
    public void showTicketInfo(String seat) {
        if ("一等座".equals(seat)) {
            price = 400 + (int) (Math.random() * 400);  // 模拟价格
        } else {
            price = new Random().nextInt(400);  // 模拟价格
        }
        System.out.println(number + "车次-" + seat + "-价格：" + price);
    }
}
