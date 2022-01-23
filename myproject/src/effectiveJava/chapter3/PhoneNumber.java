package effectiveJava.chapter3;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 重写equals hashcode和toString 案例
 * 1.重写equals注意：自反性，x.equals(x)总为true 否则集合中contains方法就可能出错
 * 对称性 x.equals(y)和y.equals(x)总是一致   传递性：x.equals(y) x.equals(z) 则y.equals(z)
 * 注意：里氏替换原则  违反该规定可能导致递归equals 主要是父子类时出现传递错误（详见point类）
 * 若父类是抽象类就没问题因为抽象类不可实例化（可以考虑复合优于继承）  一致性：不可改变的对象一定保证对象永远相等
 * 非空性：避免空指针 通常使用instance of
 * 1.1 ==操作符检查参数是否为对象的引用 提高性能  1.2 使用instance of 避免空指针和类型转换异常
 * 1.3 优先比较关键域提高性能 1.4覆写equals总要覆写hashcode  1.5不要让equals过于智能
 * 1.6 不要轻易覆写equals 如果可以的话使用Object的就行了
 * <p>
 * 2.重写hashcode注意事项：因为hashcode要用于比如map这样的集合中，所以要遵循hashcode原则
 * 2.1程序运行期间对象的equals方法没有被改写 那么多次嗲用equals必须返回相同的hashcode值
 * 2.2如果两个对象根据equals相比较的值相同那么hashcode必须返回相同值
 * 2.3两个对象equals不同，不一定hashcode不同，但是让不相等的对象产生不同结果的hashcode能提高效率
 * map里比较时先判断hashcode是否相同 大多数时候不同 则判定为不是同一个对象提升了性能
 * 尽量理想化地将不同值的hashcode等分到int上
 * 整个步骤参见effective java 第三版45页  衍生域可以不参与计算，equals比较中没有的域不要参与hashcode会违反2.3
 *
 * @author vinci
 */
public class PhoneNumber implements Cloneable, Comparable<PhoneNumber> {
    private final short areaCode, prefix, lineNum;

    public PhoneNumber(short areaCode, short prefix, short lineNum) {
        this.areaCode = areaCode;
        this.prefix = prefix;
        this.lineNum = lineNum;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            //提高性能满足自反性
            return true;
        }
        if (!(o instanceof PhoneNumber)) {
            //避免空指针和类型转换异常  满足非空性
            return false;
        }
        PhoneNumber phoneNumber = (PhoneNumber) o;
        //注意优先关键域比较  这时候要问下是否满足对称性和传递性还有一致性呢
        //满足对称性 和一致性 在没有子类的情况下满足传递性
        return phoneNumber.lineNum == this.lineNum && phoneNumber.prefix == this.prefix
                && phoneNumber.areaCode == this.areaCode;
    }

    @Override
    public int hashCode() {
        int result = Short.hashCode(areaCode);
        result = 31 * result + Short.hashCode(prefix);
        result = 31 * result + Short.hashCode(lineNum);
        return result;
    }

    public short getAreaCode() {
        return areaCode;
    }

    public short getPrefix() {
        return prefix;
    }

    public short getLineNum() {
        return lineNum;
    }

    /**
     * 并不推荐这样的方式 更好的方式是提供一个拷贝构造器或者工厂
     *
     * @return
     */
    @Override
    public PhoneNumber clone() {
        /*try {
            return (PhoneNumber) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }*/
        //如果用构造器而不是上述被注释掉的代码 子类调用super.clone()就会抛异常
        //克隆方法必须保证一个调用链
        return new PhoneNumber(this.areaCode, this.prefix, this.lineNum);
    }


    public static void main(String[] args) {
        Map<PhoneNumber, String> m = new HashMap<>();
        short s1 = 7;
        short s2 = 8;
        short s3 = 9;
        m.put(new PhoneNumber(s1, s2, s3), "vinci");
        System.out.println(m.get(new PhoneNumber(s1, s2, s3)));
        PhoneNumber phoneNumber = new PhoneNumber(s1, s2, s3);
        PhoneNumber clone = phoneNumber.clone();
        //因为覆写了hash所以结果一样
        System.out.println(phoneNumber);
        System.out.println(clone);
        short a = 32767;
        int b= Integer.MAX_VALUE;
        int c = b+1;
        System.out.println(c);
    }


    /**
     * 优先比较关键域
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(PhoneNumber o) {
        int result = Short.compare(this.areaCode, o.areaCode);
        if (result == 0) {
            result = Short.compare(this.prefix, o.prefix);
            if (result == 0) {
                result = Short.compare(lineNum, o.lineNum);
            }
        }
        return result;
    }
}
