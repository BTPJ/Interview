package com.btpj.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.sync.withPermit
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.ReentrantLock

/**
 * 协程的几种并发安全方案对比
 * 非同步 > AtomicInteger（原子操作）> synchronized（线程同步锁）> ReentrantLock（可重入锁）
 * > Semaphore（协程信号量限制） >  Mutex（协程互斥锁）> actor通信并发同步模型
 *
 * @author LTP  2023/5/25
 */
class CoroutinesConcurrent {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            CoroutinesConcurrent().apply {
                testSimple()
                testAtomic()
                testSynchronized()
                testReentrantLock()
                testSemaphore()
                testMutex()
                testActor()
            }
        }
    }

    /** 使用非同步方式 47ms */
    @Test
    fun testSimple() {
        runBlocking {
            val start = System.currentTimeMillis()
            var count = 0

            List(1000) {
                CoroutineScope(Dispatchers.Default).launch {
                    repeat(10000) {
                        count++
                    }
                }
            }.joinAll()

            println("count值为：${count}，非同步耗时为：${System.currentTimeMillis() - start}")
        }
    }

    /** 使用Mutex协程锁实现同步 14881ms */
    @Test
    fun testMutex() {
        runBlocking {
            val start = System.currentTimeMillis()
            var count = 0
            val mutex = Mutex()

            List(1000) {
                CoroutineScope(Dispatchers.Default).launch {
                    repeat(10000) {
                        mutex.withLock {
                            count++
                        }
                    }

                }
            }.joinAll()

            println("count值为：${count}，Mutex耗时为：${System.currentTimeMillis() - start}")
        }
    }

    /** 使用Semaphore协程信号量实现同步 1518ms */
    @Test
    fun testSemaphore() {
        runBlocking {
            val start = System.currentTimeMillis()
            var count = 0
            val semaphore = Semaphore(1)

            List(1000) {
                CoroutineScope(Dispatchers.Default).launch {
                    repeat(10000) {
                        semaphore.withPermit {
                            count++
                        }
                    }
                }
            }.joinAll()

            println("count值为：${count}，Semaphore耗时为：${System.currentTimeMillis() - start}")
        }
    }

    /** 使用actor并发同步模型实现同步 4497ms */
    @OptIn(ObsoleteCoroutinesApi::class)
    @Test
    fun testActor() {
        runBlocking {
            val start = System.currentTimeMillis()
            var count = 0

            val actor = actor<Int>(capacity = Channel.UNLIMITED) {
                for (msg in channel) {
                    count++
                }
            }

            List(1000) {
                CoroutineScope(Dispatchers.Default).launch {
                    repeat(10000) {
                        actor.send(1)
                    }
                }
            }.joinAll()

            actor.close()

            println("count值为：${count}，Actor耗时为：${System.currentTimeMillis() - start}")
        }
    }

    /** 使用synchronized线程锁实现同步 106ms */
    @Test
    fun testSynchronized() {
        runBlocking {
            val start = System.currentTimeMillis()
            var count = 0
            val name = "123"

            List(1000) {
                CoroutineScope(Dispatchers.Default).launch {
                    repeat(10000) {
                        synchronized(name) {
                            count++
                        }
                    }
                }
            }.joinAll()

            println("count值为：${count}，Synchronized耗时为：${System.currentTimeMillis() - start}")
        }
    }

    /** 使用ReentrantLock线程锁实现同步 241ms*/
    @Test
    fun testReentrantLock() {
        runBlocking {
            val start = System.currentTimeMillis()
            var count = 0
            val lock = ReentrantLock()

            List(1000) {
                CoroutineScope(Dispatchers.Default).launch(Dispatchers.Default) {
                    repeat(10000) {
                        lock.lock()
                        count++
                        lock.unlock()
                    }
                }
            }.joinAll()

            println("count值为：${count}，ReentrantLock耗时为：${System.currentTimeMillis() - start}")
        }
    }

    /** 使用Atomic原子操作实现同步 140ms */
    @Test
    fun testAtomic() {
        runBlocking {
            val start = System.currentTimeMillis()
            val atomicCount = AtomicInteger(0)

            List(1000) {
                CoroutineScope(Dispatchers.Default).launch(Dispatchers.Default) {
                    repeat(10000) {
                        atomicCount.incrementAndGet()
                    }
                }
            }.joinAll()

            println("count值为：${atomicCount.get()}，Atomic原子操作耗时为：${System.currentTimeMillis() - start}")
        }
    }
}