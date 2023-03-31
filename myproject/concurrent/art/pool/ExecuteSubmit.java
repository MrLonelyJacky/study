package concurrent.art.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: jacky
 * @create: 2023-03-31 09:38
 **/
public class ExecuteSubmit {

    public static void main(String[] args) {
        //创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        //当线程池抛出异常后 submit无提示，其他线程继续执行
        executorService.submit(new task());

        //当线程池抛出异常后 execute抛出异常，其他线程继续执行新任务
        executorService.execute(new task());
    }
}
// 任务类
class task implements Runnable {


    @Override
    public void run() {

        try {
            System.out.println("进入了task方法！！！");
            int i = 1 / 0;
        } catch (Exception e) {

            System.out.println("使用了try -catch 捕获异常" + e);
        }
    }
}