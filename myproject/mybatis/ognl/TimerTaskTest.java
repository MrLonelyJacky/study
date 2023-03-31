package mybatis.ognl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @description:
 * @author: jacky
 * @create: 2023-02-20 16:05
 **/
public class TimerTaskTest {
    public static void main(String[] args) throws ParseException {
        //创建定时任务
        TaskTest taskTest1 = new TaskTest("taskTest1");
        //定时器创建
        Timer timer = new Timer("定时器线程");
        //指定开始执行的日期
        Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-03-03 10:55:10");
        //时间间隔，单位是ms
        long intervalTime = 2 * 1000;
        try {
            timer.schedule(taskTest1, startDate, intervalTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class TaskTest extends TimerTask {
    //  测试任务名称
    private String taskTestName;

    public TaskTest(String taskTestName) {
        super();
        this.taskTestName = taskTestName;
    }

    @Override
    public void run() {
        //这里是需要执行的具体任务
        System.out.printf("定时任务  %s 执行中，响铃响一下\n", taskTestName);
    }


    public static void main(String[] args) {
       int a = (int) 1626162698405838848l;
        System.out.println(a);
    }
}
