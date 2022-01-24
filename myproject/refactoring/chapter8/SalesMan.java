package refactoring.chapter8;

public class SalesMan extends EmployeeType{
    @Override
    int getTypeCode() {
        return Employee.SALESMAN;
    }
}
