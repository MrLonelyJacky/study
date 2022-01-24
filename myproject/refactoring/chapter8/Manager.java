package refactoring.chapter8;

public class Manager extends EmployeeType {
    @Override
    int getTypeCode() {
        return Employee.MANAGER;
    }
}
