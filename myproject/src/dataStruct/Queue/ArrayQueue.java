package dataStruct.Queue;

/**
 * Created by 15151 on 2019/1/28.
 * 队列的数组实现方式  解决队列的假溢出方式很多，这里选择比较通用的循环队列
 */
public class ArrayQueue<E> {
    /**
     * 队列数组
     */
    private Object[] queue;
    /**
     * 头指针
     */
    private int front;
    /**
     * 尾指针
     */
    private int rear;

    public ArrayQueue(int maxSize) {
        queue = new Object[maxSize];
        front = rear = 0;
    }

    /**
     * 清空队列，将首位指针指向开始位置
     */
    public void clear() {
        front = rear = 0;
    }

    /**
     * 判断队列是否为空，当头节点和尾节点只指向0的位置则为空
     *
     * @return
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 获取队列长度
     *
     * @return
     */
    public int getLength() {
        //TODO
        return rear - front;
    }

    /**
     * 队首元素
     *
     * @return
     */
    public Object peek() {
        if (isEmpty()) {
            return null;
        } else {
            return queue[front];
        }
    }

    /**
     * 入队列
     *
     * @param element
     */
    public void offer(Object element) throws Exception {
        if ((rear + 1) % queue.length == front) {
            throw new Exception("队列已经满！");
        } else {
            queue[rear] = element;
            rear = (rear + 1) % queue.length;
        }
    }

    /**
     * 出队列
     */
    public Object poll() {
        if (front == rear) {
            return null;
        } else {
            Object o = queue[front];
            queue[front] = null;
            front = (front + 1) % queue.length;
            return o;
        }
    }

    public static void main(String[] args) {

    }

}
