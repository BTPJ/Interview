package com.btpj.by

/**
 * @author LTP  2023/8/23
 */
class AnimalSound(sound: Sound) : Sound by sound {
    /** 学狗叫 */
    fun studyDogSound() {
        makeSound()
    }
}