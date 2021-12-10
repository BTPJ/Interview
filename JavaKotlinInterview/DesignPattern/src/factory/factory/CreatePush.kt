package factory.factory

/**
 * 具体调用
 *
 * @author LTP  2021/11/10
 */
class CreatePush {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            PushFactory.createPush(MiPush::class.java).push()
            PushFactory.createPush(HuaWeiPush::class.java).push()
            PushFactory.createPush(OppoPush::class.java).push()
        }
    }
}