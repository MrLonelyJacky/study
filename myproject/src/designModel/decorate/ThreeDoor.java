package designModel.decorate;

public class ThreeDoor {
    private int N;//定义N来表示进行实验的次数

    public ThreeDoor(int N) {

        if (N <= 0) {//检测N是否小于0，若是 抛出非法参数异常
            throw new IllegalArgumentException("N必须大于0");
        }
        //保存传入的N
        this.N = N;
    }

    public void run(boolean changeDoor) {

        int wins = 0;//用来记录中奖次数

        for (int i = 0; i < N; i++) {
            //传入是否换门的参数给play（）方法，play（）方法返回在换门/不换门情况下的是否中奖，中奖返回true，没中返回false
            if (play(changeDoor)) {
                wins++;//若中奖，次数+1
            }
        }
        //输出前提语句
        System.out.println(changeDoor ? "换门" : "不换门");
        //用中奖的次数除于总次数N得到概论
        System.out.println("中奖的概论是:" + (double) wins / N);

    }

    //定义一个play（）方法，模拟参赛者抽奖过程
    private boolean play(boolean changeDoor) {   //1，2，3号门中随机一个为中奖门
        int prizeDoor = (int) (Math.random() * 3);
        System.out.println("宝物藏在" + prizeDoor + "门中");
        //1，2，3号门中玩家随机选一个门
        int playerChoice = (int) (Math.random() * 3);
        System.out.println("你选择了" + playerChoice + "号门");
        if (prizeDoor == playerChoice) {
            //当玩家选的门是有车的门时，换门=输 不换门=赢
            return changeDoor ? false : true;
        } else {
            //当玩家选的门是无车的门时，换门=赢，不换门=输
            return changeDoor ? true : false;
        }

    }

    public static void main(String[] args) {
        System.out.println("假设汽车在3号门");
        int suceess=0;
        for (int i = 0; i < 99; i++) {

            int choice = (int) (Math.random() * 3) + 1;
            System.out.println("你选择了" + choice + "号门");
            if (choice == 1) {
                System.out.println("主持人打开了空门2---你换成3，成功！---");
                suceess++;
            } else if (choice == 2) {
                System.out.println("主持人打开了空门1---你换成3，成功！---");
                suceess++;
            } else {
                int c = (int) (Math.random() * 2);
                if (c == 0) {
                    System.out.println("主持人打开了空门1---你换成2，失败！---");
                } else {
                    System.out.println("主持人打开了空门2---你换成1，失败！---");
                }

            }
        }
        double gailv = (double) suceess / 100;
        System.out.println("换门成功的概率："+gailv);

    }

}
