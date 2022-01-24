package effectiveJava.chapter3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15151 on 2019/12/19.
 * equals 自反性违反示例
 */
public final class CaseInsensitiveString {
    private final String s;

    public CaseInsensitiveString(String s) {
        this.s = s;
    }

    /**
     * 该 equals 违反了自反性
     * @param o
     * @return
     */

    /*public boolean equals(Object o) {
        if (o instanceof CaseInsensitiveString) {
            return s.equalsIgnoreCase(((CaseInsensitiveString) o).s);
        }
        return o instanceof String && s.equalsIgnoreCase((String) o);
    }*/

    /**
     * 为了解决这个问题，只需把企图与Str 工口q 互操作的这段代码从equals 方法中去掉
     就可以了。这样做之后，就可以重构该方法，使它变成一条单独的返回语句：
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof CaseInsensitiveString && s.equalsIgnoreCase(((CaseInsensitiveString) o).s);
    }

    public static void main(String[] args) {
        CaseInsensitiveString cis = new CaseInsensitiveString("Publish");
        String s = "publish";
        System.out.println(cis.equals(s));
        System.out.println(s.equals(cis.s));
        List<CaseInsensitiveString> list = new ArrayList<>();
        list.add(cis);
        /**
         * －.§.违反了equ als 约定，当其他对象面对你
         的对象时，你完全不知道这些对象的行为会怎么样。
         */
        list.contains(s);
    }
}
