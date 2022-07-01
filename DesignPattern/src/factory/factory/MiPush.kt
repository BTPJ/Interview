package factory.factory

/**
 * 具体产品类：小米推送具体实现
 * @author LTP  2021/11/10
 */
class MiPush : IPush {

    override fun push() {
        println("小米手机使用小米推送")
    }
}
