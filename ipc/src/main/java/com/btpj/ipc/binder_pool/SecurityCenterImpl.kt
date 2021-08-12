package com.btpj.ipc.binder_pool

/**
 * @author LTP  2021/7/30
 */
class SecurityCenterImpl : ISecurityCenter.Stub() {

    private val secretCode = "ABCSAHK"

    override fun encrypt(content: String?): String {
        return secretCode + content
    }

    override fun decrypt(password: String?): String {
        return password!!.substring(secretCode.length, password.length)
    }
}