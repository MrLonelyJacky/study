package designModel.visitor;

import org.apache.ibatis.jdbc.SQL;

import java.util.LinkedList;
import java.util.List;

public class BusinessReport {
    private List<Staff> mStaffs = new LinkedList<>();

    public BusinessReport() {
        mStaffs.add(new Manager("经理-A"));
        mStaffs.add(new Engineer("工程师-A"));
        mStaffs.add(new Engineer("工程师-B"));
        mStaffs.add(new Engineer("工程师-C"));
        mStaffs.add(new Manager("经理-B"));
        mStaffs.add(new Engineer("工程师-D"));
    }

    public void showReports(Visitor visitor){
        for (Staff staff : mStaffs){
            staff.accept(visitor);
        }
    }

    public static void main(String[] args) {

        // 构建报表
        BusinessReport report = new BusinessReport();
        System.out.println("=========== CEO看报表 ===========");
        report.showReports(new CeoVisitor());
        System.out.println("=========== CTO看报表 ===========");
        report.showReports(new CtoVisitor());
    }

}
