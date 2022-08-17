package flyweight;

import java.util.HashMap;

/**
 * 车票享元工厂
 *
 * @author BTPJ  2022/8/17
 */
public class TicketFactory {

    private static HashMap<String, ITicket> map = new HashMap<>();

    public static ITicket getTicket(String number) {
        if (map.containsKey(number)) {
            System.out.println("使用享元模式缓存的对象" + number);
            return map.get(number);
        } else {
            System.out.println("创建对象并缓存" + number);
            ITicket ticket = new Ticket(number);
            map.put(number, ticket);
            return ticket;
        }
    }
}
