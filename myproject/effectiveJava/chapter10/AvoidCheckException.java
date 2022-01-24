package effectiveJava.chapter10;

/**
 * Created by 15151 on 2020/3/18.
 * avoid using checked exception
 * when we use checked exception always we want the caller
 * to recover sth like InputStream IoException the caller should do sth recover it
 * otherwise avoid using it  two ways to avoid
 * one use Optional but it cannot return extra information (nothing serious)
 * two use status check  make a check method  but be careful with repeating
 * （为了获取状态重复调用了发生异常的方法，不值得推荐，影响性能）
 * to sum up,abuse using checked exception will make client users painful
 * if client cannot recover then use unchecked exception
 * if client can recover and we want client to handle with it the optional should
 * be the first choice (do sth recover) or status check
 * 能不抛受检异常尽量不抛，哪怕是可以恢复，也要想方法尽量避免抛出受检异常
 */
public class AvoidCheckException {
    public static void main(String[] args) {

    }
}
