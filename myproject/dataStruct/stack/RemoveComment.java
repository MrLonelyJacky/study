package dataStruct.stack;

import effectiveJava.chapter5.Stack;

/**
 * 删除代码中的注释部分
 */
public class RemoveComment {


    public static void main(String[] args) {
        String[] source = new String[]{"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test  multiline  comment for  testing */", "a = b + c;", "}"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < source.length; i++) {
            sb.append(getByRemoveComment(source[i]));
        }
        System.out.println(sb.toString());
    }

    public static String getByRemoveComment(String args) {
        boolean isDoubleFlag = false;
        int firstFlagIndex = 0;
        boolean isBlockFlag = false;
        for (int i = 0; i < args.length() - 1; i++) {
            String charWithNext = args.substring(i, i + 2);
            if ("//".equals(charWithNext)) {
                isDoubleFlag = true;
                firstFlagIndex = i;
                break;
            }
            if ("/*".equals(charWithNext)) {
                isBlockFlag = true;
                firstFlagIndex = i;
                break;
            }
        }
        if (isDoubleFlag) {
            return args.substring(0, firstFlagIndex);
        }
        if (isBlockFlag) {
            int endFlag = -1;
            for (int i = firstFlagIndex; i < args.length() - 1; i++) {
                String charWithNext = args.substring(i, i + 2);
                if ("*/".equals(charWithNext)) {
                    endFlag = i;
                }
            }
            return args.substring(0, firstFlagIndex) + args.substring(endFlag + 2);
        }
        return args;
    }
}
