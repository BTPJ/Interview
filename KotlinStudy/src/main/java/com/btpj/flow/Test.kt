package com.btpj.flow

/**
 * @author LTP  2023/5/30
 */
fun main() {
    val list = listOf(2, 3, 5, 6, 8, 7, 9, 11)
    list.filter {
        (2 until it).map { item -> it % item }.none { it1 -> it1 == 0 }
    }.lsPrint()
}

fun <T> T.lsPrint() = println(this)

inline fun <T, R> T.let2(block: (T) -> R): R {
    return block(this)
}

inline fun <T> T.apply2(block: T.() -> Unit): T {
    block()
    return this
}

inline fun <T, R> T.run2(block: T.() -> R): R {
    return block()
}

inline fun <T, R> with2(t: T, block: T.() -> R): R {
    return t.block()
}

inline fun <T> T.also2(block: (T) -> Unit): T {
     return this
}
