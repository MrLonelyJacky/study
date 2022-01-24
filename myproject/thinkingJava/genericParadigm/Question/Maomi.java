package thinkingJava.genericParadigm.Question;

/**
 * Created by 15151 on 2019/5/5.
 * 这里叫做基类限定泛型接口不能使用
 * extends ComparablePet
 */
public class Maomi  implements Comparable<Maomi>{

    @Override
    public int compareTo(Maomi o) {
        return 0;
    }
}
