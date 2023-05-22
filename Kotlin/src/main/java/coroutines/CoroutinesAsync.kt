package coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.sync.withPermit
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.ReentrantLock
import kotlin.system.measureTimeMillis

/**
 * 协程同步方案
 *
 * @author BTPJ  2023/5/22
 */
class CoroutinesAsync {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("普通方式总耗时：${measureTimeMillis { testSimple() }}")
            println("Mutex协程锁同步方式总耗时：${measureTimeMillis { testMutex() }}")
            println("synchronized线程锁同步方式总耗时：${measureTimeMillis { testSynchronized() }}")
            println("ReentrantLock线程锁同步方式总耗时：${measureTimeMillis { testReentrantLock() }}")
            println("Atomic原子操作实现同步方式总耗时：${measureTimeMillis { testAtomic() }}")
            println("Semaphore协程信号量实现同步方式总耗时：${measureTimeMillis { testSemaphore() }}")
            println("actor实现同步方式总耗时：${measureTimeMillis { testActor() }}")
        }

        /** 使用非同步方式 */
        private fun testSimple() {
            runBlocking {
                var count = 0
                val list = ArrayList<Job>()

                repeat(100) {
                    list.add(
                        CoroutineScope(Dispatchers.IO).launch {
                            repeat(1000) {
                                count++
                            }
                        })
                }

                list.forEach { it.join() }

                println("count值为：${count}")
            }
        }

        /** 使用Mutex协程锁实现同步 */
        private fun testMutex() {
            runBlocking {
                var count = 0
                val mutex = Mutex()
                val list = ArrayList<Job>()

                repeat(100) {
                    list.add(
                        CoroutineScope(Dispatchers.IO).launch {
                            repeat(1000) {
                                mutex.withLock {
                                    count++
                                }
                            }
                        })
                }

                list.forEach { it.join() }

                println("count值为：${count}")
            }
        }

        /** 使用Semaphore协程信号量实现同步 */
        private fun testSemaphore() {
            runBlocking {
                var count = 0
                val semaphore = Semaphore(1)
                val list = ArrayList<Job>()

                repeat(100) {
                    list.add(
                        CoroutineScope(Dispatchers.IO).launch {
                            repeat(1000) {
                                semaphore.withPermit {
                                    count++
                                }
                            }
                        })
                }

                list.forEach { it.join() }

                println("count值为：${count}")
            }
        }

        /** 使用actor并发同步模型实现同步 */
        @OptIn(ObsoleteCoroutinesApi::class)
        private fun testActor() {
            runBlocking {
                var count = 0

                val actor = actor<Int>(capacity = Channel.UNLIMITED) {
                    for (msg in channel) {
                        count++
                    }
                }

                val list = ArrayList<Job>()

                repeat(100) {
                    list.add(
                        CoroutineScope(Dispatchers.IO).launch {
                            repeat(1000) {
                                actor.send(1)
                            }
                        })
                }

                list.forEach { it.join() }

                actor.close()

                println("count值为：${count}")
            }
        }

        /** 使用synchronized线程锁实现同步 */
        private fun testSynchronized() {
            runBlocking {
                var count = 0
                val list = ArrayList<Job>()

                repeat(100) {
                    list.add(
                        CoroutineScope(Dispatchers.IO).launch {
                            repeat(1000) {
                                synchronized(list) {
                                    count++
                                }
                            }
                        })
                }

                list.forEach { it.join() }

                println("count值为：${count}")
            }
        }

        /** 使用ReentrantLock线程锁实现同步 */
        private fun testReentrantLock() {
            runBlocking {
                var count = 0
                val lock = ReentrantLock()
                val list = ArrayList<Job>()

                repeat(100) {
                    list.add(
                        CoroutineScope(Dispatchers.IO).launch {
                            repeat(1000) {
                                lock.lock()
                                count++
                                lock.unlock()
                            }
                        })
                }

                list.forEach { it.join() }

                println("count值为：${count}")
            }
        }

        /** 使用Atomic原子操作实现同步 */
        private fun testAtomic() {
            runBlocking {
                val atomicCount = AtomicInteger(0)
                val list = ArrayList<Job>()

                repeat(100) {
                    list.add(
                        CoroutineScope(Dispatchers.IO).launch {
                            repeat(1000) {
                                atomicCount.incrementAndGet()
                            }
                        })
                }

                list.forEach { it.join() }

                println("count值为：${atomicCount.get()}")
            }
        }
    }
}

