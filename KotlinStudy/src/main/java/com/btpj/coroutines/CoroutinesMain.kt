package com.btpj.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * @author LTP  2023/9/6
 */
suspend fun main() {
    println('1')
    val job = GlobalScope.launch {
        println('2')
    }
    println('3')
    job.join()
//    job.start()
//    job.cancel()
    println('4')
}