package flyweight;

/**
 * 调用类
 *
 * @author BTPJ  2022/8/17
 */
public class Client {

    public static void main(String[] args) {
        ITicket ticket1 = TicketFactory.getTicket("G111");
        ticket1.showTicketInfo("一等座");
        ITicket ticket2 = TicketFactory.getTicket("G111");
        ticket2.showTicketInfo("二等座");

        ITicket ticket3 = TicketFactory.getTicket("G222");
        ticket3.showTicketInfo("无座");
    }
}
