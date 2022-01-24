package dataStruct.stack;

/**
 * Created by 15151 on 2019/1/21.
 */
public class SqStack<T> {
    private Object data[];//数组
    private int maxSize;//栈空间大小
    private int top;//栈顶指针

    @SuppressWarnings("unchekced")
    private SqStack(int maxSize) {
        this.maxSize = maxSize;
        this.data = new Object[maxSize];
        this.top = -1;//有的书上用的0
    }

    private boolean isNull() {
        return this.top <= -1;
    }

    private boolean isFull() {
        return this.top >= maxSize - 1;
    }

    private boolean push(T value) throws Exception {
        if (isFull()){
            throw new Exception("栈已满！");
        }
        data[++top]=value;
        return true;
    }

    private Object pop(){
        if (isNull()){
            return "栈已空！";
        }
        //TODO 这里存在一个内存泄漏的问题
        return data[top--];
    }

    public static void main(String[] args) throws Exception {
        SqStack<String> stack = new SqStack<>(5);
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        stack.push("5");
        System.out.println(stack.pop());
    }
}
