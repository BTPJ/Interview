package reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/**
 * 弱引用
 * 无论内存是否充足，只要手动调用垃圾回收System.gc()或等待虚拟机自动GC，弱引用就会被回收。
 * 主要用来放置在容易引起内存泄漏的位置，如Android中的Handler
 *
 * @author BTPJ  2023/3/15
 */
public class ReferenceQueueDemo {

    public static void main(String[] args) {
        testWeakReference();
    }

    private static void testWeakReference() {
        // 构造一个强引用
        Object obj = new Object();
        // 创建引用队列
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        // 利用强引用和引用队列构造弱引用
        WeakReference<Object> weakReference = new WeakReference<>(obj, referenceQueue);
        System.out.println("调用GC前弱引用：" + weakReference.get()); // java.lang.Object@6108b2d7
        System.out.println("调用GC前引用队列：" + referenceQueue.poll()); // null

        // 将强引用手动置null
        obj = null;
        // 手动GC或虚拟机自动GC都会回收弱引用，这里手动调用GC
        System.gc();

        System.out.println("调用GC后弱引用：" + weakReference.get()); // null
        System.out.println("调用GC后引用队列：" + referenceQueue.poll()); // java.lang.Object@6108b2d7
    }
}
