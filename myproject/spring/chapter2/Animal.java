package spring.chapter2;

public abstract class Animal {
    //用于lookup-method注入
    public abstract Dog createDog();

    private Dog dog;

    public Dog getDog() {
        return dog;
    }

    //setter注入
    public void setDog(Dog dog) {
        this.dog = dog;
    }
}
