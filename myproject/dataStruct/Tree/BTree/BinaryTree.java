package dataStruct.Tree.BTree;

import lombok.Data;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 二叉树
 */
@Data
public class BinaryTree<K extends Comparable<K>, V> implements Tree<K, V> {

    private BTreeNode<K, V> root;

    @Override
    public void put(K key, V value) {
        if (root == null) {
            root = new BTreeNode<>(key, value, null, null);
        } else {
            put(key, value, root);
        }

    }

    private BTreeNode<K, V> put(K key, V value, BTreeNode<K, V> node) {
        if (node == null) {
            return new BTreeNode<>(key, value, null, null);
        }
        int compare = key.compareTo(node.getKey());
        if (compare < 0) {
            System.out.println("存放左子树："+key+":"+value);
            BTreeNode<K, V> put = put(key, value, node.getLeft());
            node.setLeft(put);
            return node;

        } else if (compare > 0) {
            System.out.println("存放右子树："+key+":"+value);
            BTreeNode<K, V> put = put(key, value, node.getRight());
            node.setRight(put);
            return node;
        }else {
            return new BTreeNode<>(key, value, null, null);
        }

    }


    @Override
    public void delete(K key) {
        if (root == null) {
            throw new RuntimeException("二叉树未初始化不支持删除！！");
        } else {
            root = delete(key, root);
        }
    }

    private BTreeNode<K, V> delete(K key, BTreeNode<K, V> node) {

        if (node==null){
            return null;
        }
        int c = key.compareTo(node.getKey());

        if (c<0){
            BTreeNode<K, V> delete = delete(key, node.getLeft());
            node.setLeft(delete);
            return node;
        }else if (c>0){
            BTreeNode<K, V> delete = delete(key, node.getRight());
            node.setRight(delete);
            return node;
        }else {
            //查找右子树最小左子树
            Optional<BTreeNode<K, V>> minLeft = getMinLeft(node);
            //删除右子树最小左子树
            deleteMinLeft(node);
            if (minLeft.isPresent()){
                BTreeNode<K, V> kvbTreeNode = minLeft.get();
                kvbTreeNode.setLeft(node.getLeft());
                kvbTreeNode.setRight(node.getRight());
                node = kvbTreeNode;
            }else {
                //没有最小左子树 如果有右子树先替换右子树，没有替换左子树
                if (node.getRight()!=null){
                    node = node.getRight();
                }else if (node.getLeft()!=null){
                    node = node.getLeft();
                }else {
                    return null;
                }

            }
            return node;
        }

        /*BTreeNode<K, V> kvbTreeNode = get(key, node);
        if (kvbTreeNode != null) {
            Optional<BTreeNode<K, V>> minLeft = getMinLeft(kvbTreeNode.getRight());
            if (minLeft.isPresent()) {
                if (kvbTreeNode.getParent()==null){
                    //说明是根节点,取代根节点
                    this.root = minLeft.get();
                }else {
                    kvbTreeNode.getParent().setRight(minLeft.get());
                }

            } else {
                if (kvbTreeNode.getParent()==null){
                    //说明是根节点,取代根节点
                    this.root = kvbTreeNode.getLeft();
                }else {
                    //右子树为空
                    kvbTreeNode.getParent().setLeft(kvbTreeNode.getLeft());
                }

            }
        }*/

    }

    private void deleteMinLeft(BTreeNode<K,V> node) {

        BTreeNode<K, V> result = node.getRight();
        BTreeNode<K, V> prev = node;

        if (result == null) {
            return;
        }
        while (result.getLeft() != null) {
            prev=result;
            result = result.getLeft();
        }
        if (result!=node.getRight()){
            //说明有最小左子树，否则不处理
            prev.setLeft(null);
        }


    }

    /**
     * 获取最小左子树
     *
     * @param node
     */
    private Optional<BTreeNode<K, V>> getMinLeft(BTreeNode<K, V> node) {
        if (node.getRight() == null) {
            return Optional.empty();
        }
        BTreeNode<K, V> result = node.getRight();
        while (result.getLeft() != null) {
            result = result.getLeft();
        }
        if (result == node.getRight()){
            //没有最小左子树
            return Optional.empty();
        }
        return Optional.of(result);
    }


    @Override
    public BTreeNode<K, V> get(K key) {
        if (root == null) {
            return null;
        } else {
            return get(key, root);
        }
    }

    private BTreeNode<K, V> get(K key, BTreeNode<K, V> root) {
        if (root == null) {
            return null;
        }
        int compare = key.compareTo(root.getKey());
        if (compare < 0) {
            return get(key, root.getLeft());
        } else if (compare > 0) {
            return get(key, root.getRight());
        } else {
            return root;
        }
    }

    public List<BTreeNode<K,V>> getPreForeachResult(){
        List<BTreeNode<K,V>> result = new ArrayList<>();
        preForeach(this.root,result);
        return result;
    }


    private void preForeach(BTreeNode<K,V> node, List<BTreeNode<K,V>> result){
        if (node==null){
            return;
        }
        result.add(node);
        preForeach(node.getLeft(),result);
        preForeach(node.getRight(),result);
    }

    public List<BTreeNode<K,V>> getMiddleForeachResult(){
        List<BTreeNode<K,V>> result = new ArrayList<>();
        middleForeach(this.root,result);
        return result;
    }

    private void middleForeach(BTreeNode<K,V> node, List<BTreeNode<K,V>> result){
        if (node==null){
            return;
        }
        middleForeach(node.getLeft(),result);
        result.add(node);
        middleForeach(node.getRight(),result);
    }

    public List<BTreeNode<K,V>> getLevelForeachResult(){
        List<BTreeNode<K,V>> result = new ArrayList<>();
        levelForeach(this.root,result);
        return result;
    }

    private void levelForeach(BTreeNode<K,V> node, List<BTreeNode<K,V>> result){
        if (node==null){
            return;
        }
        result.add(node);
        int count =0;
        while (result.size()>count){
            BTreeNode<K, V> treeNode = result.get(count);
            if (treeNode!=null&&treeNode.getLeft()!=null){
                result.add(treeNode.getLeft());
            }
            if (treeNode!=null&&treeNode.getRight()!=null){
                result.add(treeNode.getRight());
            }

            count++;
        }




    }

    @Override
    public int size() {
        return 0;
    }

    public static void main(String[] args) {
        BinaryTree<Integer, String> tree = new BinaryTree<>();
        tree.put(8, "8");
        tree.put(7, "7");
        tree.put(9, "9");
        tree.put(4, "4");
        tree.put(5, "5");
        tree.put(6, "6");
        List<BTreeNode<Integer, String>> preForeachResult = tree.getPreForeachResult();
        preForeachResult.forEach(item-> System.out.println(item.getKey()));
        System.out.println("----------------------------");
        List<BTreeNode<Integer, String>> middleForeachResult = tree.getMiddleForeachResult();
        middleForeachResult.forEach(item-> System.out.println(item.getKey()));
        System.out.println("----------------------------");
        List<BTreeNode<Integer, String>> levelForeachResult = tree.getLevelForeachResult();
        levelForeachResult.forEach(item-> System.out.println(item.getKey()));
        tree.delete(9);
        tree.delete(7);

        System.out.println("删除后键5对应的元素是： " + tree.get(9));
        System.out.println("删除后键5对应的元素是： " + tree.get(7));
        System.out.println("最终结构： " + tree.root);

    }
}
