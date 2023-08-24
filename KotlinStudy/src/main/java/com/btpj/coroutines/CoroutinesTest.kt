package com.btpj.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

/**
 * 协程与线程、线程池对比
 * @author LTP  2023/8/18
 */
class CoroutinesTest {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runBlocking {
                val startTime = System.currentTimeMillis()
                start10WCoroutines()
//                start10WThread()
//                start10WJobByExecutor()
//                start10WLaunchForCustomExecutor()
                println("耗时：${System.currentTimeMillis() - startTime}")
            }
        }

        /** 创建10w个协程 */
        private fun start10WCoroutines() {
            repeat(100000) {
                CoroutineScope(Dispatchers.IO).launch {
                    delay(100)
                }
            }
        }

        /** 创建10w个线程 */
        private fun start10WThread() {
            repeat(100000) {
                thread { Thread.sleep(100) }
            }
        }

        /** 使用线程池运行10w个线程 */
        private fun start10WJobByExecutor() {
            val threadPool = Executors.newScheduledThreadPool(10)
            repeat(100000) {
                threadPool.schedule({}, 100, TimeUnit.MILLISECONDS)
            }
            threadPool.shutdown()
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS)
        }

        /** 使用自定义调度器运行10w个协程 */
        private fun CoroutineScope.start10WLaunchForCustomExecutor() {
            val coroutineDispatcher = Executors.newScheduledThreadPool(10).asCoroutineDispatcher()
            repeat(100000) {
                launch(coroutineDispatcher) {
                    delay(100)
                }
            }
        }
    }
}