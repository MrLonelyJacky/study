package refactoring.chapter8;

public class Engineer extends EmployeeType{
    @Override
    int getTypeCode() {
        return Employee.ENGINEER;
    }
}
