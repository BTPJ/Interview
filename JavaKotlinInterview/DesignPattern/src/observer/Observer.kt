package observer

/**
 * 观察者的接口
 *
 * @author LTP  2021/11/10
 */
interface Observer {

    /**
     * 定义一个收到被观察者改变而更新的动作
     *
     * @param msg 收到的消息
     */
    fun update(msg: String)
}