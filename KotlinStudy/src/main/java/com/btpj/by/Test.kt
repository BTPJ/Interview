package com.btpj.by

import java.util.concurrent.ConcurrentHashMap

/**
 * @author LTP  2023/8/23
 */
fun main() {
    val dogSound = DogSound()
    val animalSound = AnimalSound(dogSound)
    animalSound.studyDogSound() // 输出 "汪汪汪"
}