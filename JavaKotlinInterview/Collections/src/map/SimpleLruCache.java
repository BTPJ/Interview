package map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 简易的LruCache
 *
 * @author LTP  2021/12/7
 */
public class SimpleLruCache<K, V> extends LinkedHashMap<K, V> {

    private static final int MAX_NODE_NUM = 2 << 4;

    private final int limit;

    public SimpleLruCache() {
        this(MAX_NODE_NUM);
    }

    public SimpleLruCache(int limit) {
        super(limit, 0.75f, true);
        this.limit = limit;
    }

    public V putValue(K key, V val) {
        return put(key, val);
    }

    public V getValue(K key) {
        return get(key);
    }

    /**
     * 判断存储元素个数是否预定阈值
     *
     * @return 超限返回 true，否则返回 false
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > limit;
    }

    public static void main(String[] args) {
        // 构建一个阈值为 3 的 LruCache 类
        SimpleLruCache<String,Integer> simpleLruCache = new SimpleLruCache<>(3);


        simpleLruCache.putValue("老大", 1);
        simpleLruCache.putValue("老二", 2);
        simpleLruCache.putValue("老三", 3);

        simpleLruCache.getValue("老大");

        //超过指定 阈值 3 再次添加元素的 将会删除最近最少访问的节点
        simpleLruCache.putValue("老四", 4);

        System.out.println("lruCache = " + simpleLruCache);
    }
}
