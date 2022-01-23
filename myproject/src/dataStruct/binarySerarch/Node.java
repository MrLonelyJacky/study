package dataStruct.binarySerarch;

import com.sun.javafx.iio.gif.GIFImageLoaderFactory;

import java.util.Arrays;

public class Node {
    int val;
    Node left;
    Node right;

    public Node(int val) {
        this.val = val;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.right.left = new Node(4);
        root.right.right = new Node(5);
        StringBuilder builder = new StringBuilder();
        String[] strings = new String[7];
        binary(root, strings, 0);
        System.out.println(Arrays.toString(strings));
    }

    public static void binary(Node node, String[] strings, int i) {
        if (node != null) {
            strings[i] = node.val + "";
            binary(node.left, strings, 2 * i + 1);
            binary(node.right, strings, 2 * i + 2);
        } else {
            if (i<strings.length){
                strings[i] = "null";
            }
        }
    }
}
