package refactoring.chapter8;

/**
 * replace type class with strategy/state
 * 有个员工类，有一些状态码，但是我们面临着状态的切换，比如工程师由于表现好，升职为管理者
 * 所以我们要把状态抽象出来详见EmployeeType
 * 注释掉的代码都是重构之前的代码
 */
public class Employee {
    //private int type;
    public static final int ENGINEER = 0;
    public static final int SALESMAN = 1;
    public static final int MANAGER = 2;

    /**
     *
     */
    private EmployeeType employeeType;



    public void setType(int type){
        employeeType=EmployeeType.newType(type);
    }

    public int getEmployeeType() {
        return employeeType.getTypeCode();
    }

    /**
     * 状态模式之前
     * @return
     */
   /* public int payAmount() {
        switch (type){
            case ENGINEER:
                return 1000;
            case SALESMAN:
                return 1000+200;
            case MANAGER:
            return 1000+500;
            default:
                throw new RuntimeException("illegal type");
        }
    }*/

    /**
     * 做到这一步之后还需要做一个事情，就是把这switch case用多态去代替
     * 这里我就不做了，可以用一个接口计算价格的
     * @return
     */
    public int payAmount() {
        switch (getEmployeeType()){
            case EmployeeType.ENGINEER:
                return 1000;
            case EmployeeType.SALESMAN:
                return 1000+200;
            case EmployeeType.MANAGER:
                return 1000+500;
            default:
                throw new RuntimeException("illegal type");
        }
    }
}
