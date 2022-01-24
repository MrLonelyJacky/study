package effectiveJava.chapter4;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by 15151 on 2020/1/17.
 * make class and members minimum accessibility
 * 公有类的实例域决不能是公有的（详见第16 条） 。如果实例域是非final 的，或者是一
 个指向可变对象的final 引用， 那么一旦使这个域成为公有的，就等于放弃了对存储在这个
 域中的值进行限制的能力；这意味着，你也放弃了强制这个域不可变的能力。同时，当这个
 域被修改的时候，你也失去了对它采取任何行动的能力。因此， 包含公有可变域的类通常并
 不是线程安全的。即使域是final 的，并且引用不可变的对象，但当把这个域变成公有的时
 候，也就放弃了“切换到一种新的内部数据表示法”的灵活性。
 这条建议也同样适用于静态域，只是有一种情况例外。假设常量构成了类提供的整个
 抽象中的一部分，可以通过公有的静态final 域来暴露这些常量。按惯例，这种域的名称由
 大写字母组成，单词之间用下划线隔开（详见第68 条） 。很重要的一点是，这些域要么包含
 基本类型的值，要么包含指向不可变对象的引用（详见第17 条） 。如果final 域包含可变对
 象的引用，它便具有非final 域的所有缺点
 */
public class MinimumAccess {
    /**
     * if it is public ,it can be not safe
     * so we can make it private and provide public method for visiting
     * we have two ways to realize it
     */
    private static final Integer[] values = {1, 2, 3};

    /**
     * one method to return a unmodifiable list(view)
     *
     * @return
     */
    public static List<Integer> getSafeList() {
        return Collections.unmodifiableList(Arrays.asList(values));
    }

    /**
     * another method to return a array copy
     * @return
     */
    public static Integer[] getValues() {
        return values.clone();
    }

}
