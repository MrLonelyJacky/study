package mybatis.entity;

public class Author {
    private int age;
    private String name;
    private String password;
    private String email;


    public Author(int age, String name, String password, String email) {
        this.age = age;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
