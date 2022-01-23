package dataStruct.algorithm.lru;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LruCache<K, V> {

    private final Map<K, V> cacheMap;
    private final int cacheSize;

    public LruCache(int cacheSize) {
        if (cacheSize<=0){
            throw new RuntimeException("size can not lte 0");
        }
        this.cacheSize = cacheSize;
        this.cacheMap = new LinkedHashMap<>(16, 0.75f, true);
    }

    public void put(K key, V value) {
        if (this.cacheMap.size() > cacheSize) {
            //删除表头的值 最近最少访问 因为linkedHashMap中采用尾插法，所以删除表头表示最少访问
            Set<Map.Entry<K, V>> entrySet = this.cacheMap.entrySet();
            Iterator<Map.Entry<K, V>> iterator = entrySet.iterator();
            Map.Entry<K, V> next = iterator.next();
            this.cacheMap.remove(next.getKey());
        }
        this.cacheMap.put(key, value);
    }


    public static void main(String[] args) {

       /* Map<String, Integer> map = new LinkedHashMap<>(16, 0.75f, true);
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("a", 1);
        System.out.println(map.size());
        map.forEach((key, value) -> System.out.println(key + ":" + value));*/
       LruCache<String,Integer> lruCache = new LruCache<>(6);
       lruCache.put("a",1);
       lruCache.put("b",1);
       lruCache.put("c",1);
       lruCache.put("d",1);
       lruCache.put("e",1);
       lruCache.put("f",1);
       lruCache.put("g",1);
       lruCache.put("c",1);
       lruCache.cacheMap.forEach((k,v)-> System.out.println(k));
    }
}
