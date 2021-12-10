package factory.simple_factory

/**
 * 推送工厂类
 * @author LTP  2021/11/10
 */
object PushFactory {

    /**
     * 根据具体的手机类型使用具体的推送服务
     *
     * @param type 推送类型
     * @return Push 具体的推送类型
     */
    fun createPush(type: String): IPush {
        return when (type) {
            "xiaoMi" -> MiPush()
            "huaWei" -> HuaWeiPush()
            else -> JiGuangPush()
        }
    }
}