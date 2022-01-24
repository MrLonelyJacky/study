package concurrent.chapter6;

import java.util.concurrent.TimeUnit;

/**
 * Created by vinci on 2020/4/27.
 * 和thread一样threadGroup也有父子关系
 * enumerate方法将active线程复制到Thread数组中
 * 注意：1、enumerate仅仅是个预估值，可能会有新的线程加入，或者其他线程死亡
 * 2、返回值并不等于数组长度，因为数组长度可能是你定义的
 */
public class ThreadGroupEnumerateThreads {
    public static void main(String[] args) throws InterruptedException {
        //默认myGroup的父级是当前线程的group
        ThreadGroup myGroup= new ThreadGroup("my group");
        //创建线程并放入group中
        Thread thread = new Thread(myGroup,()->{
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"myThread");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

        Thread[] list = new Thread[mainGroup.activeCount()];
        //将队规子group 一起放入list中
        int enumerate = mainGroup.enumerate(list);
        System.out.println(enumerate);
        //不递归子类将减少一个
        enumerate=mainGroup.enumerate(list,false);
        System.out.println(enumerate);
        for (Thread item:list){
            System.out.println(item.getName());
        }
    }
}
