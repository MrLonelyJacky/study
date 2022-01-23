package refactoring.chapter6;

/**
 * 分离临时变量
 */
public class SplitTempVariable {
    private double primaryForce;
    private double secondaryForce;
    private double mass;
    private int delay;



    double getDistanceTravelled(int time) {
        double result;
        double acc = primaryForce / mass;
        int primaryTime = Math.min(time, delay);
        result = 0.5 * acc * primaryTime * primaryTime;
        int secondaryTime = time - delay;
        if (secondaryTime > 0) {
            double primaryVal = acc * delay;
            acc = (primaryTime + secondaryForce) / mass;
            result += primaryVal * secondaryTime + 0.5 * acc * secondaryTime * secondaryTime;
        }
        return result;
    }


    /**
     * 先把重复赋值的临时变量分离开来
     * @param time
     * @return
     */
    double getDistanceTravelled2(int time) {
        double result;
        final double primaryAcc = primaryForce / mass;
        int primaryTime = Math.min(time, delay);
        result = 0.5 * primaryAcc * primaryTime * primaryTime;
        int secondaryTime = time - delay;
        if (secondaryTime > 0) {
            double primaryVal = primaryAcc * delay;
            final double secondaryAcc = (primaryTime + secondaryForce) / mass;
            result += primaryVal * secondaryTime + 0.5 * secondaryAcc * secondaryTime * secondaryTime;
        }
        return result;
    }


    /**
     * 开始我们的重构，根据之前所学
     * @param time
     * @return
     */
    double getDistanceTravelled3(int time) {
        double result = 0.5 * getPrimaryAcc() * getPrimaryTime(time) * getPrimaryTime(time);
        if (getSecondaryTime(time) > 0) {
            result += getPrimaryVal() * getSecondaryTime(time) + getHalfSecondAccSquare(time);
        }
        return result;
    }

    double getPrimaryAcc(){
        return primaryForce / mass;
    }

    double getPrimaryTime(int time){
        return Math.min(time, delay);
    }

    double getSecondaryTime(int time){
        return time - delay;
    }

    double getPrimaryVal(){
        return getPrimaryAcc() * delay;
    }

    double getSecodaryAcc(int time){
        return (getPrimaryTime(time) + secondaryForce) / mass;
    }

    double getHalfSecondAccSquare(int time){
       return  0.5 * getSecodaryAcc(time) * getSecondaryTime(time) * getSecondaryTime(time);
    }
}
