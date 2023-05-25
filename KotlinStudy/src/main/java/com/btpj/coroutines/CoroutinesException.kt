package com.btpj.coroutines

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import org.junit.Test

/**
 * 协程异常处理
 *
 * @author LTP  2023/5/25
 */
class CoroutinesException {

    /**
     * 测试主协程抛异常并捕获
     * 方式1：直接在异常位置try-catch
     */
    @Test
    fun catchMainCoroutineException() = runBlocking {
        val job = CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            try {
                throw Exception("协程抛出异常")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * 测试主协程抛异常并捕获
     * 方式2：使用CoroutineExceptionHandler捕获
     */
    @Test
    fun catchMainCoroutineException2() = runBlocking {
        val handler = CoroutineExceptionHandler { _, throwable ->
            println(throwable.message)
            throwable.printStackTrace()
        }

        val job = CoroutineScope(Dispatchers.IO + handler).launch {
            delay(1000)
            throw Exception("协程抛出异常")
        }

        job.join()
    }

    /**
     * 测试主协程抛异常并捕获
     * 方式1和方式2的区别：CoroutineExceptionHandler不会捕获协程取消异常
     * CoroutineExceptionHandler不能捕获async启动的协程
     *
     */
    @Test
    fun catchMainCoroutineException3() = runBlocking<Unit> {
        val job = CoroutineScope(Dispatchers.IO).launch {
            try {
                delay(1000)
            } catch (e: Exception) {
                println(e.message)
                e.printStackTrace()
            }
        }
        job.cancelAndJoin()

        val handler = CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }
        val job2 = CoroutineScope(Dispatchers.IO + handler).launch {
            delay(1000)
        }
        job2.cancelAndJoin()

        // CoroutineExceptionHandler也无法处理async的异常
        val deferred = async(handler) {
            delay(1000)
            throw Exception("使用async启动的协程，无法使用CoroutineExceptionHandler捕获异常")
        }
        deferred.await()
    }

    /**
     * 测试子协程抛异常并捕获
     * CoroutineScope创建的作用域中一个子协程异常其他子协程也自动取消
     */
    @Test
    fun catchChildCoroutineException() = runBlocking {
        val handler = CoroutineExceptionHandler { _, throwable ->
            println(throwable.message)
            throwable.printStackTrace()
        }

        val job = CoroutineScope(Dispatchers.IO + handler).launch {
            launch {
                delay(1000)
                throw Exception("子协程抛出异常，父协程也会捕获到")
            }

            launch {
                delay(2000)
                println("子协程抛出异常，其他子协程也会退出，所以这里不会打印")
            }
        }
        job.join()
    }

    /**
     * 测试子协程抛异常并捕获
     * 方式1和方式2的区别：CoroutineExceptionHandler不会捕获协程取消异常
     */
    @Test
    fun catchChildCoroutineException2() = runBlocking {
        val job = supervisorScope {
            launch {
                delay(1000)
                throw Exception("子协程抛出异常，")
            }

            launch {
                delay(2000)
                println("使用supervisorScope的作用域，一个子协程抛异常，其他子协程不会退出")
            }
        }
        job.join()
    }

    /**
     * 测试子协程抛异常并捕获
     * 方式1和方式2的区别：CoroutineExceptionHandler不会捕获协程取消异常
     */
    @Test
    fun catchChildCoroutineException3() = runBlocking {
        val handler = CoroutineExceptionHandler { _, throwable ->
            // suppressedExceptions会聚合所有子线程的异常
            println("${throwable.message}-----${throwable.suppressedExceptions}")
            throwable.printStackTrace()
        }
        val job = CoroutineScope(handler).launch {
            launch {
                delay(1000)
                throw Exception("子协程抛异常，会导致其他子协程也取消")
            }

            launch {
                try {
                    delay(2000)
                } catch (e: Exception) {
                    throw Exception("这里会捕获取消异常后，我再抛一个异常")
                }
            }

            launch {
                try {
                    delay(3000)
                } catch (e: Exception) {
                    throw Exception("这里会捕获取消异常后，我再抛一个其他异常")
                }
            }
        }
        job.join()
    }
}