package thinkingJava.Annotation.dbAnnotation;

/**
 * Created by 15151 on 2019/5/10.
 */
@DBTable(name = "MEMBER")
public class Member {
    @SQLString(value = 30)
    String firstName;
    @SQLInteger
    Integer age;
    @SQLString(value = 30, constraints = @Constraints(primaryKey = true))
    String handler;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }
}
