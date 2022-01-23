package refactoring.chapter7;

/**
 * 隐藏委托关系 HideDelegate
 * RemoveMiddleMan 去除中间人
 * 两者相反，具体看你怎么用，标准是被委托的函数如果很多就可以不用委托了
 * 
 *
 */
public class Person {
    /**
     * department 类中有经理属性，如果我要获取一个人的经理
     * 就必须 person.getDepartment().getManager()
     */
    Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     *但是如果我在person类中包含一个委托，就直接person.getManager();
     * 就减少了耦合，否则一旦委托关系改变比如经理改成另一个类DepartmentManager，
     * 就必须把所有用到person.getDepartment()的地方给换掉，如果采用如下委托方式
     * 只要改掉该方法就可以，当然这也有坏处，就是department类被委托了，那每次该类中
     * 有新的特性时我就得新增新的方法去委托，参见RemoveMiddleMan
     *
     */
    public Person getManager(){
        return department.getManager();
    }
}
