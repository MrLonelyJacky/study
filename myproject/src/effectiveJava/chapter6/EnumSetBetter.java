package effectiveJava.chapter6;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by 15151 on 2020/1/19.
 * 利用EnumSet代替位域  更简洁 更清楚也更安全
 */
public class EnumSetBetter {
    public enum Style {BOLD, NORMAL;}

    public static void applyStyles(Set<Style> styles) {

    }

    public static void main(String[] args) {
        EnumSet<Style> bold = EnumSet.of(Style.BOLD, Style.NORMAL);

        Iterator<Style> iterator = bold.iterator();
        while (iterator.hasNext()){
            Style next = iterator.next();
            System.out.println();
        }
        System.out.println();
    }
}
