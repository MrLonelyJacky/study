package dataStruct.stackAndQueue;

import java.util.Stack;

/**
 * Created by 15151 on 2019/7/4.
 * 非递归方式实现  f(m,n) = n+1; m=0
 * f(m,n) = f(m-1,1) m!=0,n=0
 * f(m,n) = f(m-1,f(m,n-1))
 */
public class Shulie {
    public static void main(String[] args) {
        System.out.println(func(2, 1));
    }

    /**
     * Created by 15151 on 2019/7/4.
     * 非递归方式实现  f(m,n) = n+1; m=0
     * f(m,n) = f(m-1,1) m!=0,n=0
     * f(m,n) = f(m-1,f(m,n-1))
     */
    public static int func(int m, int n) {
        int result;
        Stack<Integer> stack = new Stack<>();
        while (true) {
            if (m == 0) {
                result = n + 1;
                n = result;
                if (stack.isEmpty()) {
                    return result;
                }
                m = stack.pop();
            } else if (n == 0) {
                n = 1;
                m--;
            } else {
                stack.push(m - 1);
                n = n - 1;
            }
        }
    }
}
