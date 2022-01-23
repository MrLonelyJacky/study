package dataStruct.stackAndQueue;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by 15151 on 2019/7/1.
 * 用栈实现表达式的匹配，这里只包含{},[],()
 */
public class ExpressionMatch {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String next = scanner.next();
            if ("{".equals(next)) {
                stack.push("{");
            } else if ("(".equals(next)) {
                stack.push("(");
            } else if ("[".equals(next)) {
                stack.push("[");
            } else if ("}".equals(next)) {
                String pop = stack.pop();
                if (!pop.equals("{")) {
                    System.out.println("不匹配");
                    return;
                }
            } else if (")".equals(next)) {
                String pop1 = stack.pop();
                if (!pop1.equals("(")) {
                    System.out.println("不匹配");
                    return;
                }
            } else if ("]".equals(next)) {
                String pop2 = stack.pop();
                if (!pop2.equals("[")) {
                    System.out.println("不匹配");
                    return;
                }
            } else if ("exit".equals(next)) {
                break;
            }
        }
        if (stack.isEmpty()) {
            System.out.println("完全匹配！");
        } else {
            System.out.println("未完全匹配");
        }
    }
}
