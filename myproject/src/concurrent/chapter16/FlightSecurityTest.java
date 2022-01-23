package concurrent.chapter16;

/**
 * Created by 15151 on 2020/5/13.
 */
public class FlightSecurityTest {
    //旅客线程
    static class Passengers extends Thread {
        //机场安检类
        private final FlightSecurity flightSecurity;
        //旅客身份证
        private final String idCard;
        //登机牌
        private final String boardingPass;

        Passengers(FlightSecurity flightSecurity, String idCard, String boardingPass) {
            this.flightSecurity = flightSecurity;
            this.idCard = idCard;
            this.boardingPass = boardingPass;
        }

        /**
         * 参数传递会出现线程不安全问题
         */
        @Override
        public void run() {
            for (int i=0;i<1000;i++){
                //旅客过安检
                flightSecurity.pass(boardingPass, idCard);
            }

        }

    }

    public static void main(String[] args) {
        final FlightSecurity flightSecurity = new FlightSecurity();
        new Passengers(flightSecurity,"a123","af111").start();
        new Passengers(flightSecurity,"b123","bf111").start();
        new Passengers(flightSecurity,"c123","cf111").start();
        new Passengers(flightSecurity,"d123","df111").start();
       /* new Passengers(flightSecurity,"e123","ef111").start();
        new Passengers(flightSecurity,"f123","ff111").start();
        new Passengers(flightSecurity,"g123","gf111").start();*/
        System.out.println("main thread exit");

    }
}
