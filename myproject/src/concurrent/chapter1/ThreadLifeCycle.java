package concurrent.chapter1;

/**
 * Created by 15151 on 2020/3/25.
 * the png  lifeCycle.png
 * the thread life cycle
 * 1.new state
 *  when u new a thread object it was not in running state yet
 * it just like a common object 新建一个thread就和普通对象没啥区别
 * 2.runnable  state
 * when u call the method start  the jvm just create a thread really
 * the thread is runnable but a thread start do not represent it can be executed
 * waiting for cpu scheduling. strictly speaking ,a thread is in runnable state
 * will terminate unexpectedly or be in running state  等待cpu调度
 * 3.running state 从cpu调度队列中被选中，运行状态可转换为其他状态，运行状态才真正执行run方法
 * in this state can change to other state
 * change to terminated state like stop method
 * change to block state like sleep method or use wait method to join in wait set
 * or obtain lock resource or network io
 * change to runnable state as cpu scheduler polling or the thread call the yield method give up cpu
 * 4.blocked state
 * as mentioned above running state can change to block
 * change to terminated state like stop method
 * change to runnable state like arrive at specified sleep time or be waked up by notify method
 * or obtain block resource????? or interrupt method  不明白为何获取锁资源
 * 5.terminated state
 * all the life cycle ends like 1.ends common 2.ends unexpectedly 3.jvm crash
 */
public class ThreadLifeCycle {

}
