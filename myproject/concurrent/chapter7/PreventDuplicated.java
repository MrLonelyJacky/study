package concurrent.chapter7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by 15151 on 2020/4/28.
 * 开发中经常会遇到hook线程，比如为了防止某个程序被重复启动，在进程启动时
 * 会创建一个lock文件，进程收到中断信号时会删除这个lock文件 mysql kafka zookeeper都有
 */
public class PreventDuplicated {
    private final static String LOCK_PATH = "D:";
    private final static String LOCK_FILE = "a.lock";
    // private final static String PERMISSIONS = "rw-------";

    public static void main(String[] args) throws IOException {
        //1、注入hook线程，在程序结束时删除lock文件
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("the program received kill signal");
            getLockFile().toFile().delete();
        }));
        //2.检查是否存在.lock文件
        checkRunning();
        //3.简单模拟当前程序正在运行
        //TODO 为什么进程关闭了但是hook线程没有运行呢
        for (int i = 0; i < 1000; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("program is running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("program stop");
    }

    private static Path getLockFile() {
        return Paths.get(LOCK_PATH, LOCK_FILE);
    }

    private static void checkRunning() throws IOException {
        Path lockFile = getLockFile();
        if (lockFile.toFile().exists()) {
            throw new RuntimeException("the program already running!");
        }
        //Set<PosixFilePermission> posixFilePermissions = PosixFilePermissions.fromString(PERMISSIONS);
        Files.createFile(lockFile);
        System.out.println("create file!");
    }
}
