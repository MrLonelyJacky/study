package dataStruct.Tree.BTree;

import lombok.Data;

/**
 * Created by 15151 on 2019/6/13.
 */
@Data
public class BTreeNode<K, V> {

    private K key;
    private V value;

    private BTreeNode<K,V> left;
    private BTreeNode<K,V> right;


    public BTreeNode(K key, V value, BTreeNode<K, V> left, BTreeNode<K, V> right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
