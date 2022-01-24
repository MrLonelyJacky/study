package concurrent.art.chapter5;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *  use thread local to parse is safe
 */
public class DateUtilSafe {

    private static final ThreadLocal<DateFormat> THREAD_LOCAL = ThreadLocal.withInitial(
            () -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    );

    public static Date parse(String dateStr) {

        Date date = null;
        try {
            date = THREAD_LOCAL.get().parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static void main(String[] args) {

        // 创建线程池
        // ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 20, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024));
        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.execute(
                    ()-> System.out.println(parse("2019-06-01 16:34:30"))
            );
        }
        threadPoolExecutor.shutdown();
    }
}
