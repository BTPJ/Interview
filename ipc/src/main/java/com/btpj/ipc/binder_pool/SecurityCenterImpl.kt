package com.btpj.ipc.binder_pool

import java.math.BigInteger

/**
 * @author LTP  2021/7/30
 */
class SecurityCenterImpl : ISecurityCenter.Stub() {

    private val secretCode = "^".toBigInteger()

    override fun encrypt(content: String?): String {
        val let1 = content?.let { BigInteger(it) xor secretCode }
        return let1.toString()
    }

    override fun decrypt(password: String?): String {
        return encrypt(password)
    }
}