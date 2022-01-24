package dataStruct.recursion;

/**
 * Created by 15151 on 2019/6/26.
 * 非递归实现   m = 0 f(m,n) = n+1
 * m!=0 n=0 f(m,n) = f(m-1,1)
 * m!=0 n!=0 f(m,n) = f(m-1,f(m,n-1))
 */
public class FunctionMN {
    int value;

    public void func(int m, int n) {
        if (m == 0) {
            value = n + 1;
        }
        if (m != 0 && n == 0) {
            while (m != 0) {

            }
        }
    }
}
