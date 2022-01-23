package refactoring.chapter8;

public abstract class EmployeeType {
    abstract int getTypeCode();

    public static EmployeeType newType(int type) {
        switch (type) {
            case ENGINEER:
                return new Engineer();

            case SALESMAN:
                return new SalesMan();

            case MANAGER:
                return new Manager();
            default:
                throw new RuntimeException("illegal type");
        }
    }

    public static final int ENGINEER = 0;
    public static final int SALESMAN = 1;
    public static final int MANAGER = 2;
}
