package dataStruct.Tree.BTree;

public interface Tree<K extends Comparable<K>,V> {
    /**
     * 新增节点
     * @param key
     * @param value
     */
    void put(K key,V value);

    /**
     * 删除节点
     * @param key
     */
    void delete(K key);

    /**
     * 查找
     * @param key
     * @return
     */
    BTreeNode<K,V> get(K key);

    /**
     * 大小
     * @return
     */
    int size();
}
