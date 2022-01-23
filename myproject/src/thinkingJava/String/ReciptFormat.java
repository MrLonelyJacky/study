package thinkingJava.String;

import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 15151 on 2019/3/25.
 */
public class ReciptFormat {
    private double total = 0;
    private Formatter formatter = new Formatter(System.out);
    public static void main(String[] args) {
        String t="x123xxxxxx123";
        Pattern p=Pattern.compile(".*123");
        Matcher m=p.matcher(t);
        System.out.println("===========贪婪模式================");
        while(m.find()){
            System.out.println("开始"+m.start());
            System.out.println(m.group());
            System.out.println("结束"+m.end());
        }

    }
    public void printTitle(){
        formatter.format("%-15s %5s %10s","Item","Qty","Price");
        formatter.format("%-15s %5s %10s","----","---","-----");
    }
    public void print(String item , int qty , double price){
        formatter.format("%-15s %5d %10.2f",item,qty,price);
        total +=price;
    }
    public void printTotal(){

    }
}
