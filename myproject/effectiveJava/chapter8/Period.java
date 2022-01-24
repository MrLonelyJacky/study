package effectiveJava.chapter8;

import com.sun.istack.internal.NotNull;

import java.util.Date;
import java.util.Objects;

/**
 * Created by Administrator on 2020/2/13.
 * 乍看这个类没啥问题是不可变的 并且添加了约束条件 但是Date本身是可变的
 * 对于客户端就很容易去违反   拷贝性保护
 */
public final class Period {
    private final Date start;
    private final Date end;

    /**
     * 这里要进行一些保护性拷贝 注意这里不用date的clone方法是因为date是非final的
     *
     * @param start
     * @param end
     */
    public Period(@NotNull Date start, @NotNull Date end) {

        /*this.start = start;
        this.end = end;*/
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
        Objects.requireNonNull(this.start, "start cannot be null");
        Objects.requireNonNull(this.end, "end cannot be null");
        if (this.start.compareTo(this.end) > 0) {
            throw new IllegalArgumentException("start can not beyond end");
        }
    }

    /**
     * 这里要改造了 否则还是有漏洞
     *
     * @return
     */
    public Date start() {
        return new Date(start.getTime());
    }

    public Date end() {
        return new Date(end.getTime());
    }

    /**
     * 模拟客户端代码
     *
     * @param args
     */
    public static void main(String[] args) {
        /**
         * 攻击方式1
         */
        Date start = new Date();
        Date end = new Date();
        Period p = new Period(null, end);
        end.setYear(78);
    }
}
