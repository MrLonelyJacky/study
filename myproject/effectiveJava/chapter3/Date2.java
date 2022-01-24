package effectiveJava.chapter3;

import java.util.Date;

/**
 * Created by 15151 on 2020/5/15.
 */
public class Date2 extends Date{
    @Override
    public Object clone(){
        System.out.println("date2");
        return super.clone();
    }
}
