package map;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * LinkedHashMap相关Demo
 *
 * @author BTPJ  2021/7/20
 */
public class LinkedHashMapDemo {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("LTP1", "LTP1");
        map.put("LTP2", "LTP2");
        map.put("LTP3", "LTP3");
        map.put("LTP4", "LTP4");
        System.out.println(map);
        // hash是复制的hashMap的hash方法15为默认capacity-1
        map.forEach((key, value) -> System.out.println("index：" + (hash(key) & 15) + " ~ " + key + "：" + value));

        System.out.println("------------------");

        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16, 0.75f, true);
        linkedHashMap.put("LTP1", "LTP1");
        linkedHashMap.put("LTP2", "LTP2");
        linkedHashMap.put("LTP3", "LTP3");
        linkedHashMap.put("LTP4", "LTP4");
        System.out.println(linkedHashMap);

        linkedHashMap.get("LTP2");
        linkedHashMap.get("LTP4");
        System.out.println(linkedHashMap);
    }

    /**
     * hash算法
     *
     * @param key key
     * @return key的hash值
     */
    private static int hash(Object key) {
        int h;
        return key == null ? 0 : (h = key.hashCode()) ^ h >>> 16;
    }
}
