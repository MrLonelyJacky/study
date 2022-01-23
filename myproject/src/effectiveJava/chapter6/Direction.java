package effectiveJava.chapter6;

/**
 * Created by 15151 on 2020/1/22.
 * 下面实例来展示位域 平面上一般有八个方位 用某一位为1代表某个方向
 * 其他联合方向如东北则表示为EAST|NORTH  但是位域具有int枚举常量的所有缺点
 * 选择好类型 如int 还不能超出宽度32位
 */
public class Direction {
    public static final short NORTH = 1;

    public static final short SOUTH = 1 << 1;

    public static final short WEST = 1 << 2;

    public static final short EAST = 1 << 3;

}
