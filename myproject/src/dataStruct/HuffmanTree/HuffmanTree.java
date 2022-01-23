package dataStruct.HuffmanTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 15151 on 2019/2/12.
 */
public class HuffmanTree {
    /**
     * 内部类节点
     *
     * @param <E>
     */
    public static class Node<E> implements Comparable<Node<E>> {
        E data;
        double weight;
        Node leftChild;
        Node rightChild;
        Node parent;

        public Node(E data, double weight) {
            this.data = data;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node<E> o) {
            if (o.weight > this.weight) {
                return 1;
            }
            if (o.weight < this.weight) {
                return -1;
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<Node>();
        nodes.add(new Node("A", 5));
        nodes.add(new Node("B", 4));
        nodes.add(new Node("C", 3));
        nodes.add(new Node("D", 2));
        nodes.add(new Node("E", 1));
        HuffmanTree.Node node = createHuffmanTree(nodes);
        System.out.println(node.weight);
        System.out.println(nodes.size());
        createHuffmanCode(node.leftChild.leftChild);
    }

    /**
     * 构造霍夫曼树
     */
    public static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node left = nodes.get(nodes.size() - 1);
            Node right = nodes.get(nodes.size() - 2);
            left.data = 0;
            right.data = 1;
            Node parent = new Node(null, left.weight + right.weight);
            parent.leftChild = left;
            parent.rightChild = right;
            left.parent = parent;
            right.parent = parent;
            nodes.remove(nodes.size() - 1);
            nodes.remove(nodes.size() - 1);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    /**
     * 实现哈夫曼编码
     */
    public static void createHuffmanCode(Node node) {
        while (node.data != null) {
            System.out.println(node.data);
            node = node.parent;
        }
    }
}
