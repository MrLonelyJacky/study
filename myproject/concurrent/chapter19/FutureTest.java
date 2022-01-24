package concurrent.chapter19;

import java.util.concurrent.TimeUnit;

public class FutureTest {

    public static void main(String[] args) throws InterruptedException {
        FutureService<Void, Void> service = FutureService.newService();
        //submit方法为立即返回的方法
        Future<Void> future = service.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("finish done");
        });
        System.out.println("返回凭据future" + future);
        //get方法会使当前线程阻塞
        future.get();
        FutureService<String, Integer> futureService = FutureService.newService();
        Future<Integer> hello = futureService.submit(input -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return input.length();
        }, "hello");
        System.out.println("返回凭据:" + hello);
        System.out.println(hello.get());
    }


}
