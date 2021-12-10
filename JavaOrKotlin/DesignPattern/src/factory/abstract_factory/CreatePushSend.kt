package factory.abstract_factory

/**
 * 具体调用
 *
 * @author LTP  2021/11/10
 */
class CreatePushSend {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            // 小米工厂
            val miFactory = MiFactory()
            miFactory.createPush().push()
            miFactory.createSend().send()

            // 华为工厂
            val huaWeiFactory = HuaWeiFactory()
            huaWeiFactory.createPush().push()
            huaWeiFactory.createSend().send()
        }
    }
}
