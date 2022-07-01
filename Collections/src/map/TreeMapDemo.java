package map;

import java.util.TreeMap;

/**
 * TreeMap相关Demo
 * 红黑树
 *
 * @author BTPJ  2021/7/20
 */
public class TreeMapDemo {

    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(1, "LTP1");
        treeMap.put(2, null);
        treeMap.put(4, null);
        treeMap.put(3, "LTP3");
        System.out.println(treeMap);

        TreeMap<Integer, String> treeMap2 = new TreeMap<>((t1, t2) -> t2 - t1);
        treeMap2.put(1, "LTP1");
        treeMap2.put(2, null);
        treeMap2.put(4, null);
        treeMap2.put(3, "LTP3");
        System.out.println(treeMap2);
    }
}
