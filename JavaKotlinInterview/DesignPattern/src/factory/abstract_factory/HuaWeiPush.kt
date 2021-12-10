package factory.abstract_factory

/**
 * 具体产品类：华为推送具体实现
 * @author LTP  2021/11/10
 */
class HuaWeiPush : IPush {

    override fun push() {
        println("华为手机使用华为推送")
    }
}
