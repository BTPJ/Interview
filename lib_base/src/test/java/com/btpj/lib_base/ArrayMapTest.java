package com.btpj.lib_base;

import android.util.SparseArray;

import org.junit.Test;

import java.util.HashMap;

import androidx.collection.ArrayMap;

/**
 * @author LTP  2021/12/13
 */
public class ArrayMapTest {

    @Test
    public void arrayMapPut() {
        ArrayMap<String, Integer> arrayMap = new ArrayMap<>();
        long time = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            arrayMap.put("数据" + i, i);
        }
        // 当倒叙时key存储的顺序为由大到小，而arrayMap存储的key是由小到大，速度会更慢
        // 所以当存储的key值正序率比较高时效率更高
        // for (int i = 20000; i > 0; i--) {
        //  arrayMap.put("数据" + i, i);
        //}
        System.out.println("arrayMap的put消耗时间：" + (System.currentTimeMillis() - time) + "ms");

        HashMap<String, Integer> hashMap = new HashMap<>();
        long time2 = System.currentTimeMillis();
        for (int i = 20000; i > 0; i--) {
            hashMap.put("数据" + i, i);
        }
        System.out.println("hashMap的put消耗时间：" + (System.currentTimeMillis() - time2) + "ms");
    }

    @Test
    public void sparseArrayPut() {
        SparseArray<String> sparseArray = new SparseArray<>();
        long time = System.currentTimeMillis();
        for (int i = 0; i < 2000000; i++) {
            sparseArray.put(i, "数据" + i);
        }
        System.out.println("SparseArray的put消耗时间：" + (System.currentTimeMillis() - time) + "ms");
        long time3 = System.currentTimeMillis();
        for (int i = 0; i < 2000000; i++) {
            sparseArray.get(i);
        }
        System.out.println("SparseArray的get消耗时间：" + (System.currentTimeMillis() - time3) + "ms");

        HashMap<Integer, String> hashMap = new HashMap<>();
        long time2 = System.currentTimeMillis();
        for (int i = 2000000; i > 0; i--) {
            hashMap.put(i, "数据" + i);
        }
        System.out.println("hashMap的put消耗时间：" + (System.currentTimeMillis() - time2) + "ms");
        long time4 = System.currentTimeMillis();
        for (int i = 0; i < 2000000; i++) {
            hashMap.get(i);
        }
        System.out.println("hashMap的get消耗时间：" + (System.currentTimeMillis() - time4) + "ms");
    }
}

