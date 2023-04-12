package reference;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 弱引用
 * 无论内存是否充足，只要手动调用垃圾回收System.gc()或等待虚拟机自动GC，弱引用就会被回收。
 * 主要用来放置在容易引起内存泄漏的位置，如Android中的Handler
 *
 * @author BTPJ  2023/3/15
 */
public class WeakReferenceDemo {

    public static void main(String[] args) {
        testWeakReference();
    }

    private static void testWeakReference() {
        int[] a = new int[3];
        // 软引用对象中指向了一个长度为300000000个元素的整形数组
        WeakReference<int[]> weakReference = new WeakReference<>(new int[5]);
        System.out.println("调用GC前强引用：" + Arrays.toString(a));
        System.out.println("调用GC前弱引用：" + Arrays.toString(weakReference.get()));

        // 手动GC或虚拟机自动GC都会回收弱引用，这里手动调用GC
        System.gc();

        System.out.println("调用GC后强引用：" + Arrays.toString(a));
        System.out.println("调用GC后弱引用：" + Arrays.toString(weakReference.get()));
    }
}
