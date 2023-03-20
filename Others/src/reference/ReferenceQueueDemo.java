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
        // 软引用对象中指向了一个长度为300000000个元素的整形数组
        ReferenceQueue<int[]> referenceQueue = new ReferenceQueue<>();
        WeakReference<int[]> weakReference = new WeakReference<>(new int[5], referenceQueue);
//        WeakReference<int[]> weakReference = new WeakReference<>(new int[6], referenceQueue);
        System.out.println("调用GC前弱引用：" + Arrays.toString(weakReference.get()));
        System.out.println("调用GC前引用队列：" + referenceQueue.poll());

        // 手动GC或虚拟机自动GC都会回收弱引用，这里手动调用GC
        System.gc();

        System.out.println("调用GC后弱引用：" + Arrays.toString(weakReference.get()));
        System.out.println("调用GC后引用队列：" + referenceQueue.poll());
    }
}
