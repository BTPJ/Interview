package flyweight;

/**
 * 定义享元高铁票对象接口
 *
 * @author BTPJ  2022/8/17
 */
public interface ITicket {

    /**
     * 展示车票信息
     *
     * @param seat 座位类型（包括一等座、二等座、无座）
     */
    void showTicketInfo(String seat);
}
