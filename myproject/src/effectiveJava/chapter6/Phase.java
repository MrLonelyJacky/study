package effectiveJava.chapter6;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by 15151 on 2020/1/31.
 *
 * @author vinci
 */
public enum Phase {
    /**
     *
     */
    SOLID, LIQUID, GAS;

    /**
     * 固液气对应的转换
     */
    public enum Transition {
        /**
         * 可以用策略枚举和enumMap的方式分别优化下列两行代码
         */
        /*MELT, FREEZE, BOIL, CONDENSE, SUBLIME, DEPOSIT;
        private static final Transition[][] TRANSITIONS = {{null, MELT, SUBLIME},
                {FREEZE, null, BOIL}, {DEPOSIT, CONDENSE, null}};*/
        MELT(SOLID,LIQUID), FREEZE(LIQUID,SOLID), BOIL(LIQUID,GAS),
        CONDENSE(GAS,LIQUID), SUBLIME(SOLID,GAS), DEPOSIT(GAS,SOLID);
        private final Phase from;
        private final Phase to;
        /*private static final Map<Phase,Map<Phase,Transition>> m = Stream.of(values())
                .collect(Collectors.groupingBy());*/
        Transition(Phase from, Phase to) {
            this.from = from;
            this.to = to;
        }

        public static void main(String[] args) {
            Transition[] values = values();
        }

        /**
         * 这种方式坏处是数组越界问题 空指针，而且没有任何提示
         *
         * @param from
         * @param to
         * @return
         */
        /*public static Transition from(Phase from, Phase to) {
            return TRANSITIONS[from.ordinal()][to.ordinal()];
        }*/
    }
}
