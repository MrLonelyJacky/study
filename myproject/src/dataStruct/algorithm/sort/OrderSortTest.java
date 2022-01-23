package dataStruct.algorithm.sort;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderSortTest{

    public static void main(String[] args) {

        Order o1 = new Order("2018-01-01",1234567890,3.14);
        Order o2 = new Order("2018-01-02",1234567891,3.14);
        Order o3 = new Order("2018-01-01",1234567892,3.15);
        Order o4 = new Order("2018-01-02",1234567893,3.16);
        Order o5 = new Order("2018-01-01",1234567893,3);

        List<Order> list = new ArrayList<>();
        list.add(o1);
        list.add(o2);
        list.add(o3);
        list.add(o4);
        list.add(o5);

        Collections.sort(list, (o11, o21) -> {
            String s1 = o11.getDateStr();
            String s2 = o21.getDateStr();

            int temp = s1.compareTo(s2);

            if(temp != 0){
                return  temp;
            }

            double m1 = o11.getMoney();
            double m2 = o21.getMoney();

            BigDecimal data1 = new BigDecimal(m1);
            BigDecimal data2 = new BigDecimal(m2);

            return data2.compareTo(data1);
        });

        System.out.println(list);
    }

    // 订单类
    static class Order{
        // 订单日期
        private String dateStr;
        // 订单号
        private long order;
        // 订单金额
        private double money;

        public String getDateStr() {
            return dateStr;
        }

        public long getOrder() {
            return order;
        }

        public double getMoney() {
            return money;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "dateStr='" + dateStr + '\'' +
                    ", order=" + order +
                    ", money=" + money +
                    '}';
        }
        public Order(String dateStr, long order, double money) {
            this.dateStr = dateStr;
            this.order = order;
            this.money = money;
        }
    }
}