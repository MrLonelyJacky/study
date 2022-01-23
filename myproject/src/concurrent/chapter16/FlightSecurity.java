package concurrent.chapter16;

/**
 * Created by 15151 on 2020/5/13.
 * 机场安检类
 */
public class FlightSecurity {
    private int count = 0;
    //登机牌
    private String boardingPass = "null";
    //身份证
    private String idCard = "null";

    public void pass(String boardingPass, String idCard) {
        this.boardingPass = boardingPass;
        this.idCard = idCard;
        this.count++;
        check();
    }

    private void check() {
        if (boardingPass.charAt(0) != idCard.charAt(0)) {
            throw new RuntimeException("登机牌和身份证首字母不同！无法通行！");
        }
        System.out.println("检验通过！！");
    }

    @Override
    public String toString() {
        return "FlightSecurity{" +
                "count=" + count +
                ", boardingPass='" + boardingPass + '\'' +
                ", idCard='" + idCard + '\'' +
                '}';
    }
}
