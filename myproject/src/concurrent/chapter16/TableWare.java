package concurrent.chapter16;

/**
 * Created by 15151 on 2020/5/13.
 * 吃面问题导致死锁
 */
public class TableWare {
    //餐具名称
    private final String toolName;

    public TableWare(String toolName) {
        this.toolName = toolName;
    }

    @Override
    public String toString() {
        return "TableWare{" +
                "toolName='" + toolName + '\'' +
                '}';
    }
}
