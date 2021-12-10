package com.btpj.ipc.binder_pool

/**
 * @author LTP  2021/7/30
 */
class ComputeImpl : ICompute.Stub() {
    override fun add(a: Int, b: Int): Int {
        return a + b
    }
}