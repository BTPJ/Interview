package factory.abstract_factory

/**
 * 小米工厂类
 *
 * @author LTP  2021/11/10
 */
class MiFactory : AbsPushSendFactory() {

    override fun createPush(): IPush {
        return MiPush()
    }

    override fun createSend(): ISend {
        return MiSend()
    }
}
