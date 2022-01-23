package concurrent.chapter19;

/**
 * Created by 15151 on 2020/5/27.
 * future实际上是个凭据，可以添加方法进行如取消任务的操作
 * 该接口用于获取计算结果和判断任务是否完成
 */
public interface Future<T> {
    //返回计算的结果，该方法会进入阻塞状态
    T get() throws InterruptedException;
    //判断任务是否已经被执行完成
    boolean done();
}
