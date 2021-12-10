package observer

/**
 * 定义被观察者接口
 * @author LTP  2021/11/10
 */
interface Subject {

    /**
     * 绑定观察者
     * @param observer 观察者
     */
    fun attach(observer: Observer)

    /**
     * 解绑观察者
     * @param observer 观察者
     */
    fun detach(observer: Observer)

    /**
     * 通知观察者
     * @param msg 通知的内容
     */
    fun notify(msg: String)
}