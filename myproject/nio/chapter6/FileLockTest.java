package nio.chapter6;

import lombok.SneakyThrows;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: jacky
 * @create: 2022-09-09 09:47
 **/
public class FileLockTest {

    @Test
    public void test1() throws InterruptedException, ExecutionException, IOException {
        Path path = Paths.get("myproject/nio/file/a.txt");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
        System.out.println("lock start "+System.currentTimeMillis());
        Future<java.nio.channels.FileLock> future = fileChannel.lock();
        System.out.println("lock end"+System.currentTimeMillis());
        FileLock fileLock = future.get();
        System.out.println("get lock time"+System.currentTimeMillis());
        //给定时间test2方法启动
        TimeUnit.SECONDS.sleep(8);
        fileLock.release();
        System.out.println("release lock time"+System.currentTimeMillis());
        fileChannel.close();
    }


    @Test
    public void test2() throws InterruptedException, ExecutionException, IOException {
        Path path = Paths.get("myproject/nio/file/a.txt");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
        System.out.println("lock begin"+System.currentTimeMillis());
        Future<java.nio.channels.FileLock> future = fileChannel.lock();
        System.out.println("lock end"+System.currentTimeMillis());
        FileLock fileLock = future.get();
        System.out.println("get lock time"+System.currentTimeMillis());
        fileLock.release();
        System.out.println("release lock time"+System.currentTimeMillis());
        fileChannel.close();
    }


    @Test
    public void test3() throws InterruptedException, IOException {
        Path path = Paths.get("myproject/nio/file/a.txt");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
        System.out.println("begin time"+System.currentTimeMillis());
        fileChannel.lock("我是附加值", new CompletionHandler<FileLock, String>() {
            @Override
            public void completed(FileLock result, String attachment) {
                System.out.println("public void completed attachment="+attachment);
                try {
                    result.release();
                    fileChannel.close();
                    System.out.println("release and close");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, String attachment) {
                System.out.println("failed hahaha");
            }
        });
        System.out.println("end time="+System.currentTimeMillis());
        TimeUnit.SECONDS.sleep(3);
    }

    @Test
    public void test4() throws InterruptedException, IOException {
        Path path = Paths.get("myproject/nio/file/a.txt");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE,StandardOpenOption.READ);
        fileChannel.close();
        System.out.println("begin time"+System.currentTimeMillis());
        fileChannel.lock("我是附加值", new CompletionHandler<FileLock, String>() {
            @Override
            public void completed(FileLock result, String attachment) {
                System.out.println("public void completed attachment="+attachment);
                try {
                    result.release();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, String attachment) {
                System.out.println("failed hahaha");
            }
        });
        System.out.println("end time="+System.currentTimeMillis());
        TimeUnit.SECONDS.sleep(3);
    }


    /**
     * 测试a线程九秒内b才能获取到锁
     * @throws InterruptedException
     * @throws IOException
     */
    @Test
    public void test5() throws InterruptedException, IOException {
        Path path = Paths.get("myproject/nio/file/a.txt");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
        System.out.println("A begin time"+System.currentTimeMillis());
        fileChannel.lock("我是附加值A", new CompletionHandler<FileLock, String>() {
            @Override
            public void completed(FileLock result, String attachment) {

                try {
                    TimeUnit.SECONDS.sleep(9);
                    result.release();
                    System.out.println("A release lock time="+System.currentTimeMillis());
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, String attachment) {
                System.out.println("A failed hahaha");
            }
        });
        System.out.println("A end time="+System.currentTimeMillis());
        TimeUnit.SECONDS.sleep(10);
    }

    @Test
    public void test6() throws InterruptedException, IOException {
        Path path = Paths.get("myproject/nio/file/a.txt");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
        System.out.println("B begin time"+System.currentTimeMillis());
        fileChannel.lock("我是附加值B", new CompletionHandler<FileLock, String>() {
            @Override
            public void completed(FileLock result, String attachment) {

                try {
                    System.out.println("B complete "+attachment);
                    result.release();
                    System.out.println("A release lock time="+System.currentTimeMillis());
                    fileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, String attachment) {
                System.out.println("failed hahaha");
            }
        });
        System.out.println("end time="+System.currentTimeMillis());
        TimeUnit.SECONDS.sleep(8);
    }

    @Test
    public void testRead1() throws IOException, ExecutionException, InterruptedException {
        Path path = Paths.get("myproject/nio/file/a.txt");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        ByteBuffer allocate = ByteBuffer.allocate(3);
        Future<Integer> future = fileChannel.read(allocate, 0);
        System.out.println("length="+future.get());
        fileChannel.close();
        byte[] array = allocate.array();
        for (int i=0;i<array.length;i++){
            System.out.println(array[i]);
        }
    }

    @Test
    public void testWrite1() throws IOException, ExecutionException, InterruptedException {
        Path path = Paths.get("myproject/nio/file/a.txt");
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
        ByteBuffer allocate = ByteBuffer.wrap("abcde".getBytes());
        Future<Integer> future = fileChannel.write(allocate, 0);
        System.out.println(future.get());
    }


}
