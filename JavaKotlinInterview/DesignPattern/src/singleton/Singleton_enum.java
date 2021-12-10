package singleton;

/**
 * 枚举，一般不用枚举，可读性差，而且使用枚举会造成内存加大（每个枚举类都会申明成一个静态变量），Android不推荐
 *
 * @author LTP  2021/11/9
 */
class Singleton_enum {

    public static void main(String[] args) {
        SingleTon singleTon = SingleTon.INSTANCE;
    }

    enum SingleTon {
        INSTANCE
    }
}


