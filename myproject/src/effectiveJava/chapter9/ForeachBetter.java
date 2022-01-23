package effectiveJava.chapter9;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 15151 on 2020/3/6.
 * foreach is better than iterator and traditional for loop
 * because iterator can appear many times  it is confused and for loop is the same
 * but three common situations : 1.need remove element when loop 2.transform some elements need index
 * 3.parallel loop
 * some times u can use iterable interface to use foreach
 */
public class ForeachBetter {
    enum Suit {CLUB, HEART}
    enum Rank {ACE, DEUCE}
    static Collection<Suit> suits = Arrays.asList(Suit.values());
    static Collection<Rank> ranks = Arrays.asList(Rank.values());
    public static void wrongMethod(){
        for (Iterator<Suit> i = suits.iterator();i.hasNext();){
            for (Iterator<Rank> j = ranks.iterator();j.hasNext();){
                //NoSuchElementException
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(0.1+0.1+0.1+0.1);
    }
}
