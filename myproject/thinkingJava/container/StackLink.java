package thinkingJava.container;

import java.util.LinkedList;

/**
 * Created by 15151 on 2019/3/27.
 * 从java角度来说 一个linklist就能满足栈的基本操作
 */
public class StackLink<T> {
    private LinkedList<T> storage = new LinkedList<>();
    public void push(T t){
        storage.add(t);
    }
    public  T peek(){
        return storage.getFirst();
    }
    public T pop(){
        return storage.removeFirst();
    }
    public boolean empty(){
        return storage.isEmpty();
    }
    public String toString(){
        return storage.toString();
    }

    public static void main(String[] args) {
        StackLink<String> stringStackLink = new StackLink<>();
        for (String s : "My dog is a big dog!".split(" ")){
            stringStackLink.push(s);
        }
        while (!stringStackLink.empty()){
            System.out.println(stringStackLink.pop());
        }

    }
}
