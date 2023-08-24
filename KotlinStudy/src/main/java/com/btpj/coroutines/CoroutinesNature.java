package com.btpj.coroutines;

/**
 * @author LTP  2023/8/21
 */
class CoroutinesNature {

    public static void main(String[] args) {
        plus(1, 2, result -> System.out.println(result));
    }

    private static void plus(int num1, int num2, Continuation continuation) {
        continuation.next(num1 + num2);
    }

    interface Continuation {
        void next(int result);
    }
}
