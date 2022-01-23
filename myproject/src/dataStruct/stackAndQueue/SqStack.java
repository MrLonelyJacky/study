/*
package dataStruct.stackAndQueue;

import java.util.Stack;

*/
/**
 * Created by 15151 on 2019/6/28.
 * 顺序栈的实现
 *//*

public class SqStack {
    private static final int MAX_SIZE = 100;
    private int[] sz = new int[MAX_SIZE];
    private int top = -1;//栈顶指针

    private void push(int data.txt) {
        //visit left right
        //left right visit
        top++;
        sz[top] = data.txt;

    }

    private void getHeitht(int a){
        if (a == -1){
            return;
        }
        //递归左子树

    }


    public static void main(thinkingJava.String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(p);//放置根节点
        boolean flag = true;//根节点标识  刚开始存放根节点，后面就不存放
        while (!stack.isEmpty()) {
            if (p) {
                if (!flag){
                    stack.push(p);
                }
                flag =false;
                p = p->left;
            } else {
                p = stack.pop();//抛出并设置
                visit();
                p= p ->right;
            }
        }
    }

    private static void visit() {
        //线序遍历
        Stack<Integer> stack = new Stack<>();
        stack.push(p);//放置根节点
        visit();//先访问根节点
        boolean flag = true;//根节点标识  刚开始存放根节点，后面就不存放
        while (!stack.isEmpty()) {
            if (p) {
                if (!flag){
                    visit();//先访问节点
                    stack.push(p);//访问玩融入站
                }
                flag =false;
                p = p->left;
            } else {
                p = stack.pop();
                p = p->right;
            }
        }
    }

    public void inOrder() {
        //先左子树再根节点再右子树
        //入栈根节点
        Stack<Integer> stack = new Stack<>();
        push();
        while (!stack.isEmpty()) {
            //栈不为空
            if (p -> left != null) {
                //左子树不为空 一直走下去
                push();
                p = p -> left;
            }
            //左孩子为空，就找到根节点了
            else {
                push();//根节点出战
                visit();//访问根节点
                p = p -> right;//递归右子树
                //走到这说明左子树走完，开始走右子树
                //可是有个问题右子树可能为空，如果为空，直接出战，不为空则继续看右子树有无自己的左子树
                if (p == null) {
                    //如果右子树是空 过程结束
                    //让p等于栈顶元素
                    p = push();
                    p = p -> right;
                }
            }
        }
    }
}
*/
