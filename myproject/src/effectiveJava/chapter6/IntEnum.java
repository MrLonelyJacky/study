package effectiveJava.chapter6;

/**
 * Created by 15151 on 2020/1/22.
 * int 枚举有很多不足，首先不具有类型安全，也没有什么描述性可言 如将apple传入到orange中也不会发出警告
 * int 枚举是编译时常量 倘若值换了则客户端需要重新编译才能得到正确的值
 * 枚举则更好 类型安全 倘如枚举常量被替换了或者移除了则客户端在未编译的情况下会抛异常
 */
public class IntEnum {
    public static final int APPLE_FUJI = 0;
    public static final int ORANGE_FUJI = 0;
}
